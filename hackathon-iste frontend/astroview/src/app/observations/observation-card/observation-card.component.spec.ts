import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ObservationCardComponent } from './observation-card.component';

describe('ObservationCardComponent', () => {
  let component: ObservationCardComponent;
  let fixture: ComponentFixture<ObservationCardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ObservationCardComponent]
    });
    fixture = TestBed.createComponent(ObservationCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
