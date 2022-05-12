import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ReminderlistPage } from './reminderlist.page';

const routes: Routes = [
  {
    path: '',
    component: ReminderlistPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ReminderlistPageRoutingModule {}
