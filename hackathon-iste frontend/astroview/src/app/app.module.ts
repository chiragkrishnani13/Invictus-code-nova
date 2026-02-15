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
import { CityEventsPageComponent } from './city-events-page/city-events-page.component';
import { CitySearchComponent } from './city-search/city-search.component';
import { ReplayCardComponent } from './replay-card/replay-card.component';
import { ReplayViewerModalComponent } from './replay-viewer-modal/replay-viewer-modal.component';
import { UpcomingEventsComponent } from './upcoming-events/upcoming-events.component';


@NgModule({
  declarations: [
    AppComponent,
    CityEventsPageComponent,
    CitySearchComponent,
    ReplayCardComponent,
    ReplayViewerModalComponent,
    UpcomingEventsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AuthModule,
    ObservationsModule,
    HomepageModule,
    SharedModule,
    HttpClientModule,
    CityEventsModule

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
