import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ObservationsMapComponent } from './observations-map.component';

describe('ObservationsMapComponent', () => {
  let component: ObservationsMapComponent;
  let fixture: ComponentFixture<ObservationsMapComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ObservationsMapComponent]
    });
    fixture = TestBed.createComponent(ObservationsMapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
