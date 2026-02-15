import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ObservationsHeaderComponent } from './observations-header.component';

describe('ObservationsHeaderComponent', () => {
  let component: ObservationsHeaderComponent;
  let fixture: ComponentFixture<ObservationsHeaderComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ObservationsHeaderComponent]
    });
    fixture = TestBed.createComponent(ObservationsHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
