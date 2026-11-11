package parking.model;

import java.time.Duration;
import java.time.LocalDateTime;

/** Double the car rate for each billable hour overlapping 09:00-17:00. */
public class DeliveryVan extends Vehicle {
    private static final double CAR_RATE = 30.0;
    private static final double DAY_RATE = CAR_RATE * 2.0;

    public DeliveryVan(String regNo) { super(regNo, VehicleCategory.DELIVERY_VAN); }

    @Override public double calculateParkingFee(LocalDateTime entry, LocalDateTime exit) {
        if (entry == null || exit == null || exit.isBefore(entry))
            throw new IllegalArgumentException("Invalid parking time interval.");
        long totalMinutes = Duration.between(entry, exit).toMinutes();
        if (totalMinutes == 0) return 0.0;

        // Charge each started hour; classify an hour by its start time.
        long hours = (totalMinutes + 59) / 60;
        double fee = 0.0;
        LocalDateTime cursor = entry;
        for (long i = 0; i < hours; i++) {
            LocalDateTime hourStart = cursor;
            int h = hourStart.getHour();
            fee += (h >= 9 && h < 17) ? DAY_RATE : CAR_RATE;
            cursor = cursor.plusHours(1);
        }
        return fee;
    }
}
