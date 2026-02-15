import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-observation-card',
  templateUrl: './observation-card.component.html',
  styleUrls: ['./observation-card.component.css']
})
export class ObservationCardComponent implements OnInit {

  @Input() data: any;

  constructor(private http: HttpClient) {}

  defaultImage =
    'https://images.unsplash.com/photo-1444703686981-a3abbc4d4fe3?q=80&w=600&auto=format&fit=crop';

  get imageUrl(): string {
    if (this.data?.photoUrl?.trim()) {
      return this.data.photoUrl;
    }
    return this.defaultImage;
  }

  // 👁️ View count increment — card load hone par
  ngOnInit(): void {
    if (!this.data?.id) return;

    this.http.post(
      `http://localhost:8088/api/observations/${this.data.id}/view`,
      {}
    ).subscribe();

    // Optimistic UI
    this.data.viewCount = (this.data.viewCount || 0) + 1;
  }

  // 👍 Upvote
  upvote(): void {
    const payload = {
      observationId: this.data.id,
      userId: 1, // later auth/JWT se
      voteType: 'UPVOTE'
    };

    this.http.post(
      'http://localhost:8088/api/votes',
      payload,
      { responseType: 'text' }
    ).subscribe(() => {
      this.data.upvotes = (this.data.upvotes || 0) + 1;
    });
  }
}
