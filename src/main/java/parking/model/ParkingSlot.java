package parking.model;

public class ParkingSlot {
    private final int floorNumber;
    private final int slotNumber;
    private final boolean chargingEnabled;
    private Vehicle vehicle;

    public ParkingSlot(int floorNumber, int slotNumber, boolean chargingEnabled) {
        this.floorNumber = floorNumber;
        this.slotNumber = slotNumber;
        this.chargingEnabled = chargingEnabled;
    }

    public int getFloorNumber() { return floorNumber; }
    public int getSlotNumber() { return slotNumber; }
    public boolean isChargingEnabled() { return chargingEnabled; }
    public boolean isFree() { return vehicle == null; }
    public Vehicle getVehicle() { return vehicle; }

    public void park(Vehicle v) {
        if (!isFree()) throw new IllegalStateException("Slot is occupied.");
        vehicle = v;
    }

    public void release() {
        if (isFree()) throw new IllegalStateException("Slot is already free.");
        vehicle = null;
    }
}
