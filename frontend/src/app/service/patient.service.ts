import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Patient } from 'app/model/patient';
import { TokenStorageService } from 'app/_services/token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class PatientService {
  private patientUrl: string;

  constructor(private http: HttpClient, private tokenService: TokenStorageService) {
    this.patientUrl = 'http://localhost:8080/doctors/patients';
   }

   public findAll(): Observable<Patient[]> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${this.tokenService.getToken()}`
    })
     return this.http.get<Patient[]>(this.patientUrl, {headers: headers});
   }
}
