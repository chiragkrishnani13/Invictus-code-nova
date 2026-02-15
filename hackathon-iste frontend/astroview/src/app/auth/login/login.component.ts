import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  loginForm = new FormGroup({
    email: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  });

  loginkaro() {

    if (this.loginForm.invalid) {
      return;
    }

    this.authService.loginkaro(this.loginForm.value).subscribe({
      next: (res: any) => {

        // 1️⃣ TOKEN STORE
        localStorage.setItem('token', res.token);

        // 2️⃣ TOKEN DECODE
        const payload = JSON.parse(atob(res.token.split('.')[1]));

        // 3️⃣ USER INFO STORE (navbar ke liye)
        const user = {
          userId: payload.userId,
          email: payload.sub,
          name: payload.name || payload.sub   // fallback
        };

        localStorage.setItem('user', JSON.stringify(user));

        // 4️⃣ NAVIGATE (tumne bola ye rehne do)
        this.router.navigate(['/observations']);
      },

      error: (err) => {
        console.log(err);
        alert('Login ya password galat hai bro');
      }
    });
  }
}
