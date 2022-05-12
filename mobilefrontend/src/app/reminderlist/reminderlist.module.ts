import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ReminderlistPageRoutingModule } from './reminderlist-routing.module';

import { ReminderlistPage } from './reminderlist.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ReminderlistPageRoutingModule
  ],
  declarations: [ReminderlistPage]
})
export class ReminderlistPageModule {}
