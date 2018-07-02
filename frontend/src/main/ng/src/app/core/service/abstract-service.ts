import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs/internal/Observable';

export abstract class AbstractService {

  protected constructor(private http: HttpClient) {}

  protected post<T, R>(url: string, body: T, params?: HttpParams): Observable<R> {
    return this.http.post<R>(url, body, {params: params});
  }

  protected textWithHeaders(url: string, headers: HttpHeaders): Observable<any> {
    return this.http.get(url, {headers: headers, responseType: 'text'});
  }

  protected text(url: string) {
    return this.http.get(url, {responseType: 'text'});
  }
}
