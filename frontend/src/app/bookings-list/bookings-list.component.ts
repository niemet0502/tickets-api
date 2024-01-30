import { NgFor } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-bookings-list',
  standalone: true,
  imports: [NgFor, RouterModule],
  templateUrl: './bookings-list.component.html',
  styleUrl: './bookings-list.component.css',
})
export class BookingsListComponent {
  fetchedData: any[] = []; // Array to store fetched data

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchData();
  }

  fetchData() {
    // Make your API call here
    this.http.get<any[]>('http://localhost:7004/bookings').subscribe({
      next: (data) => {
        this.fetchedData = data; // Assign fetched data to array
      }, // Handle the response data
    });
  }
}
