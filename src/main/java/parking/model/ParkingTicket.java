package parking.model;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class ParkingTicket {
    private static final AtomicLong NEXT_ID = new AtomicLong(1000);

    private final long ticketNumber;
    private final Vehicle vehicle;
    private final ParkingUser user;
    private final ParkingSlot slot;
    private final LocalDateTime entryTime;

    public ParkingTicket(Vehicle vehicle, ParkingUser user, ParkingSlot slot,
                         LocalDateTime entryTime) {
        this.ticketNumber = NEXT_ID.incrementAndGet();
        this.vehicle = vehicle;
        this.user = user;
        this.slot = slot;
        this.entryTime = entryTime;
    }

    public long getTicketNumber() { return ticketNumber; }
    public Vehicle getVehicle() { return vehicle; }
    public ParkingUser getUser() { return user; }
    public ParkingSlot getSlot() { return slot; }
    public LocalDateTime getEntryTime() { return entryTime; }
}
