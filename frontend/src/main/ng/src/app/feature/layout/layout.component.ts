import {Component} from '@angular/core';
import {AuthService} from '../../core/service/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html'
})
export class LayoutComponent {

  constructor(
    private router: Router,
    private authService: AuthService
  ) {}

  logout() {
    this.authService.logout().subscribe(_ => {
      this.router.navigate(['/']);
    });
  }
}
