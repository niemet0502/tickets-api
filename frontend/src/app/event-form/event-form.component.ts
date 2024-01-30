import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-event-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './event-form.component.html',
  styleUrl: './event-form.component.css',
})
export class EventFormComponent {
  checkoutForm = this.formBuilder.group({
    name: 'Super bowl',
    address: 'Mineapolis',
    quantityTotal: 4500,
    price: 2000,
  });

  constructor(private formBuilder: FormBuilder, private http: HttpClient) {}

  onSubmit(): void {
    console.log(this.checkoutForm.value);

    this.http
      .post(
        'http://localhost:7003/events',
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
