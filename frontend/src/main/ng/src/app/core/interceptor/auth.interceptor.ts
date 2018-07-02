import {Injectable} from '@angular/core';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs/internal/Observable';
import {catchError} from 'rxjs/operators';
import {Router} from '@angular/router';
import {throwError} from 'rxjs/internal/observable/throwError';
import {EMPTY} from 'rxjs/internal/observable/empty';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private router: Router) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      catchError(err => this.handleError(err))
    );
  }

  private handleError(err: any): Observable<any> {
    if (err instanceof HttpErrorResponse) {
      const resp = err as HttpErrorResponse;
      if (resp.status === 401) {
        return this.handleUnauthorizedError();
      }
    }
    return throwError(err);
  }

  private handleUnauthorizedError(): Observable<never> {
    this.router.navigate(['/login']);
    return EMPTY;
  }
}
