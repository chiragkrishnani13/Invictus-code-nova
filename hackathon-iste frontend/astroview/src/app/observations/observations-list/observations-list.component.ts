import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-observations-list',
  templateUrl: './observations-list.component.html',
  styleUrls: ['./observations-list.component.css']
})
export class ObservationsListComponent implements OnInit {

  observations: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get<any[]>('http://localhost:8088/api/observations')
      .subscribe({
        next: (res) => {
          console.log('OBSERVATIONS FROM API:', res);
          this.observations = res;
        },
        error: (err) => console.error(err)
      });
  }
}
