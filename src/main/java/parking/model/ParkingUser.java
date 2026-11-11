package parking.model;

public class ParkingUser {
    private final String userId;
    private final String name;
    private final UserType userType;
    private final boolean monthlyPassValid;
    private final boolean oneDayPassValid;

    public ParkingUser(String userId, String name, UserType userType,
                       boolean monthlyPassValid, boolean oneDayPassValid) {
        if (userId == null || userId.isBlank() || name == null || name.isBlank())
            throw new IllegalArgumentException("User ID and name are required.");
        this.userId = userId;
        this.name = name;
        this.userType = userType;
        this.monthlyPassValid = monthlyPassValid;
        this.oneDayPassValid = oneDayPassValid;
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
    public UserType getUserType() { return userType; }

    public boolean hasFreePass() {
        return (userType == UserType.STUDENT || userType == UserType.STAFF)
                && monthlyPassValid || (userType == UserType.VISITOR && oneDayPassValid);
    }
}
