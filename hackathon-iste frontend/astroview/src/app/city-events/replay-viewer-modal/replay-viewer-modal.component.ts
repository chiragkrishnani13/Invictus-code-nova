import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-replay-viewer-modal',
  templateUrl: './replay-viewer-modal.component.html',
  styleUrls: ['./replay-viewer-modal.component.css']
})
export class ReplayViewerModalComponent {
  @Input() open: boolean = false;
  @Input() title: string = '';
  @Input() city: string = '';
  @Input() date: string = '';
  @Input() description: string = '';
  @Input() images: string[] = [];

  @Output() closed = new EventEmitter<void>();

  currentIndex: number = 0;

  close() {
    this.closed.emit();   // parent will close modal
  }

  next() {
    if (!this.images.length) return;
    this.currentIndex = (this.currentIndex + 1) % this.images.length;
  }

  prev() {
    if (!this.images.length) return;
    this.currentIndex =
      (this.currentIndex - 1 + this.images.length) % this.images.length;
  }
}
