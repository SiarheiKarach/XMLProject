package com.karach.xmlproject.main;

import com.karach.xmlproject.creator.TouristVoucherXMLCreator;
import com.karach.xmlproject.exception.TouristVoucherException;
import com.karach.xmlproject.model.TouristVoucher;
import com.karach.xmlproject.parser.DomTouristVoucherParser;
import com.karach.xmlproject.parser.SaxTouristVoucherParser;
import com.karach.xmlproject.parser.impl.DomTouristVoucherParserImpl;
import com.karach.xmlproject.parser.impl.SaxTouristVoucherParserImpl;
import com.karach.xmlproject.validator.XmlValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Main {
  private static final Logger logger = LogManager.getLogger();

  public static void main(String[] args) {
    try {

      TouristVoucherXMLCreator.createXmlDocument();
      File xmlFile = new File("./TouristVoucher.xml");
      File xsdFile = new File("./TouristVoucher.xsd");

      XmlValidator.validateXmlAgainstXsd(xmlFile, xsdFile);
      logger.info("XML is in line with XSD");

      // SAX
      try (FileInputStream inputStream = new FileInputStream(xmlFile)) {
        SaxTouristVoucherParser saxParser = new SaxTouristVoucherParserImpl(inputStream);
        List<TouristVoucher> saxResult = saxParser.parse(inputStream);
        logger.info("\nSAX Parser Result:");
        printTouristVouchers(saxResult);
      } catch (FileNotFoundException e) {
        logger.error("File not found", e);
      } catch (IOException e) {
        logger.error("Error closing FileInputStream", e);
      }

    } catch (TouristVoucherException e) {
      logger.error("Error creating, validating, or parsing XML document", e);
    }
  }
  private static void printTouristVouchers(List<TouristVoucher> vouchers) {
    for (TouristVoucher voucher : vouchers) {
      logger.info(voucher);
    }
  }
}