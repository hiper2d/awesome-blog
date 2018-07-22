import {Injectable} from '@angular/core';
import {AbstractService} from './abstract-service';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/internal/Observable';
import {map, tap} from 'rxjs/operators';
import {ApiConst} from '../../util/api.const';
import {TokenRequest} from "../../model/token-request.model";
import {Credentials} from "../../model/credentials.model";

@Injectable({
  providedIn: 'root'
})
export class AuthService extends AbstractService {

  authenticated = false;
  username: string;

  constructor(http: HttpClient) {
    super(http);
  }

  private static createToken(credentials: Credentials) {
    return {
      authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    };
  }

  /*getAnonymousToken(): Observable<any> {
      const tokenRequest = new TokenRequest();
      const creds = new Credentials({username: 'frontend', password: 'awesome_secret'});
      const headers = new HttpHeaders(AuthService.createToken(creds));
  }*/

  authenticate(credentials: Credentials): Observable<any> {
    const headers = new HttpHeaders(credentials ? AuthService.createToken(credentials) : {});
    return this.textWithHeaders(ApiConst.USER, headers).pipe(
      tap(response => this.username = response),
      map<string, boolean>(response => !!response),
      tap(isAuthenticated => this.authenticated = isAuthenticated)
    );
  }

  logout(): Observable<never> {
    return this.post<any, never>(ApiConst.LOGOUT, null).pipe(
      tap(_ => {
        this.username = null;
        this.authenticated = false;
      })
    );
  }
}
