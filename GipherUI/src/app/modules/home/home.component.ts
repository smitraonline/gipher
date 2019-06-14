import { Component, OnInit } from '@angular/core';
import { TOKEN_NAME } from '../../modules/authentication/authentication.service';
import { AuthenticationService } from 'src/app/modules/authentication/authentication.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  verified: boolean;

  constructor(private authService: AuthenticationService) { }

  ngOnInit() {
    if(localStorage.getItem(TOKEN_NAME)) {
      console.log(localStorage.getItem(TOKEN_NAME));
      this.verified = true;
    }
    this.authService.getAuthenticationStatus()
      .subscribe(item => {
        console.log("checking for login status");
        this.verified=item});
  }

}
