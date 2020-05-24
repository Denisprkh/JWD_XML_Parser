package by.prokhorenko.xmlparser.entity;

public class HotelCharacteristics {
    private int stars;
    private FoodType foodType;
    private RoomType roomType;
    private boolean hasTV;
    private boolean hasAirCondition;

    public HotelCharacteristics(){}

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public boolean isHasTV() {
        return hasTV;
    }

    public void setHasTV(boolean hasTV) {
        this.hasTV = hasTV;
    }

    public boolean isHasAirCondition() {
        return hasAirCondition;
    }

    public void setHasAirCondition(boolean hasAirCondition) {
        this.hasAirCondition = hasAirCondition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HotelCharacteristics that = (HotelCharacteristics) o;

        if (stars != that.stars) return false;
        if (hasTV != that.hasTV) return false;
        if (hasAirCondition != that.hasAirCondition) return false;
        if (foodType != that.foodType) return false;
        return roomType == that.roomType;
    }

    @Override
    public int hashCode() {
        int result = stars;
        result = 31 * result + (foodType != null ? foodType.hashCode() : 0);
        result = 31 * result + (roomType != null ? roomType.hashCode() : 0);
        result = 31 * result + (hasTV ? 1 : 0);
        result = 31 * result + (hasAirCondition ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HotelCharacteristics{");
        sb.append("stars=").append(stars);
        sb.append(", foodType=").append(foodType);
        sb.append(", roomType=").append(roomType);
        sb.append(", hasTV=").append(hasTV);
        sb.append(", hasAirCondition=").append(hasAirCondition);
        sb.append('}');
        return sb.toString();
    }
}
