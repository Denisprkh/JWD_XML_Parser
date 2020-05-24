package by.prokhorenko.xmlparser.builder.impl.dom;

import by.prokhorenko.xmlparser.builder.AbstractTouristVoucherBuilder;
import by.prokhorenko.xmlparser.entity.*;
import by.prokhorenko.xmlparser.exception.TouristVoucherParserException;
import by.prokhorenko.xmlparser.builder.util.TouristVoucherTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class TouristVoucherDOMBuilder extends AbstractTouristVoucherBuilder {
    private DocumentBuilder documentBuilder;
    private DatatypeFactory datatypeFactory;
    private static final Logger LOG = LogManager.getLogger();

    public TouristVoucherDOMBuilder() throws TouristVoucherParserException {
        this.touristVouchers = new HashSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
            datatypeFactory = DatatypeFactory.newInstance();
        } catch (ParserConfigurationException | DatatypeConfigurationException e) {
            LOG.error(e);
            throw new TouristVoucherParserException("Datatype configuration error",e);
        }
    }

    public TouristVoucherDOMBuilder(Set<TouristVoucher> touristVouchers) throws TouristVoucherParserException {
        super(touristVouchers);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
            datatypeFactory = DatatypeFactory.newInstance();
        } catch (ParserConfigurationException | DatatypeConfigurationException e) {
            LOG.error(e);
            throw new TouristVoucherParserException("Datatype configuration error",e);
        }
    }

    @Override
    public void buildTouristVouchers(String xmlLink) throws TouristVoucherParserException {
        Document document;
        File file = new File(xmlLink);
        try {
            document = documentBuilder.parse(file);
            Element root = document.getDocumentElement();
            NodeList touristVoucherList = root.getElementsByTagName(TouristVoucherTag.TOURIST_VOUCHER.getTag());
            for (int i = 0; i < touristVoucherList.getLength(); i++) {
                Element touristVoucherElement = (Element) touristVoucherList.item(i);
                TouristVoucher touristVoucher = buildTouristVoucher(touristVoucherElement);
                touristVouchers.add(touristVoucher);
            }
        } catch (SAXException e) {
            LOG.error(e);
            throw new TouristVoucherParserException("DOM parsing tourist voucher error",e);
        } catch (IOException e) {
            LOG.error(e);
            throw new TouristVoucherParserException("DOM parsing tourist voucher error",e);
        }
    }

    private TouristVoucher buildTouristVoucher(Element element){
        TouristVoucher touristVoucher = new TouristVoucher();
        touristVoucher.setId(element.getAttribute(TouristVoucherTag.ID.getTag()));
       String title = element.getAttribute(TouristVoucherTag.TITLE.getTag());
        if(!title.isEmpty()){
            touristVoucher.setTitle(title);
        }
        touristVoucher.setVoucherType(TouristVoucherType.getTouristVoucherTypeByValue
                (getElementTextContent(element,TouristVoucherTag.VOUCHER_TYPE.getTag())).get());
         touristVoucher.setCountry(getElementTextContent(element,TouristVoucherTag.COUNTRY.getTag()));
        touristVoucher.setCity(getElementTextContent(element,TouristVoucherTag.CITY.getTag()));
        touristVoucher.setTransportType(TransportType.getTransportTypeByValue
                (getElementTextContent(element,TouristVoucherTag.TRANSPORT.getTag())).get());
        touristVoucher.setCost(new BigDecimal(getElementTextContent(element,TouristVoucherTag.COST.getTag())));
        touristVoucher.setHotelCharacteristics(buildHotelCharacteristics(element));
        touristVoucher.setDuration(buildDuration(element));
        return touristVoucher;
    }

    private Duration buildDuration(Element element){
        Duration duration = new Duration();
        duration.setStartDate(datatypeFactory.newXMLGregorianCalendar(
                (getElementTextContent(element,TouristVoucherTag.START_DATE.getTag()))));
        duration.setEndDate(datatypeFactory.newXMLGregorianCalendar(
                (getElementTextContent(element,TouristVoucherTag.END_DATE.getTag()))));
        return duration;
    }

    private HotelCharacteristics buildHotelCharacteristics(Element element){
        HotelCharacteristics hotelCharacteristics = new HotelCharacteristics();
        hotelCharacteristics.setStars
                (Integer.parseInt(getElementTextContent(element,TouristVoucherTag.STARS.getTag())));
        hotelCharacteristics.setFoodType
                (FoodType.getFoodTypeByValue
                        (getElementTextContent(element,TouristVoucherTag.FOOD_TYPE.getTag())).get());
        hotelCharacteristics .setRoomType
                (RoomType.getRoomTypeByValue
                        (getElementTextContent(element,TouristVoucherTag.ROOM_TYPE.getTag())).get());
        hotelCharacteristics.setHasTV
                (Boolean.parseBoolean(getElementTextContent(element,TouristVoucherTag.TV.getTag())));
        hotelCharacteristics.setHasAirCondition(
                (Boolean.parseBoolean(getElementTextContent(element,TouristVoucherTag.AIR_CONDITION.getTag()))));
        return hotelCharacteristics;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }

}
