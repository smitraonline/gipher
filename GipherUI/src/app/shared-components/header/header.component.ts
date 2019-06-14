import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/modules/authentication/authentication.service';
import { TOKEN_NAME } from '../../modules/authentication/authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  searchKey : string;
  verified: boolean;

  constructor(public router: Router, private authService: AuthenticationService) { }

  ngOnInit() {
    if(localStorage.getItem(TOKEN_NAME)) {
      this.verified = true;
    }
    this.authService.getAuthenticationStatus()
      .subscribe(item => {
        console.log("checking for login status");
        this.verified=item});
  }

  logout() {
    this.authService.logout();
    this.router.navigate(["/home"]);
  }

}
