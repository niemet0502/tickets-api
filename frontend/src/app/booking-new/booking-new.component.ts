import { NgFor } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormControl, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-booking-new',
  standalone: true,
  imports: [ReactiveFormsModule, NgFor],
  templateUrl: './booking-new.component.html',
  styleUrl: './booking-new.component.css',
})
export class BookingNewComponent {
  events: any[] = []; // Array to store fetched data
  checkoutForm = this.formBuilder.group({
    username: 'Lyam Isiaha',
    useremail: 'lyam@gmail.com',
    total: 0,
    eventId: new FormControl(this.events[0]),
  });

  constructor(private formBuilder: FormBuilder, private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchData();
  }

  fetchData() {
    // Make your API call here
    this.http.get<any[]>('http://localhost:7003/events').subscribe({
      next: (data) => {
        this.events = data; // Assign fetched data to array
      }, // Handle the response data
    });
  }

  onSubmit(): void {
    console.log(this.checkoutForm.value);

    this.http
      .post(
        'http://localhost:7004/bookings',
        // JSON.stringify(this.checkoutForm.value)
        { ...this.checkoutForm.value, date: new Date() }
      )
      .subscribe({
        next: (data) => {
          console.log(data); // Handle API response
        },
        error: (error) => {
          console.error('There was an error!', error);
        },
      });
    this.checkoutForm.reset();
  }
}
