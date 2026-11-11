package parking.model;

import java.time.LocalDateTime;

public class ElectricCar extends Car implements Chargeable {
    public static final double CHARGING_RATE_PER_KWH = 12.0;

    public ElectricCar(String regNo) { super(regNo); }

    @Override public double calculateParkingFee(LocalDateTime in, LocalDateTime out) {
        return billableHours(in, out) * RATE_PER_HOUR;
    }

    @Override public double chargingFee(double kWh) {
        if (kWh < 0) throw new IllegalArgumentException("kWh cannot be negative.");
        return kWh * CHARGING_RATE_PER_KWH;
    }
}
