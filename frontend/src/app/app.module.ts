import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DoctorListComponent } from './doctor-list/doctor-list.component';
import { DoctorService } from './service/doctor.service';
import { HttpClientModule } from '@angular/common/http';
import { PatientListComponent } from './patient-list/patient-list.component';
import { ReminderListComponent } from './reminder-list/reminder-list.component';
import { LoginComponent } from './login/login.component';
import { PatientService } from './service/patient.service';
import { ReminderService } from './service/reminder.service';
import { AddReminderComponent } from './add-reminder/add-reminder.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CommonModule } from '@angular/common';
import * as PlotlyJS from 'plotly.js-dist-min';
import { PlotlyModule } from 'angular-plotly.js';
import { PlotlyExampleComponent } from './history-bar/history-bar.component';

PlotlyModule.plotlyjs = PlotlyJS;

@NgModule({
  declarations: [
    AppComponent,
    DoctorListComponent,
    PatientListComponent,
    ReminderListComponent,
    LoginComponent,
    AddReminderComponent,
    PlotlyExampleComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    CommonModule,
    PlotlyModule
  ],
  providers: [DoctorService, PatientService, ReminderService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
