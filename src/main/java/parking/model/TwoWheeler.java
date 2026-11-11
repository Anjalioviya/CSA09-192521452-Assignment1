package parking.model;

import java.time.LocalDateTime;

public class TwoWheeler extends Vehicle {
    public static final double RATE_PER_HOUR = 10.0;

    public TwoWheeler(String regNo) {
        super(regNo, VehicleCategory.TWO_WHEELER);
    }

    @Override public double calculateParkingFee(LocalDateTime in, LocalDateTime out) {
        return billableHours(in, out) * RATE_PER_HOUR;
    }
}
