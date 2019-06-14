import { TestBed } from '@angular/core/testing';

import { AuthenticationService } from './authentication.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

const testdata = {
  username: "test",
  password: "testpass",
  email: "testemail"
}

describe('AuthenticationService', () => {
  let authService : AuthenticationService;
  let httpTestingController :  HttpTestingController;
  const testForRegister = "http://"+ location.hostname + ":9080/accountmanagerservice/api/v1/register";
  const testForLogin = "http://"+ location.hostname + ":9080/accountmanagerservice/api/v1/login";
  
  beforeEach(() => {TestBed.configureTestingModule({
    imports: [HttpClientTestingModule],
    providers: [AuthenticationService]
    })

    authService = TestBed.get(AuthenticationService);
    httpTestingController = TestBed.get(HttpTestingController);
  }
  );

  it('should be created', () => {
    //const service: AuthenticationService = TestBed.get(AuthenticationService);
    expect(authService).toBeTruthy();
  });

  it('#registerUser() should fetch proper response from Http call', () => {
    authService.registerUser(testdata).subscribe(res => {
      console.log(res);
      expect(res.body).toBe(testdata);
    });

    const httpMockReq = httpTestingController.expectOne(testForRegister);
    expect(httpMockReq.request.method).toBe('POST');
    expect(httpMockReq.request.url).toEqual(testForRegister);
  });

  it('#login() should fetch proper response from Http call', () => {
    authService.loginUser(testdata).subscribe(res => {
      console.log(res);
      expect(res.body).toBe(testdata);
    });

    const httpMockReq = httpTestingController.expectOne(testForLogin);
    expect(httpMockReq.request.method).toBe('POST');
    expect(httpMockReq.request.url).toEqual(testForLogin);
  });
});
