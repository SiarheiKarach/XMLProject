package com.karach.xmlProject.validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
public class XmlValidator {
  private static final Logger logger = LogManager.getLogger();

  public static boolean validateXmlAgainstXsd(File xmlFile, File xsdFile) {
    try {
      SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
      Schema schema = factory.newSchema(xsdFile);
      Validator validator = schema.newValidator();
      validator.validate(new StreamSource(xmlFile));
      logger.info("Validation successful.");
      return true;
    } catch (SAXException | IOException e) {
      logger.error("Validation error: " + e.getMessage());
      return false;
    }
  }
}
