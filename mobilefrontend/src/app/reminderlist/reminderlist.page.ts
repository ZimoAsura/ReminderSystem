import { Component, OnInit } from '@angular/core';
import { Reminder } from '../model/reminder';
import { ReminderService } from '../service/reminder.service';
import { TokenStorageService } from '../auth_services/token-storage.service';

@Component({
  selector: 'app-reminderlist',
  templateUrl: './reminderlist.page.html',
  styleUrls: ['./reminderlist.page.scss'],
})
export class ReminderlistPage implements OnInit {

  reminders: Reminder[];
  constructor(

    private tokenService: TokenStorageService,
    private reminderService: ReminderService) {}

  ionViewWillEnter() {
    this.reminderService.findAll().subscribe(data =>{
      function comp(a, b) {
        return new Date(b.assign_time).getTime() - new Date(a.assign_time).getTime();
    }
      data.sort(comp);
      console.log(data);
      this.reminders = data;
    })
  }

  doRefresh(event) {  
    console.log('Pull Event Triggered!');  
    setTimeout(() => {
      this.reminderService.findAll().subscribe(data =>{
        function comp(a, b) {
          return new Date(b.assign_time).getTime() - new Date(a.assign_time).getTime();
      }
        data.sort(comp);
        console.log(data);
        this.reminders = data;
      })
      event.target.complete();
    }, 1500); 
  }

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

  logout(): void {
    this.tokenService.signOut();
    window.location.href = 'home';
  }

}
