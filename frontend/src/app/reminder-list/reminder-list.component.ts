import { Component, OnInit } from '@angular/core';
import { Reminder } from 'app/model/reminder';
import { ReminderService } from 'app/service/reminder.service';

@Component({
  selector: 'app-reminder-list',
  templateUrl: './reminder-list.component.html',
  styleUrls: ['./reminder-list.component.css']
})
export class ReminderListComponent implements OnInit {

  reminders: Reminder[];
  
  constructor(private reminderService: ReminderService) { }

  ngOnInit(){
    this.reminderService.findAll().subscribe(data => { 
      this.reminders = data
    })
  }

}
