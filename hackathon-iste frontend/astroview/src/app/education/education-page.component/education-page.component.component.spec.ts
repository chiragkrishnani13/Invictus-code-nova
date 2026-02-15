import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EducationPageComponentComponent } from './education-page.component.component';

describe('EducationPageComponentComponent', () => {
  let component: EducationPageComponentComponent;
  let fixture: ComponentFixture<EducationPageComponentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EducationPageComponentComponent]
    });
    fixture = TestBed.createComponent(EducationPageComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
