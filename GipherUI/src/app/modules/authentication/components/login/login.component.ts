import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../authentication.service';
import { TOKEN_NAME } from '../../authentication.service';
import { User } from '../../User';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User;

  constructor(
    private authService: AuthenticationService,
    private snackbar: MatSnackBar,
    private router: Router) {
    this.user = new User();
  }

  ngOnInit() {
  }

  login() {
    this.authService.loginUser(this.user).subscribe(data => {
      console.log(data);
      if(data) {
        console.log("token:", data.body["token"]);
        localStorage.setItem(TOKEN_NAME, data.body["token"]);
        this.snackbar.open(data.body["message"], " ", {
          duration: 1000
        });
        this.router.navigate(['/explore']);
      }
    }, error => {
      console.log(error)
      if(error.status === 404) {
        const errorMsg = error.error.message;
        this.snackbar.open(errorMsg, " ", {
          duration: 1000
        });
      }
      this.authService.logout();
    })
  }


}
