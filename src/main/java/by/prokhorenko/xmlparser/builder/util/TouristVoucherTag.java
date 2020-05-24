package by.prokhorenko.xmlparser.builder.util;

import java.util.Arrays;
import java.util.Optional;

public enum  TouristVoucherTag {
    TOURIST_VOUCHERS("tourist_vouchers"),
    ID("id"),
    TITLE("title"),
    TOURIST_VOUCHER("tourist_voucher"),
    VOUCHER_TYPE("voucher_type"),
    COUNTRY("country"),
    CITY("city"),
    DURATION("duration"),
    START_DATE("start_date"),
    END_DATE("end_date"),
    TRANSPORT("transport"),
    HOTEL_CHARACTERISTICS("hotel_characteristics"),
    STARS("stars"),
    FOOD_TYPE("food_type"),
    ROOM_TYPE("room_type"),
    TV("tv"),
    AIR_CONDITION("air_condition"),
    COST("cost"),
    INVALID_TAG("invalidTag");

    private String tag;

    TouristVoucherTag(String tag){
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public static Optional<TouristVoucherTag> getTagByValue(String value){
        TouristVoucherTag[] touristVoucherTags = TouristVoucherTag.values();
        Optional<TouristVoucherTag> touristVoucherTag
                = Arrays.stream(touristVoucherTags).filter(o -> o.getTag().equals(value)).findAny();
        return touristVoucherTag;
    }
}
