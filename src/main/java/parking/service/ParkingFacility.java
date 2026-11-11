package parking.service;

import parking.model.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Multi-floor facility. Jagged arrays support different slot counts per floor.
 * EVs are allocated a charging slot first, then a normal slot if none is free.
 */
public class ParkingFacility {
    private final ParkingSlot[][] floors;
    private final Map<String, ParkingTicket> activeTickets = new HashMap<>();

    public ParkingFacility(int[] slotsPerFloor, Set<String> chargingPositions) {
        if (slotsPerFloor == null || slotsPerFloor.length == 0)
            throw new IllegalArgumentException("At least one floor is required.");
        floors = new ParkingSlot[slotsPerFloor.length][];
        Set<String> chargers = chargingPositions == null ? Set.of() : chargingPositions;
        for (int f = 0; f < slotsPerFloor.length; f++) {
            if (slotsPerFloor[f] < 1) throw new IllegalArgumentException("Each floor needs a slot.");
            floors[f] = new ParkingSlot[slotsPerFloor[f]];
            for (int s = 0; s < slotsPerFloor[f]; s++) {
                floors[f][s] = new ParkingSlot(f, s, chargers.contains(f + ":" + s));
            }
        }
    }

    public ParkingFacility(int[] slotsPerFloor) {
        this(slotsPerFloor, Set.of());
    }

    public synchronized ParkingTicket vehicleEntry(Vehicle vehicle, ParkingUser user,
                                                     LocalDateTime entryTime) {
        Objects.requireNonNull(vehicle);
        Objects.requireNonNull(user);
        if (activeTickets.containsKey(vehicle.getRegistrationNumber()))
            throw new IllegalArgumentException("This vehicle already has an active ticket.");

        ParkingSlot slot = findSlot(vehicle instanceof ElectricCar);
        if (slot == null) throw new IllegalStateException("No suitable parking slot is available.");

        slot.park(vehicle);
        ParkingTicket ticket = new ParkingTicket(vehicle, user, slot, entryTime);
        activeTickets.put(vehicle.getRegistrationNumber(), ticket);
        return ticket;
    }

    private ParkingSlot findSlot(boolean preferCharging) {
        if (preferCharging) {
            for (ParkingSlot[] floor : floors)
                for (ParkingSlot slot : floor)
                    if (slot.isFree() && slot.isChargingEnabled()) return slot;
        }
        for (ParkingSlot[] floor : floors)
            for (ParkingSlot slot : floor)
                if (slot.isFree() && !slot.isChargingEnabled()) return slot;
        // If every remaining free slot is a charger, allow an EV to use it.
        if (preferCharging) {
            for (ParkingSlot[] floor : floors)
                for (ParkingSlot slot : floor)
                    if (slot.isFree()) return slot;
        }
        return null;
    }

    public synchronized double vehicleExit(String registrationNumber,
                                           LocalDateTime exitTime, double kWh) {
        ParkingTicket ticket = activeTickets.get(registrationNumber.toUpperCase());
        if (ticket == null) throw new IllegalArgumentException("No active ticket for vehicle.");
        Vehicle vehicle = ticket.getVehicle();
        double fee = ticket.getUser().hasFreePass()
                ? 0.0
                : vehicle.calculateParkingFee(ticket.getEntryTime(), exitTime);
        if (vehicle instanceof Chargeable chargeable) fee += chargeable.chargingFee(kWh);
        ticket.getSlot().release();
        activeTickets.remove(vehicle.getRegistrationNumber());
        return fee;
    }

    public synchronized ParkingTicket findActiveTicket(String registrationNumber) {
        return activeTickets.get(registrationNumber.toUpperCase());
    }

    public int getFloorCount() { return floors.length; }
    public int getActiveVehicleCount() { return activeTickets.size(); }

    public int getTotalSlotCount() {
        int total = 0;
        for (ParkingSlot[] floor : floors) total += floor.length;
        return total;
    }

    public int getAvailableSlotCount() {
        int free = 0;
        for (ParkingSlot[] floor : floors)
            for (ParkingSlot slot : floor) if (slot.isFree()) free++;
        return free;
    }
}
