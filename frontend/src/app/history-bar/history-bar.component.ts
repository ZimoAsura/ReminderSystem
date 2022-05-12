import { Component } from '@angular/core';
import { ReminderService } from 'app/service/reminder.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
    selector: 'plotly-example',
    template: '<plotly-plot [data]="graph.data" [layout]="graph.layout"></plotly-plot>',
})
export class PlotlyExampleComponent {
  public graph;
  private patientId: string;

  constructor(
    private reminderService: ReminderService,
    private _router: Router,
    private _route:ActivatedRoute) {
      this.patientId = this._route.snapshot.paramMap.get('id')
  }

  ngOnInit() {
    this.reminderService.getHistoryReminder(this.patientId).subscribe(response => {
      this.graph = {
        data: [
          { x: response["x"], y: response["y"], type: 'bar' },
      ],
      layout: {title: 'Patient ' + this.patientId + " Expired Reminders in 7 days"}
      };
    })
  }
}
