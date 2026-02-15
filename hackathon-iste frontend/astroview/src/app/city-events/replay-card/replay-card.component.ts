import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-replay-card',
  templateUrl: './replay-card.component.html',
  styleUrls: ['./replay-card.component.css']
})
export class ReplayCardComponent {
  @Output() viewReplay = new EventEmitter<void>();

  onViewReplay() {
    this.viewReplay.emit();
  }
}
