import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddReminderComponent } from './add-reminder/add-reminder.component';
import { DoctorListComponent } from './doctor-list/doctor-list.component';
import { PlotlyExampleComponent } from './history-bar/history-bar.component';
import { LoginComponent } from './login/login.component';
import { PatientListComponent } from './patient-list/patient-list.component';
import { ReminderListComponent } from './reminder-list/reminder-list.component';


const routes: Routes = [
  {path: 'doctors', component: DoctorListComponent},
  {path: 'patients', component: PatientListComponent},
  {path: 'reminders', component: ReminderListComponent},
  {path: 'login', component: LoginComponent},
  {path: "addreminder/:id", component: AddReminderComponent},
  {path: "reminder/history/:id", component: PlotlyExampleComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes),],
  exports: [RouterModule]
})
export class AppRoutingModule { }
