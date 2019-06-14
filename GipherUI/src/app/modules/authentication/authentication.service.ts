import { Injectable, Output, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';

export const TOKEN_NAME = 'jwt_token';
export const USER_NAME = 'username';
export const DISABLE_AUTO_SAVE = 'disable_auto_save';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  thirdPartyAPI: string;
  apiKey: string;
  springRegisterEndPoint: string;
  springSaveEndPoint: string;
  springLoginEndPoint: string;
  @Output() verified: EventEmitter<any> = new EventEmitter();

  private location: Location;

  constructor(private httpClient: HttpClient) {
    //this.springRegisterEndPoint = "http://localhost:9090/api/v1/usertrackservice/";
    //this.springSaveEndPoint = "http://localhost:9091/api/v1/userservice/";
    //this.springRegisterEndPoint = "http://"+ location.hostname + ":9093/orchestrationservice/api/v1/user";
    this.springRegisterEndPoint = "http://"+ location.hostname + ":9080/accountmanagerservice/api/v1/register";
    this.springLoginEndPoint = "http://"+ location.hostname + ":9080/accountmanagerservice/api/v1/login";
  }
  
  registerUser(newUser) {
    const url = this.springRegisterEndPoint;
    return this.httpClient.post(url, newUser, { observe : "response"});
  }

  // saveUser(newUser) {
  //   const url = this.springSaveEndPoint + "save";
  //   return this.httpClient.post(url, newUser);
  // }
  
  loginUser(newUser) {
    const url = this.springLoginEndPoint;
    sessionStorage.setItem(USER_NAME, newUser.username);
    this.verified.emit(true);
    return this.httpClient.post(url, newUser, { observe : "response"});
  }

  getToken() {
    return localStorage.getItem(TOKEN_NAME);
  }

  isTokenExpired(token?: string): boolean {
    if(localStorage.getItem(TOKEN_NAME)) {
      return true;
    } else {
      return false;
    }
  }

  logout() {
    sessionStorage.removeItem(USER_NAME);
    sessionStorage.clear();
    localStorage.removeItem(TOKEN_NAME);
    localStorage.clear();
    this.verified.emit(false);
  }

  getAuthenticationStatus() {
    return this.verified;
  }
}
