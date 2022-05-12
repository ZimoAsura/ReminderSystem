import { Component, OnInit } from '@angular/core';
import { Doctor } from 'app/model/doctor';
import { DoctorService } from 'app/service/doctor.service';
@Component({
  selector: 'app-doctor-list',
  templateUrl: './doctor-list.component.html',
  styleUrls: ['./doctor-list.component.css']
})
export class DoctorListComponent implements OnInit {

  doctors: Doctor[];

  constructor(private doctorService: DoctorService) { }

  ngOnInit() {
    this.doctorService.findAll().subscribe(data =>{
      this.doctors = data;
    });
  }

}
