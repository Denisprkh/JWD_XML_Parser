package by.prokhorenko.xmlparser.validation;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class XMLValidatorTest {

    XMLValidator validator;
    String xmlLink;
    String xsdLink;
    String incorrectXmlLink;

    @BeforeClass
    public void setUp(){
        validator = new XMLValidator();
        xmlLink = "testData/touristVouchersTest.xml";
        xsdLink = "testData/touristVouchersTest.xsd";
        incorrectXmlLink = "testData/incorretTouristVouchersTest.xml";
    }

    @Test
    public void validateXMLFileTest(){
        boolean actual = validator.validateXmlFile(xmlLink,xsdLink);
        Assert.assertTrue(actual);
    }

    @Test
    public void validateXMLFileIncorrectTest(){
        boolean actual = validator.validateXmlFile(incorrectXmlLink,xsdLink);
        Assert.assertFalse(actual);
    }

    @AfterClass
    public void tierDown(){
        validator = null;
        xmlLink = null;
        xsdLink = null;
        incorrectXmlLink = null;
    }


}
