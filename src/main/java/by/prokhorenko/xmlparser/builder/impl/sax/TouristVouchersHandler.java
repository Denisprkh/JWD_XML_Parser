package by.prokhorenko.xmlparser.builder.impl.sax;

import by.prokhorenko.xmlparser.entity.*;
import by.prokhorenko.xmlparser.builder.util.TouristVoucherTag;
import by.prokhorenko.xmlparser.exception.TouristVoucherParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


public class TouristVouchersHandler extends DefaultHandler {
    private static final Logger LOG = LogManager.getLogger();
    private Set<TouristVoucher> touristVouchers;
    private TouristVoucher current;
    private TouristVoucherTag currentTag;
    private static final int ID_INDEX = 0;
    private static final int TITLE_INDEX = 1;
    private DatatypeFactory datatypeFactory;
    private static final int VOUCHER_WITH_TWO_ATTRIBUTES_LENGTH = 2;


    public TouristVouchersHandler() throws TouristVoucherParserException {
        touristVouchers = new HashSet<>();
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
           LOG.error(e);
            throw new TouristVoucherParserException("Datatype configuration error",e);
        }
    }

    public Set<TouristVoucher> getTouristVouchers() {
        return touristVouchers;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes){
        if(TouristVoucherTag.TOURIST_VOUCHER.getTag().equals(localName)){
            current = new TouristVoucher();
            current.setId(attributes.getValue(ID_INDEX));
           if(attributes.getLength() == VOUCHER_WITH_TWO_ATTRIBUTES_LENGTH){
                current.setTitle(attributes.getValue(TITLE_INDEX));
            }
        }else{
            currentTag =TouristVoucherTag.getTagByValue(localName).get();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName){
        if(TouristVoucherTag.TOURIST_VOUCHER.getTag().equals(localName)){
            touristVouchers.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length){
        String value = new String(ch,start,length).trim();
        if(currentTag != null){
            switch (currentTag){
                case TOURIST_VOUCHERS:
                    break;
                case VOUCHER_TYPE:
                    current.setVoucherType(TouristVoucherType.getTouristVoucherTypeByValue(value).get());
                    break;
                case COUNTRY:
                    current.setCountry(value);
                    break;
                case CITY:
                    current.setCity(value);
                    break;
                case DURATION:current.setDuration(new Duration());
                break;
                case START_DATE:
                        current.getDuration().setStartDate(datatypeFactory.newXMLGregorianCalendar(value));
                    break;
                case END_DATE:
                        current.getDuration().setEndDate(datatypeFactory.newXMLGregorianCalendar(value));
                    break;
                case TRANSPORT:
                    current.setTransportType(TransportType.getTransportTypeByValue(value).get());
                    break;
                case HOTEL_CHARACTERISTICS: current.setHotelCharacteristics(new HotelCharacteristics());
                break;
                case STARS:
                    current.getHotelCharacteristics().setStars(Integer.parseInt(value));
                    break;
                case FOOD_TYPE:
                    current.getHotelCharacteristics().setFoodType(FoodType.getFoodTypeByValue(value).get());
                    break;
                case ROOM_TYPE:
                    current.getHotelCharacteristics().setRoomType(RoomType.getRoomTypeByValue(value).get());
                    break;
                case TV:
                    current.getHotelCharacteristics().setHasTV(Boolean.parseBoolean(value));
                    break;
                case AIR_CONDITION:
                    current.getHotelCharacteristics().setHasAirCondition(Boolean.parseBoolean(value));
                    break;
                case COST:
                    current.setCost(new BigDecimal(value));
                    break;
                default:
                    LOG.warn("Unexpected tag");
            }
            currentTag = null;
        }

    }
}
