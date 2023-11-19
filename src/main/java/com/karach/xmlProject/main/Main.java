package com.karach.xmlProject.main;
import com.karach.xmlProject.creator.TouristVoucherXMLCreator;
import com.karach.xmlProject.validator.XmlValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;

public class Main {
  private static final Logger logger = LogManager.getLogger();

  public static void main(String[] args) {
    TouristVoucherXMLCreator.createXmlDocument();
    File xmlFile = new File("./TouristVoucher.xml");
    File xsdFile = new File("./TouristVoucher.xsd");

    if (XmlValidator.validateXmlAgainstXsd(xmlFile, xsdFile)) {
      logger.info("XLS in line with XSD");
  } else {
      logger.info("XLS is not in line with XSD");
    }
    }
  }