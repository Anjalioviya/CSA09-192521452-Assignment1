package parking.app;

import parking.model.*;
import parking.service.ParkingFacility;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.Scanner;

/** Small console demonstration of entry, ticket issue, exit and fee collection. */
public class ParkingApp {
    public static void main(String[] args) {
        ParkingFacility facility = new ParkingFacility(
                new int[]{3, 2, 4},
                Set.of("0:2", "2:3"));

        ParkingUser visitor = new ParkingUser("V001", "Demo Visitor",
                UserType.VISITOR, false, false);

        LocalDateTime entry = LocalDateTime.of(2026, 11, 11, 10, 15);
        LocalDateTime exit = LocalDateTime.of(2026, 11, 11, 12, 20);

        ParkingTicket ticket = facility.vehicleEntry(
                new ElectricCar("TN01AB1234"), visitor, entry);

        System.out.println("Ticket number: " + ticket.getTicketNumber());
        System.out.println("Allocated slot: floor " + ticket.getSlot().getFloorNumber()
                + ", slot " + ticket.getSlot().getSlotNumber());
        double fee = facility.vehicleExit("TN01AB1234", exit, 5.0);
        System.out.printf("Total fee: Rs %.2f%n", fee);
        System.out.println("Available slots: " + facility.getAvailableSlotCount()
                + "/" + facility.getTotalSlotCount());
    }
}
