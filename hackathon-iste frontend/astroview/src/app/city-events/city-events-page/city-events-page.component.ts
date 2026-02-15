import { Component } from '@angular/core';

@Component({
  selector: 'app-city-events-page',
  templateUrl: './city-events-page.component.html',
  styleUrls: ['./city-events-page.component.css']
})
export class CityEventsPageComponent {

  isModalOpen = false;

modalData = {
  title: 'Lunar Eclipse',
  city: 'Mumbai',
  date: 'Feb 10, 2026',
  description: 'A total lunar eclipse visible from India.',
  images: [
    'https://images.unsplash.com/photo-1444703686981-a3abbc4d4fe3?q=80&w=1200',
    'https://images.unsplash.com/photo-1462331940025-496dfbfc7564?q=80&w=1200'
  ]
};

openReplayModal() {
  this.isModalOpen = true;
}

closeReplayModal() {
  this.isModalOpen = false;
}

}
