import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './modules/home/home.component';
import { LoginComponent } from './modules/authentication/components/login/login.component';
import { RegisterComponent } from './modules/authentication/components/register/register.component';
import { CardContainerComponent } from './modules/gipher/components/card-container/card-container.component';
import { AuthGuard } from './modules/gipher/auth.guard';
import { BookmarksComponent } from './modules/gipher/components/bookmarks/bookmarks.component';
import { HistoryComponent } from './modules/gipher/components/history/history.component';
import { RecommenderComponent } from './modules/gipher/components/recommender/recommender.component';

const routes: Routes =[
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
  }, {
    path: '',
    children: [
        {
      path: 'home',
      component: HomeComponent
      //loadChildren: './layouts/home-layout/home-layout.module#HomeLayoutModule'
  }]},
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "register",
    component: RegisterComponent
  },
  {
    path: "explore",
    component: CardContainerComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "favourite",
    component: BookmarksComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "recommendations",
    component: RecommenderComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "history",
    component: HistoryComponent,
    canActivate: [AuthGuard]
  },
    // { path: 'dashboard',      component: DashboardComponent },
    // { path: '',               redirectTo: 'dashboard', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
