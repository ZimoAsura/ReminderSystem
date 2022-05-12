import { Component, OnInit } from '@angular/core';
import { Reminder } from '../model/reminder';
import { ReminderService } from '../service/reminder.service';

@Component({
  selector: 'app-reminderlist',
  templateUrl: './reminderlist.page.html',
  styleUrls: ['./reminderlist.page.scss'],
})
export class ReminderlistPage implements OnInit {

  reminders: Reminder[];
  constructor(private reminderService: ReminderService) {}

  ngOnInit() {
    this.reminderService.findAll().subscribe(data =>{
      function comp(a, b) {
        return new Date(b.assign_time).getTime() - new Date(a.assign_time).getTime();
    }
      data.sort(comp);
      console.log(data);
      this.reminders = data;
    })
  }

}
