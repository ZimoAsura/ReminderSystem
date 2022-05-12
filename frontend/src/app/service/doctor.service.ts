import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Doctor } from 'app/model/doctor';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class DoctorService {
  private doctorUrl: string;

  constructor(private http: HttpClient) {
    this.doctorUrl = 'http://localhost:8080/doctors';
   }

  public findAll(): Observable<Doctor[]> {
    return this.http.get<Doctor[]>(this.doctorUrl);
  }
}
