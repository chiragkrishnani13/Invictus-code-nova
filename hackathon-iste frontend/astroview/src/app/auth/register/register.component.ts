import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  constructor(private authservice:AuthService){}
  registerForm=new FormGroup({
    name:new FormControl('',[Validators.required]),
    email:new FormControl('',[Validators.required]),
    password:new FormControl('',[Validators.required]),
    city:new FormControl('',[Validators.required]),
    country:new FormControl('',[Validators.required]),

  })
  registerkaro(){
    this.authservice.registerkaro(this.registerForm.value).subscribe({
      next:(data)=>{
        console.log(data);
      },
      error:(err)=>{
        console.log("error",err);
      }


    })
  }

}
