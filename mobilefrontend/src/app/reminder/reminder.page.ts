import { Component, OnInit } from '@angular/core';
import { AlertController } from '@ionic/angular';
import { TokenStorageService } from '../auth_services/token-storage.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Reminder } from '../model/reminder';
import { ReminderService } from '../service/reminder.service';

@Component({
  selector: 'app-reminder',
  templateUrl: './reminder.page.html',
  styleUrls: ['./reminder.page.scss'],
})
export class ReminderPage implements OnInit {
  reminder: Reminder = {
    id: null,
    description: null,
    status: null,
    assign_time: null,
    priority: null,
    patient: null,
    duration: null
  };
  description: string;
  private reminder_id: string;
  private deadline: Date;
  constructor(
    public alertController: AlertController,
    private reminderService: ReminderService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.reminder_id = this.route.snapshot.paramMap.get('id');
    this.deadline = new Date(this.route.snapshot.paramMap.get('deadline'));
  }

  showAlert() {
    let cur_time = new Date();
    if (this.reminder.status == 'Finished') {
      this.alertController.create({
        header: 'Notice',
        message: 'The reminder is already finished.',
        buttons: ['OK']
      }).then(res => {
        res.present();
      });
    }
    else if (cur_time < this.deadline) {
      this.alertController.create({
        header: 'Success',
        message: 'The reminder is finish.',
        buttons: ['OK']
      }).then(res => {
        res.present();
      });
      this.reminderService.finishReminder(this.reminder_id).subscribe(data => {
        console.log(data);
      });
    }
    else {
      this.alertController.create({
        header: 'Fail',
        message: 'The reminder is expired.',
        buttons: ['OK']
      }).then(res => {
        res.present();
      });
    }
  }

  

  ngOnInit() {
    this.reminderService.findOne(this.reminder_id).subscribe(data => {
      this.description = data.description;
      this.reminder = data;
      console.log(this.reminder.patient);
    });
  }

}
