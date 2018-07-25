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

  authenticate(credentials: Credentials): Observable<any> {
    return this.post<Credentials, any>(ApiConst.TOKEN, credentials)
  }
}
