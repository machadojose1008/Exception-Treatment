package model.entities;

import model.exceptions.DomainException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Reservation {
    private Integer roomNumber;
    private LocalDate checkin;
    private LocalDate checkout;

    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Reservation(){};

    public Reservation(Integer roomNumber, LocalDate checkin, LocalDate checkout) {
        if(!checkout.isAfter(checkin)){
            throw new DomainException("Check-out date must be after check-in date");
        }
        this.roomNumber = roomNumber;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDate getCheckin() {
        return checkin;
    }

    public LocalDate getCheckout() {
        return checkout;
    }

    public int duration(){
        return (int)ChronoUnit.DAYS.between(checkin,checkout);
    }

    public void updateDates(LocalDate checkin, LocalDate checkout) {
        LocalDate now = LocalDate.now();
        if(checkin.isBefore(now) || checkout.isBefore(now)){
            throw new DomainException("Reservation dates for update must be future dates") ;
        } if(!checkout.isAfter(checkin)){
            throw new DomainException("Check-out date must be after check-in date");
        }
        this.checkin = checkin;
        this.checkout = checkout;

    }

    @Override
    public String toString(){
        return "Room: "
                + roomNumber
                + ", check-in: "
                + fmt.format(checkin)
                +", check-out: "
                + fmt.format(checkout)
                + ", "
                + duration()
                + " nights";
    }

}
