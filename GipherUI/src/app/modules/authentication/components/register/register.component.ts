import { Component, OnInit } from '@angular/core';
import { User } from '../../User';
import { AuthenticationService } from '../../authentication.service';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user: User;

  constructor(
    private authService: AuthenticationService,
    private snackbar: MatSnackBar,
    private router: Router) {
    this.user = new User();
  }

  ngOnInit() {
  }

  register() {
    var valid = this.validate();
    if(!valid) return;

    this.authService.registerUser(this.user).subscribe( data => {
      if(data.status === 201) {
        this.snackbar.open("Successfully registered", " ", {
          duration: 1000
        });
        // this.authService.saveUser(this.user).subscribe( savedata => {
        //   console.log(savedata);
        // });
      }
      this.router.navigate(["/login"]);
    }, error => {
      console.log(error)
      if(error.status === 409) {
        const errorMsg = error.error.message;
        this.snackbar.open(errorMsg, " ", {
          duration: 1000
        });
      }
    })
  }

  validate() : boolean {
    var emailEx = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if(!this.user.username) {
      this.snackbar.open("Please enter Username", " ", {
        duration: 1000
      });
      return false;
    }
    if(!this.user.email) {
      this.snackbar.open("Please enter Email", " ", {
        duration: 1000
      });
      return false;
    }
    if(!this.user.email.match(emailEx)) {
      this.snackbar.open("Incorrect email", " ", {
        duration: 1000
      });
      return false;
    }
    if(!this.user.password) {
      this.snackbar.open("Please provide a Password", " ", {
        duration: 1000
      });
      return false;
    }
    return true;
  }

}
