import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GraphsComponent } from './view/graphs/graphs.component';
import { HeaderComponent } from './frames/header/header.component';
import {ChartsModule} from 'ng2-charts';
import { DashboardComponent } from './view/dashboard/dashboard.component';
import {GridsterModule} from 'angular-gridster2';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {OWL_DATE_TIME_LOCALE, OwlDateTimeModule, OwlNativeDateTimeModule} from '@danielmoncada/angular-datetime-picker';
import { HistoryComponent } from './view/history/history.component';
import { SensorsGroupComponent } from './view/settings/sensors-group.component';
import { IncedentsComponent } from './view/incedents/incedents.component';
import {MatTableModule} from '@angular/material/table';
import { ModalFromSensorComponent } from './view/settings/modal-from-sensor/modal-from-sensor.component';
import {MatDialogModule} from '@angular/material/dialog';
import { SensorsComponent } from './view/settings/sensors/sensors.component';
import { HttpClientModule } from '@angular/common/http';
import {ApiService} from './services/api.service';
import {MatButtonModule} from '@angular/material/button';
import { PerfectScrollbarModule } from 'ngx-perfect-scrollbar';
import { PERFECT_SCROLLBAR_CONFIG } from 'ngx-perfect-scrollbar';
import { PerfectScrollbarConfigInterface } from 'ngx-perfect-scrollbar';

const DEFAULT_PERFECT_SCROLLBAR_CONFIG: PerfectScrollbarConfigInterface = {
  suppressScrollX: true
};


@NgModule({
  declarations: [
    AppComponent,
    GraphsComponent,
    HeaderComponent,
    DashboardComponent,
    HistoryComponent,
    SensorsGroupComponent,
    IncedentsComponent,
    ModalFromSensorComponent,
    SensorsComponent,
  ],
  imports: [
    GridsterModule,
    BrowserModule,
    AppRoutingModule,
    ChartsModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatSelectModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    MatTableModule,
    MatDialogModule,
    HttpClientModule,
    MatButtonModule,
    PerfectScrollbarModule

  ],
  providers: [
    ApiService,
    {
      provide: PERFECT_SCROLLBAR_CONFIG,
      useValue: DEFAULT_PERFECT_SCROLLBAR_CONFIG
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
