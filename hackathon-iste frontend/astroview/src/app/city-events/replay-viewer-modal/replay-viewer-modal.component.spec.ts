import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReplayViewerModalComponent } from './replay-viewer-modal.component';

describe('ReplayViewerModalComponent', () => {
  let component: ReplayViewerModalComponent;
  let fixture: ComponentFixture<ReplayViewerModalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ReplayViewerModalComponent]
    });
    fixture = TestBed.createComponent(ReplayViewerModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
