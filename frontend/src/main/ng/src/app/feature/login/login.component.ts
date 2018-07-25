import {Component} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {debounceTime} from 'rxjs/operators';
import {Router} from '@angular/router';
import {AuthService} from '../../core/service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  form: FormGroup;

  constructor(
    formBuilder: FormBuilder,
    private router: Router,
    private authService: AuthService
  ) {
    this.form = formBuilder.group({
      username: null,
      password: null
    });
  }

  login() {
    console.log(this.form.value);
    this.authService.authenticate(this.form.value).pipe(debounceTime(400)).subscribe(result => {
        console.log(result);
      if (result) {
        this.router.navigate(['/']);
      } else {
        this.form.reset();
      }
    });
  }
}
