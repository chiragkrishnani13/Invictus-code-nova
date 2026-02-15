import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CityEventsPageComponent } from './city-events-page.component';

describe('CityEventsPageComponent', () => {
  let component: CityEventsPageComponent;
  let fixture: ComponentFixture<CityEventsPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CityEventsPageComponent]
    });
    fixture = TestBed.createComponent(CityEventsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
