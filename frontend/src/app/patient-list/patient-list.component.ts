import { Component, OnInit } from '@angular/core';
import { Patient } from 'app/model/patient';
import { PatientService } from 'app/service/patient.service';
import { TokenStorageService } from 'app/_services/token-storage.service';

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css']
})
export class PatientListComponent implements OnInit {

  patients: Patient[];

  constructor(private patientService: PatientService) { }

  ngOnInit() {
    this.patientService.findAll().subscribe(data =>{
      this.patients = data
    })
  }

}
