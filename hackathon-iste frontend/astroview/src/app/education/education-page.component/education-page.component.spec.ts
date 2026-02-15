import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EducationPageComponent } from './education-page.component';

describe('', () => {
  let component: EducationPageComponent;
  let fixture: ComponentFixture<EducationPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EducationPageComponent]
    });
    fixture = TestBed.createComponent(EducationPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
