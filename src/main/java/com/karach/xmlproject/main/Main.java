package com.karach.xmlproject.main;
import com.karach.xmlproject.creator.TouristVoucherXMLCreator;
import com.karach.xmlproject.exception.TouristVoucherException;
import com.karach.xmlproject.validator.XmlValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class Main {
  private static final Logger logger = LogManager.getLogger();

  public static void main(String[] args) {
    try {
      TouristVoucherXMLCreator.createXmlDocument();
      File xmlFile = new File("./TouristVoucher.xml");
      File xsdFile = new File("./TouristVoucher.xsd");

      if (XmlValidator.validateXmlAgainstXsd(xmlFile, xsdFile)) {
        logger.info("XML is in line with XSD");
      } else {
        logger.info("XML is not in line with XSD");
      }
    } catch (TouristVoucherException e) {
      logger.error("Error creating or validating XML document", e);
    }
  }
}