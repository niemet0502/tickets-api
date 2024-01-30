import { Routes } from '@angular/router';
import { BookingsListComponent } from './bookings-list/bookings-list.component';
import { EventsListComponent } from './events-list/events-list.component';

export const routes: Routes = [
  { path: 'events', title: 'Events', component: EventsListComponent },
  { path: 'bookings', title: 'Bookings', component: BookingsListComponent },
  { path: '', redirectTo: '/events', pathMatch: 'full' }, // redirect to
];
