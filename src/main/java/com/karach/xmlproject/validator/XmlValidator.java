package com.karach.xmlproject.validator;

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
  private static final String XML_SCHEMA_NAMESPACE = "http://www.w3.org/2001/XMLSchema";

  public static void validateXmlAgainstXsd(File xmlFile, File xsdFile) {
    try {
      SchemaFactory factory = SchemaFactory.newInstance(XML_SCHEMA_NAMESPACE);
      Schema schema = factory.newSchema(xsdFile);
      Validator validator = schema.newValidator();
      validator.validate(new StreamSource(xmlFile));
      logger.info("Validation successful.");
    } catch (SAXException | IOException e) {
      logger.error("Error validating XML against XSD", e);
    }
  }
}