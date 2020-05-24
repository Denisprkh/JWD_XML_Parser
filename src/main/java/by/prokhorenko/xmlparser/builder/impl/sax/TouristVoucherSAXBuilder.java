package by.prokhorenko.xmlparser.builder.impl.sax;

import by.prokhorenko.xmlparser.builder.AbstractTouristVoucherBuilder;
import by.prokhorenko.xmlparser.entity.TouristVoucher;
import by.prokhorenko.xmlparser.exception.TouristVoucherParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class TouristVoucherSAXBuilder extends AbstractTouristVoucherBuilder {
    private TouristVouchersHandler handler;
    private XMLReader reader;
    private static final Logger LOG = LogManager.getLogger();

    public TouristVoucherSAXBuilder() throws TouristVoucherParserException {
        handler = new TouristVouchersHandler();
        touristVouchers = new HashSet<>();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
        } catch (SAXException e) {
            LOG.error(e);
        }
    }

   public TouristVoucherSAXBuilder(Set<TouristVoucher> touristVouchers) throws TouristVoucherParserException {
        super(touristVouchers);
        handler = new TouristVouchersHandler();
        try {
           reader = XMLReaderFactory.createXMLReader();
           reader.setContentHandler(handler);
         } catch (SAXException e) {
           LOG.error(e);
        }
   }

    @Override
    public void buildTouristVouchers(String file) throws TouristVoucherParserException {
        try {
            File xmlFile = new File(file);
            reader.parse(new InputSource(String.valueOf(xmlFile)));
            touristVouchers.addAll(handler.getTouristVouchers());
        } catch (IOException | SAXException e) {
            LOG.error(e);
            throw new TouristVoucherParserException("SAX parsing tourist voucher exception",e);
        }
    }
}
