import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthModule } from './auth/auth.module';
import { ObservationsModule } from './observations/observations.module';
import { HomepageModule } from './homepage/homepage.module';
import { SharedModule } from './shared/shared.module';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AuthInterceptor } from './interceptor/auth.interceptor';
import { EducationModule } from './education/education.module';
import { CommonModule } from '@angular/common';   



@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AuthModule,
    ObservationsModule,
    HomepageModule,
    SharedModule,
    HttpClientModule,
    EducationModule,
    CommonModule

  ],
  providers: [
     {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true              // ✅ VERY IMPORTANT
    } 
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
