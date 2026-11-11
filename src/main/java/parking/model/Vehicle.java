package parking.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

/** Common state and behaviour for all parkable vehicles. */
public abstract class Vehicle {
    private final String registrationNumber;
    private final VehicleCategory category;

    protected Vehicle(String registrationNumber, VehicleCategory category) {
        if (registrationNumber == null || registrationNumber.isBlank()) {
            throw new IllegalArgumentException("Registration number cannot be blank.");
        }
        this.registrationNumber = registrationNumber.trim().toUpperCase();
        this.category = Objects.requireNonNull(category, "category");
    }

    public String getRegistrationNumber() { return registrationNumber; }
    public VehicleCategory getCategory() { return category; }

    /** Pricing is specialised by each vehicle subtype. */
    public abstract double calculateParkingFee(LocalDateTime entry, LocalDateTime exit);

    protected static long billableHours(LocalDateTime entry, LocalDateTime exit) {
        if (entry == null || exit == null || exit.isBefore(entry)) {
            throw new IllegalArgumentException("Invalid parking time interval.");
        }
        long minutes = Duration.between(entry, exit).toMinutes();
        return (minutes + 59) / 60; // ceiling division; zero minutes remains zero
    }

    @Override public String toString() {
        return getClass().getSimpleName() + "[" + registrationNumber + "]";
    }

    @Override public boolean equals(Object other) {
        return other instanceof Vehicle v
                && registrationNumber.equals(v.registrationNumber);
    }

    @Override public int hashCode() { return registrationNumber.hashCode(); }
}
