package by.prokhorenko.xmlparser.entity;

import java.util.Arrays;
import java.util.Optional;

public enum FoodType {
    OB("OB"),
    BB("BB"),
    HB("HB"),
    FB("FB"),
    AL("AL");

    private String foodType;

    FoodType(String foodType){
        this.foodType = foodType;
    }

    public String getFoodType() {
        return foodType;
    }

    public static Optional<FoodType> getFoodTypeByValue(String value){
        FoodType[] foodTypes = FoodType.values();
        Optional<FoodType> foodType
                = Arrays.stream(foodTypes).filter(o -> o.getFoodType().equals(value)).findAny();
        return foodType;
    }
}
