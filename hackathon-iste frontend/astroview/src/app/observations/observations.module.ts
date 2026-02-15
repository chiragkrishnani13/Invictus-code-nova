import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ObservationsPageComponent } from './observations-page/observations-page.component';
import { ObservationsHeaderComponent } from './observations-header/observations-header.component';
import { ObservationsFiltersComponent } from './observations-filters/observations-filters.component';
import { ObservationsMapComponent } from './observations-map/observations-map.component';
import { ObservationsListComponent } from './observations-list/observations-list.component';
import { ObservationCardComponent } from './observation-card/observation-card.component';
import { AddObservationModalComponent } from './add-observation-modal/add-observation-modal.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    ObservationsPageComponent,
    ObservationsHeaderComponent,
    ObservationsFiltersComponent,
    ObservationsMapComponent,
    ObservationsListComponent,
    ObservationCardComponent,
    AddObservationModalComponent
  ],
  imports: [
    CommonModule,
    FormsModule
  ],
  exports: [
    ObservationsPageComponent
  ]
})
export class ObservationsModule { }
