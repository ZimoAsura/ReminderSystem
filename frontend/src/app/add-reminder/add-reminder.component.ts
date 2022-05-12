import { Component, OnInit } from '@angular/core';
import { ReminderService } from 'app/service/reminder.service';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorageService } from 'app/_services/token-storage.service';

@Component({
  selector: 'app-add-reminder',
  templateUrl: './add-reminder.component.html',
  styleUrls: ['./add-reminder.component.css']
})
export class AddReminderComponent implements OnInit {
  form: any = {
    description: null,
    duration: null,
    priority: null
  };
  errorMessage = '';
  private patientId: string;

  constructor(
    private reminderService: ReminderService,
    private _router: Router,
    private _route:ActivatedRoute) {
    this.patientId = this._route.snapshot.paramMap.get('id')
  }

  onSubmit(): void {
    const { description, duration, priority } = this.form;
    console.log(description, duration, priority)

    this.reminderService.addReminder(this.patientId, description, duration, priority).subscribe(
      data => {
        this._router.navigate(['reminders']);
      },
      err => {
        this.errorMessage = err.error.message;
      }
    );
  }

  ngOnInit(): void {

  }

}
