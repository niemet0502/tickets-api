import { NgFor } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-events-list',
  standalone: true,
  imports: [NgFor, RouterModule],
  templateUrl: './events-list.component.html',
  styleUrl: './events-list.component.css',
})
export class EventsListComponent {
  fetchedData: any[] = []; // Array to store fetched data

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchData();
  }

  fetchData() {
    // Make your API call here
    this.http.get<any[]>('http://localhost:7003/events').subscribe({
      next: (data) => {
        this.fetchedData = data; // Assign fetched data to array
      }, // Handle the response data
    });
  }
}
