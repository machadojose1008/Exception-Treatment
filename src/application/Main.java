package application;

import model.entities.Reservation;
import model.exceptions.DomainException;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);

        try{

            System.out.print("Room number: ");
            int number = sc.nextInt();
            System.out.print("Check-in date (dd/mm/yyyy): ");
            LocalDate checkIn = LocalDate.parse(sc.next(),fmt);
            System.out.print("Check-out date (dd/mm/yyyy): ");
            LocalDate checkOut = LocalDate.parse(sc.next(),fmt);


            Reservation r = new Reservation(number,checkIn,checkOut);
            System.out.println(r);

            System.out.println();
            System.out.println("Enter data to update the reservation:");
            System.out.print("Check-in date (dd/mm/yyyy): ");
            checkIn = LocalDate.parse(sc.next(),fmt);
            System.out.print("Check-out date (dd/mm/yyyy): ");
            checkOut = LocalDate.parse(sc.next(),fmt);


            r.updateDates(checkIn,checkOut);
            System.out.println("Reservation: "+r);

       }
        catch (DateTimeParseException e){
           System.out.println("Invalid date format");
       }
        catch (DomainException e){
            System.out.println("Error in Reservation: "+e.getMessage());
        }
        catch (RuntimeException e){
            System.out.println("Unexpected error");
        }

        sc.close();
    }
}
