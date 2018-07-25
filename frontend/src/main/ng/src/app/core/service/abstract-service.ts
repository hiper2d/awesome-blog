import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs/internal/Observable';

export abstract class AbstractService {

  protected constructor(private http: HttpClient) {}

  protected post<T, R>(url: string, body: T, params?: HttpParams): Observable<R> {
    return this.http.post<R>(url, body, {params: params});
  }

  protected text(url: string): Observable<string> {
    return this.http.get(url, {responseType: 'text'});
  }
}
