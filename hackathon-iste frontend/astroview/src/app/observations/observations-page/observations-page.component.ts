import { Component } from '@angular/core';

@Component({
  selector: 'app-observations-page',
  templateUrl: './observations-page.component.html',
  styleUrls: ['./observations-page.component.css']
})
export class ObservationsPageComponent {
  showAddModal = false;

  openAddModal() {
    this.showAddModal = true;
  }

  closeAddModal() {
    this.showAddModal = false;
  }
}

