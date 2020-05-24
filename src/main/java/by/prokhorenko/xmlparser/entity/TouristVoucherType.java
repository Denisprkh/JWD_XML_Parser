package by.prokhorenko.xmlparser.entity;

import java.util.Arrays;
import java.util.Optional;

public enum TouristVoucherType {
    WEEKEND("weekend"),
    EXCURSION("excursion"),
    RELAXATION("relaxation"),
    PILGRIMAGE("pilgrimage"),
    CRUISE("cruise");

    private String touristVoucherType;

    TouristVoucherType(String touristVoucherType){
        this.touristVoucherType = touristVoucherType;
    }

    public String getTouristVoucherType() {
        return touristVoucherType;
    }

    public static Optional<TouristVoucherType> getTouristVoucherTypeByValue(String value){
        TouristVoucherType[] touristVoucherTypes = TouristVoucherType.values();
        Optional<TouristVoucherType> touristVoucherType
                = Arrays.stream(touristVoucherTypes).filter(o -> o.getTouristVoucherType().equals(value)).findAny();
        return touristVoucherType;
    }
}
