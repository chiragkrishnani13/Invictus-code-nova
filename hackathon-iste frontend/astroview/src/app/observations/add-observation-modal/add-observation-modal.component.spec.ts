import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddObservationModalComponent } from './add-observation-modal.component';

describe('AddObservationModalComponent', () => {
  let component: AddObservationModalComponent;
  let fixture: ComponentFixture<AddObservationModalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddObservationModalComponent]
    });
    fixture = TestBed.createComponent(AddObservationModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
