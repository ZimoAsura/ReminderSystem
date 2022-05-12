import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Reminder } from 'app/model/reminder';
import { TokenStorageService } from 'app/_services/token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class ReminderService {
  private reminderUrl: string;
  private addReminderUrl: string;
  private historyURL: string;
  private headers: HttpHeaders;

  constructor(private http: HttpClient, private tokenService: TokenStorageService) {
    this.reminderUrl = 'http://localhost:8080/reminders';
    this.addReminderUrl = 'http://localhost:8080/addreminder';
    this.historyURL = 'http://localhost:8080/reminder/history';

    this.headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${this.tokenService.getToken()}`
    })
   }
  
  public findAll(): Observable<Reminder[]> {
    return this.http.get<Reminder[]>(this.reminderUrl, {headers:this.headers});
  }

  public getHistoryReminder(patient_id){
    return this.http.get(this.historyURL+"/"+patient_id, {headers:this.headers})
  }

  public addReminder(patient_id, description, duration, priority): Observable<Reminder> {
    // console.log(this.addReminderUrl+"/"+patient_id);
    let assignTime = new Date()
    let deadline = assignTime
    deadline.setHours(assignTime.getHours() + duration)
    console.log(assignTime, deadline);
    let body =       {
      "description": description,
      "duration": deadline,
      "priority": priority,
      "assign_time": new Date(),
      "status": "Unfinished",
    }
    console.log(body)
    return this.http.post<Reminder>(this.addReminderUrl+"/"+patient_id, 
      {
        "description": description,
        "duration": deadline,
        "priority": priority,
        "assign_time": new Date(),
        "status": "Unfinished",
      }, {headers: this.headers});
  }
}
