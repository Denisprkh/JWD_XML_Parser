package by.prokhorenko.xmlparser.builder.sax;

import by.prokhorenko.xmlparser.builder.AbstractTouristVoucherBuilder;
import by.prokhorenko.xmlparser.builder.impl.sax.TouristVoucherSAXBuilder;
import by.prokhorenko.xmlparser.entity.*;
import by.prokhorenko.xmlparser.exception.TouristVoucherParserException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class TouristVoucherSAXBuilderTest {

    AbstractTouristVoucherBuilder saxBuilder;
    String xmlLink;
    Set<TouristVoucher> expectedVouchers;
    TouristVoucher touristVoucher;
    DatatypeFactory datatypeFactory;
    Duration duration;
    HotelCharacteristics hotelCharacteristics;


    @BeforeClass
    public void setUp() throws DatatypeConfigurationException, TouristVoucherParserException {
        saxBuilder = new TouristVoucherSAXBuilder();
        xmlLink = "testData/touristVouchersTest.xml";
        datatypeFactory = DatatypeFactory.newInstance();
        touristVoucher = new TouristVoucher();
        touristVoucher.setId("ID1");
        touristVoucher.setTitle("Tourist voucher");
        touristVoucher.setVoucherType(TouristVoucherType.EXCURSION);
        touristVoucher.setCountry("Germany");
        touristVoucher.setCity("Berlin");
        touristVoucher.setTransportType(TransportType.PLAIN);
        duration = new Duration();
        duration.setStartDate(datatypeFactory.newXMLGregorianCalendar("2020-05-19"));
        duration.setEndDate(datatypeFactory.newXMLGregorianCalendar("2020-05-29"));
        touristVoucher.setDuration(duration);
        hotelCharacteristics = new HotelCharacteristics();
        hotelCharacteristics.setStars(4);
        hotelCharacteristics.setRoomType(RoomType.DBL);
        hotelCharacteristics.setFoodType(FoodType.AL);
        hotelCharacteristics.setHasTV(true);
        hotelCharacteristics.setHasAirCondition(true);
        touristVoucher.setHotelCharacteristics(hotelCharacteristics);
        touristVoucher.setCost(new BigDecimal("2000.00"));
        expectedVouchers = new HashSet<>();
        expectedVouchers.add(touristVoucher);

    }

    @Test
    public void buildTouristVouchersTestLength() throws TouristVoucherParserException {
        saxBuilder.buildTouristVouchers(xmlLink);
        int actual = saxBuilder.getTouristVouchers().size();
        int expectedLength = 1;
        Assert.assertEquals(expectedLength,actual);
    }

    @Test
    public void buildTouristVouchersTestSet() throws TouristVoucherParserException {
        saxBuilder.buildTouristVouchers(xmlLink);
        Set<TouristVoucher> actualVouchers = saxBuilder.getTouristVouchers();
        Assert.assertEquals(expectedVouchers,actualVouchers);
    }

    @AfterClass
    public void tierDown(){
        saxBuilder = null;
        xmlLink = null;
        expectedVouchers = null;
        touristVoucher = null;
        datatypeFactory = null;
        duration = null;
        hotelCharacteristics = null;
    }
}
