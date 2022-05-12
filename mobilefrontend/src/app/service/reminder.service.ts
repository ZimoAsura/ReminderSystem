import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Reminder } from '../model/reminder';
import { TokenStorageService } from '../auth_services/token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class ReminderService {

  private patientreminderUrl: string;
  private reminderUrl: string;
  private updatereminderUrl: string;
  private headers: HttpHeaders;

  constructor(private http: HttpClient, private tokenService: TokenStorageService) {
    this.patientreminderUrl = 'http://localhost:8080/patientreminders';
    this.reminderUrl = 'http://localhost:8080/reminders';
    this.updatereminderUrl = 'http://localhost:8080/finishreminder';
    this.headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${this.tokenService.getToken()}`
    })
  }

  public findAll(): Observable<Reminder[]> {
    return this.http.get<Reminder[]>(this.patientreminderUrl, {headers: this.headers});
  }

  public findOne(reminder_id): Observable<Reminder>{
    return this.http.get<Reminder>(this.reminderUrl+'/'+reminder_id, {headers: this.headers});
  }

  public finishReminder(reminder_id): Observable<Reminder> {
    return this.http.get<Reminder>( this.updatereminderUrl+'/'+reminder_id, {headers: this.headers});
  }
}
