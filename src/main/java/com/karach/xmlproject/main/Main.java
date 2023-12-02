package com.karach.xmlproject.main;

import com.karach.xmlproject.creator.TouristVoucherXMLCreator;
import com.karach.xmlproject.exception.TouristVoucherException;
import com.karach.xmlproject.model.TouristVoucher;
import com.karach.xmlproject.parser.SaxTouristVoucherParser;
import com.karach.xmlproject.parser.impl.SaxTouristVoucherParserImpl;
import com.karach.xmlproject.parser.impl.StaxTouristVoucherParserImpl;
import com.karach.xmlproject.validator.XmlValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Main {
  private static final Logger logger = LogManager.getLogger();
  private static final String XML = "./src/main/resources/TouristVoucher.xml";
  private static final String XSD = "./src/main/resources/TouristVoucher.xsd";

  public static void main(String[] args) {
    try {
      TouristVoucherXMLCreator.createXmlDocument();
      File xmlFile = new File(XML);
      File xsdFile = new File(XSD);

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


      // StAX

      try (FileInputStream inputStream = new FileInputStream(xmlFile)) {
        StaxTouristVoucherParserImpl staxParser = new StaxTouristVoucherParserImpl();
        List<TouristVoucher> staxResult = staxParser.parseXmlFile();
        logger.info("\nStAX Parser Result:");
        printTouristVouchers(staxResult);
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