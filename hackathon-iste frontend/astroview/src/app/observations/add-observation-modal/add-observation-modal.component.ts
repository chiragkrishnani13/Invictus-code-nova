import { Component, EventEmitter, Output } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-add-observation-modal',
  templateUrl: './add-observation-modal.component.html',
  styleUrls: ['./add-observation-modal.component.css']
})
export class AddObservationModalComponent {

  @Output() close = new EventEmitter<void>();

  // 🔹 Form fields (HTML se bind ho rahe the – missing the)
  title: string = '';
  description: string = '';
  eventType: string = '';
  city: string = '';

  // 🔹 Image handling
  selectedFile: File | null = null;
  selectedFileName: string | null = null;

  constructor(private http: HttpClient) {}

  // 📸 File select
  onFileSelected(event: any) {
    const file = event.target.files[0];
    if (file) {
      this.selectedFile = file;
      this.selectedFileName = file.name;
    }
  }

  // 🚀 Submit form
  submitObservation() {
  if (!this.selectedFile) {
    alert('Please upload an image');
    return;
  }

  const formData = new FormData();

  const payload = {
    title: this.title,
    description: this.description,
    eventType: this.eventType,
    locationName: this.city,

    // ✅ TEMPORARY GENERATED VALUES
    latitude: 19.0760,      // Mumbai
    longitude: 72.8777      // Mumbai
  };

  formData.append(
    'data',
    new Blob([JSON.stringify(payload)], {
      type: 'application/json'
    })
  );

  formData.append('image', this.selectedFile);

  this.http.post(
    'http://localhost:8088/api/observations',
    formData
  ).subscribe({
    next: () => {
      alert('Observation added successfully 🌌');
      this.close.emit();
    },
    error: (err) => {
      console.error(err);
      alert('Failed to submit observation');
    }
  });
}

}
