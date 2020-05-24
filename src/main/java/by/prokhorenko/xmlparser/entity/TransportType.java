package by.prokhorenko.xmlparser.entity;

import java.util.Arrays;
import java.util.Optional;

public enum TransportType {
    PLAIN("plane"),
    SHIP("ship"),
    BUS("bus"),
    TRAIN("train");

    private String transportType;

    TransportType(String transportType){
        this.transportType = transportType;
    }

    public String getTransportType() {
        return transportType;
    }

    public static Optional<TransportType> getTransportTypeByValue(String value){
        TransportType[] transportTypes = TransportType.values();
        Optional<TransportType> transportType
                = Arrays.stream(transportTypes).filter(o -> o.getTransportType().equals(value)).findAny();
        return transportType;
    }
}
