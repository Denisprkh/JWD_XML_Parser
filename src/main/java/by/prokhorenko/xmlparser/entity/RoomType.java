package by.prokhorenko.xmlparser.entity;

import java.util.Arrays;
import java.util.Optional;

public enum RoomType {
    SGL("SGL"),
    DBL("DBL"),
    TRPL("TRPL"),
    SUITE("Suite"),
    EXB("ExB");

    private String roomType;

    RoomType(String roomType){
        this.roomType = roomType;
    }

    public String getRoomType() {
        return roomType;
    }

    public static Optional<RoomType> getRoomTypeByValue(String value){
        RoomType[] roomTypes = RoomType.values();
        Optional<RoomType> roomType
                = Arrays.stream(roomTypes).filter(o -> o.getRoomType().equals(value)).findAny();
        return roomType;
    }
}
