import { Component } from '@angular/core';
import { TokenStorageService } from './_services/token-storage.service';

@Component({
  selector: 'app-root', // html selector used to bind the component to the html template file
  templateUrl: './app.component.html', // the html template file associated with the component
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title: string;
  constructor(private tokenService: TokenStorageService){
    this.title = 'Reminder System (Doctor Dashboard)';
  }

  logout(): void {
    this.tokenService.signOut();
    window.location.reload();
  }

  
}
