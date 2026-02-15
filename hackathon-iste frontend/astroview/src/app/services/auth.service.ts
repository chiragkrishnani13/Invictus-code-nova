import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  

  constructor(private HttpClient:HttpClient) { }
    apiUrl:string='http://localhost:8088';

  registerkaro(registerForm:any):Observable<any>{
    console.log('registerform:',registerForm)
    return this.HttpClient.post(`${this.apiUrl}/register`,registerForm);

  }
  loginkaro(loginForm:any):Observable<any>{
    console.log('loginform',loginForm)
    return this.HttpClient.post(`${this.apiUrl}/login`,loginForm)
  }
}
