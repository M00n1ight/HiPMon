import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GraphsComponent } from './view/graphs/graphs.component';
import { HeaderComponent } from './frames/header/header.component';
import {ChartsModule} from 'ng2-charts';
import { DashboardComponent } from './view/dashboard/dashboard.component';
import {GridsterModule} from 'angular-gridster2';

@NgModule({
  declarations: [
    AppComponent,
    GraphsComponent,
    HeaderComponent,
    DashboardComponent
  ],
  imports: [
    GridsterModule,
    BrowserModule,
    AppRoutingModule,
    ChartsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
