import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookingNewComponent } from './booking-new.component';

describe('BookingNewComponent', () => {
  let component: BookingNewComponent;
  let fixture: ComponentFixture<BookingNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BookingNewComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BookingNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
