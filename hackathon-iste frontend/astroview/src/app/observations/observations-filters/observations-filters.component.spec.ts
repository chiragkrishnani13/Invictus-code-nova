import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ObservationsFiltersComponent } from './observations-filters.component';

describe('ObservationsFiltersComponent', () => {
  let component: ObservationsFiltersComponent;
  let fixture: ComponentFixture<ObservationsFiltersComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ObservationsFiltersComponent]
    });
    fixture = TestBed.createComponent(ObservationsFiltersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
