package by.prokhorenko.xmlparser.validation;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XMLValidator {

    private static final Logger LOG = LogManager.getLogger();

    public boolean validateXmlFile(String xmlPath, String xsdPath){
        File xmlFile = new File(xmlPath);
        File xsdFile = new File(xsdPath);
        TouristVoucherErrorHandler errorHandler = new TouristVoucherErrorHandler();
        if(!xmlFile.exists()){
            LOG.error("XML file doesn't exist");
            return false;
        }
        if(!xsdFile.exists()){
            LOG.error("XSD file doesn't exist");
            return false;
        }
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = factory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            validator.setErrorHandler(errorHandler);
            validator.validate(new StreamSource(xmlPath));
            LOG.info("XML file is correct");
            return true;
        } catch (SAXException e) {
            LOG.error("SAXException");
            return false;
        } catch (IOException eIO){
            LOG.error("IOException");
            return false;
        }
    }

}
