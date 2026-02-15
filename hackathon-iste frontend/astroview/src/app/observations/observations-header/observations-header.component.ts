import { Component } from '@angular/core';
import { EventEmitter, Output } from '@angular/core';
@Component({
  selector: 'app-observations-header',
  templateUrl: './observations-header.component.html',
  styleUrls: ['./observations-header.component.css']
})
export class ObservationsHeaderComponent {
  @Output() add = new EventEmitter<void>();
}

