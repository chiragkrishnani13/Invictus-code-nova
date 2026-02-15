import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ObservationsPageComponent } from './observations-page.component';

describe('ObservationsPageComponent', () => {
  let component: ObservationsPageComponent;
  let fixture: ComponentFixture<ObservationsPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ObservationsPageComponent]
    });
    fixture = TestBed.createComponent(ObservationsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
