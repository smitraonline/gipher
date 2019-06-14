import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { SharedComponentsModule } from './shared-components/shared-components.module';
import { GipherModule } from './modules/gipher/gipher.module';
import { AuthGuard } from './modules/gipher/auth.guard';
import { HomeComponent } from './modules/home/home.component';
import { AuthenticationModule } from './modules/authentication/authentication.module';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    AuthenticationModule,
    SharedComponentsModule,
    GipherModule
  ],
  providers: [AuthGuard],
  bootstrap: [AppComponent],
  exports: [HomeComponent]
})
export class AppModule { }
