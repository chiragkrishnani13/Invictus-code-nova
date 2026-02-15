import { Component, Input, OnChanges } from '@angular/core';

@Component({
  selector: 'app-observations-map',
  templateUrl: './observations-map.component.html',
  styleUrls: ['./observations-map.component.css']
})
export class ObservationsMapComponent implements OnChanges {

  @Input() observations: any[] = [];

  pins: any[] = [];

  ngOnChanges(): void {
    this.pins = this.observations
      .filter(o => o.latitude && o.longitude)
      .map(o => ({
        top: this.latToTop(o.latitude),
        left: this.lngToLeft(o.longitude),
        type: o.eventType.toLowerCase(), // meteor / satellite / planet
        title: `${o.eventType} - ${o.locationName}`
      }));
  }

  // 🌍 latitude → top %
  private latToTop(lat: number): string {
    // lat: 90 → 0%, -90 → 100%
    return `${((90 - lat) / 180) * 100}%`;
  }

  // 🌍 longitude → left %
  private lngToLeft(lng: number): string {
    // lng: -180 → 0%, 180 → 100%
    return `${((lng + 180) / 360) * 100}%`;
  }
}
