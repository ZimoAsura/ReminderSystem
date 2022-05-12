import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoryBarComponent } from './history-bar.component';

describe('HistoryBarComponent', () => {
  let component: HistoryBarComponent;
  let fixture: ComponentFixture<HistoryBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HistoryBarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HistoryBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
