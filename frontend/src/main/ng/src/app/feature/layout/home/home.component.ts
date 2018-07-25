import {Component, OnInit} from '@angular/core';
import {ApiService} from "../../../core/service/api.service";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit {
  echo = 'nope';
  username = '';

  constructor(private apiService: ApiService) {}

    ngOnInit(): void {
      this.apiService.echo().subscribe(r => this.echo = r);
    }
}
