package by.prokhorenko.xmlparser.builder.impl.stax;

import by.prokhorenko.xmlparser.builder.AbstractTouristVoucherBuilder;
import by.prokhorenko.xmlparser.entity.*;
import by.prokhorenko.xmlparser.exception.TouristVoucherParserException;
import by.prokhorenko.xmlparser.builder.util.TouristVoucherTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class TouristVoucherStAXBuilder extends AbstractTouristVoucherBuilder {
    private XMLInputFactory inputFactory;
    private DatatypeFactory datatypeFactory;
    private static final Logger LOG = LogManager.getLogger();

    public TouristVoucherStAXBuilder() throws TouristVoucherParserException {
        inputFactory = XMLInputFactory.newInstance();
        touristVouchers = new HashSet<>();
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            LOG.error("Datatype configuration error",e);
            throw new TouristVoucherParserException("Datatype configuration error",e);
        }
    }

    public TouristVoucherStAXBuilder(Set<TouristVoucher> touristVouchers) throws TouristVoucherParserException {
        super(touristVouchers);
        inputFactory = XMLInputFactory.newInstance();
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            LOG.error("Datatype configuration error",e);
            throw new TouristVoucherParserException("Datatype configuration error",e);
        }
    }

    @Override
    public void buildTouristVouchers(String file) throws TouristVoucherParserException {
        FileInputStream inputStream = null;
        XMLStreamReader reader;
        String name;

        try {
            inputStream = new FileInputStream(new File(file));
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (TouristVoucherTag.TOURIST_VOUCHER.equals(TouristVoucherTag.getTagByValue(name).get())) {
                        TouristVoucher touristVoucher = buildTouristVoucher(reader);
                        touristVouchers.add(touristVoucher);
                    }
                }
            }
        } catch (XMLStreamException ex) {
            LOG.error("STaX parsing error",ex);
            throw new TouristVoucherParserException("STaX parsing error",ex);
        } catch (FileNotFoundException ex) {

        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LOG.error("InputStream is null",e);
                throw new TouristVoucherParserException("InputStream is null",e);
            }
        }
    }


    private TouristVoucher buildTouristVoucher(XMLStreamReader reader) throws TouristVoucherParserException, XMLStreamException {
        TouristVoucher touristVoucher = new TouristVoucher();
        touristVoucher.setId(reader.getAttributeValue(null, TouristVoucherTag.ID.getTag()));
        String title = reader.getAttributeValue(null, TouristVoucherTag.TITLE.getTag());
        if(title != null){
            touristVoucher.setTitle(title);
        }

        String name;
        while(reader.hasNext()){
            int type = reader.next();
            switch (type){
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (TouristVoucherTag.getTagByValue(name).get()){
                        case VOUCHER_TYPE: touristVoucher.setVoucherType
                                (TouristVoucherType.getTouristVoucherTypeByValue(getXMLText(reader)).get());
                        break;
                        case COUNTRY: touristVoucher.setCountry(getXMLText(reader));
                        break;
                        case CITY: touristVoucher.setCity(getXMLText(reader));
                        break;
                        case DURATION:touristVoucher.setDuration(buildTouristVoucherDuration(reader));
                        break;
                        case TRANSPORT:touristVoucher.setTransportType
                                (TransportType.getTransportTypeByValue(getXMLText(reader)).get());
                        break;
                        case HOTEL_CHARACTERISTICS:touristVoucher.setHotelCharacteristics
                                (buildTouristVoucherHotelCharacteristics(reader));
                        break;
                        case COST:touristVoucher.setCost(new BigDecimal(getXMLText(reader)));
                        break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if(TouristVoucherTag.TOURIST_VOUCHER.equals(TouristVoucherTag.getTagByValue(name).get())){
                        return touristVoucher;
                    }
                    break;
            }
        }
        LOG.warn("Building tourist voucher Unexpected tag");
        throw new TouristVoucherParserException("Unexpected tag");
    }

    private Duration buildTouristVoucherDuration(XMLStreamReader reader) throws XMLStreamException, TouristVoucherParserException {
        Duration duration = new Duration();

        String name;
        while(reader.hasNext()){
            int type = reader.next();
            switch (type){
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (TouristVoucherTag.getTagByValue(name).get()){
                        case START_DATE:
                            duration.setStartDate(datatypeFactory.newXMLGregorianCalendar(getXMLText(reader)));
                            break;
                        case END_DATE:
                            duration.setEndDate(datatypeFactory.newXMLGregorianCalendar(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if(TouristVoucherTag.DURATION.equals(TouristVoucherTag.getTagByValue(name).get())){
                        return duration;
                    }
                    break;
            }
        }
        LOG.warn("Building tourist voucher duration  Unexpected tag");
        throw new TouristVoucherParserException("Unexpected tag");
    }

    private HotelCharacteristics buildTouristVoucherHotelCharacteristics(XMLStreamReader reader) throws XMLStreamException, TouristVoucherParserException {
        HotelCharacteristics hotelCharacteristics = new HotelCharacteristics();

        String name;
        while(reader.hasNext()){
            int type = reader.next();
            switch (type){
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (TouristVoucherTag.getTagByValue(name).get()){
                        case STARS:hotelCharacteristics.setStars(Integer.parseInt(getXMLText(reader)));
                        break;
                        case FOOD_TYPE:hotelCharacteristics.setFoodType
                                (FoodType.getFoodTypeByValue(getXMLText(reader)).get());
                        break;
                        case ROOM_TYPE:hotelCharacteristics.setRoomType
                                (RoomType.getRoomTypeByValue(getXMLText(reader)).get());
                        break;
                        case TV:hotelCharacteristics.setHasTV(Boolean.parseBoolean(getXMLText(reader)));
                        break;
                        case AIR_CONDITION:hotelCharacteristics.setHasAirCondition(Boolean.parseBoolean(getXMLText(reader)));
                        break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if(TouristVoucherTag.HOTEL_CHARACTERISTICS.equals(TouristVoucherTag.getTagByValue(name).get())){
                        return hotelCharacteristics;
                    }
                    break;
            }
        }
        LOG.warn("Building tourist hotelCharacteristics Unexpected tag");
        throw new TouristVoucherParserException("Unexpected tag");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
