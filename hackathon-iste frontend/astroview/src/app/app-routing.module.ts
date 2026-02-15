import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { ObservationsPageComponent } from './observations/observations-page/observations-page.component';
import { EducationPageComponent} from './education/education-page.component/education-page.component';

const routes: Routes = [
  {
    path:'login',
    component:LoginComponent,
  },
  {
    path:'register',
    component:RegisterComponent,
  },
  { path: 'observations', component: ObservationsPageComponent },
  {
    path:'educationpage',
    component:EducationPageComponent
  },
  {
    path:'',
    component:LoginComponent
  },
 
 
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
