package by.prokhorenko.xmlparser.entity;

import by.prokhorenko.xmlparser.builder.AbstractTouristVoucherBuilder;
import java.math.BigDecimal;

public class TouristVoucher {
    private String id;
    private String title;
    private TouristVoucherType voucherType;
    private String country;
    private String city;
    private HotelCharacteristics hotelCharacteristics;
    private Duration duration;
    private TransportType transportType;
    private BigDecimal cost;
    private static final String DEFAULT_TITLE = "Tourist voucher";

    public TouristVoucher(){
        this.title = DEFAULT_TITLE;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TouristVoucherType getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(TouristVoucherType voucherType) {
        this.voucherType = voucherType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public HotelCharacteristics getHotelCharacteristics() {
        return hotelCharacteristics;
    }

    public void setHotelCharacteristics(HotelCharacteristics hotelCharacteristics) {
        this.hotelCharacteristics = hotelCharacteristics;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TouristVoucher that = (TouristVoucher) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (voucherType != that.voucherType) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (hotelCharacteristics != null ? !hotelCharacteristics.equals(that.hotelCharacteristics) :
                that.hotelCharacteristics != null)
            return false;
        if (duration != null ? !duration.equals(that.duration) : that.duration != null) return false;
        if (transportType != that.transportType) return false;
        return cost != null ? cost.equals(that.cost) : that.cost == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (voucherType != null ? voucherType.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (hotelCharacteristics != null ? hotelCharacteristics.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (transportType != null ? transportType.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TouristVoucher{");
        sb.append("id='").append(id).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", voucherType=").append(voucherType);
        sb.append(", country='").append(country).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", hotelCharacteristics=").append(hotelCharacteristics);
        sb.append(", duration=").append(duration);
        sb.append(", transportType=").append(transportType);
        sb.append(", cost=").append(cost);
        sb.append('}');
        return sb.toString();
    }
}
