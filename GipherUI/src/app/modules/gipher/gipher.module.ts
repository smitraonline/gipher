import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardComponent } from './components/card/card.component';
import { CardContainerComponent } from './components/card-container/card-container.component';
import { BookmarksComponent } from './components/bookmarks/bookmarks.component';
import { RecommenderComponent } from './components/recommender/recommender.component';
import { AngularMaterialModule } from 'src/app/angular-material/angular-material.module';
import { GipherService } from './gipher.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { InterceptorService } from './interceptor.service';
import { HistoryComponent } from './components/history/history.component';
import { AppRoutingModule } from 'src/app/app-routing.module';

@NgModule({
  declarations: [CardComponent, CardContainerComponent, BookmarksComponent, RecommenderComponent, HistoryComponent],
  imports: [
    CommonModule,
    AppRoutingModule,
    AngularMaterialModule
  ],
  exports: [CardComponent, CardContainerComponent, BookmarksComponent, RecommenderComponent, HistoryComponent],

  providers: [
    GipherService, {
      provide: HTTP_INTERCEPTORS,
      useClass: InterceptorService,
      multi: true
    }
  ]
})
export class GipherModule { }
