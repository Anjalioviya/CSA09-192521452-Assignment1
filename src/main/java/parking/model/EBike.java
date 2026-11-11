package parking.model;

import java.time.LocalDateTime;

/** E-bikes use a flat parking fee per visit, independent of duration. */
public class EBike extends Vehicle {
    public static final double FLAT_FEE = 20.0;

    public EBike(String regNo) { super(regNo, VehicleCategory.E_BIKE); }

    @Override public double calculateParkingFee(LocalDateTime in, LocalDateTime out) {
        if (in == null || out == null || out.isBefore(in))
            throw new IllegalArgumentException("Invalid parking time interval.");
        return FLAT_FEE;
    }
}
