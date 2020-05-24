package by.prokhorenko.xmlparser.validation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class TouristVoucherErrorHandler extends DefaultHandler {
    private static final Logger LOG = LogManager.getLogger();

    public void warning(SAXParseException e) {
        LOG.warn(getLineAddress(e) + "-" + e.getMessage());
    }
    public void error(SAXParseException e) {
        LOG.error(getLineAddress(e) + " - " + e.getMessage());
    }
    public void fatalError(SAXParseException e) {
        LOG.fatal(getLineAddress(e) + " - " + e.getMessage());
    }
    private String getLineAddress(SAXParseException e) {
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }
}
