import { Routes } from '@angular/router';
import { BookingNewComponent } from './booking-new/booking-new.component';
import { BookingsListComponent } from './bookings-list/bookings-list.component';
import { EventFormComponent } from './event-form/event-form.component';
import { EventsListComponent } from './events-list/events-list.component';

export const routes: Routes = [
  { path: 'events', title: 'Events', component: EventsListComponent },
  { path: 'event-new', title: 'New Event', component: EventFormComponent },
  { path: 'bookings', title: 'Bookings', component: BookingsListComponent },
  {
    path: 'booking-new',
    title: 'New Booking',
    component: BookingNewComponent,
  },
  { path: '', redirectTo: '/events', pathMatch: 'full' }, // redirect to
];
