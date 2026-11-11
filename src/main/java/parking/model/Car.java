package parking.model;

import java.time.LocalDateTime;

public class Car extends Vehicle {
    public static final double RATE_PER_HOUR = 30.0;

    public Car(String regNo) {
        super(regNo, VehicleCategory.CAR);
    }

    @Override public double calculateParkingFee(LocalDateTime in, LocalDateTime out) {
        return billableHours(in, out) * RATE_PER_HOUR;
    }
}
