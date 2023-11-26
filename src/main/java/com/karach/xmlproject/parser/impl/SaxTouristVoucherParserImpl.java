package com.karach.xmlproject.parser.impl;

import com.karach.xmlproject.exception.TouristVoucherException;
import com.karach.xmlproject.model.TouristVoucher;
import com.karach.xmlproject.parser.SaxTouristVoucherParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SaxTouristVoucherParserImpl implements SaxTouristVoucherParser {

  private static final Logger logger = LogManager.getLogger();
  private final InputStream inputStream;

  public SaxTouristVoucherParserImpl(InputStream inputStream) {
    this.inputStream = inputStream;
  }

  @Override
  public List<TouristVoucher> parse(InputStream inputStream) throws TouristVoucherException {
    try {
      SAXParserFactory factory = SAXParserFactory.newInstance();
      SAXParser saxParser = factory.newSAXParser();

      SaxHandler handler = new SaxHandler();
      saxParser.parse(inputStream, handler);

      return handler.getTouristVouchers();
    } catch (ParserConfigurationException | SAXException | IOException e) {
      logger.error("Error parsing XML using SAX", e);
      throw new TouristVoucherException("Error parsing XML using SAX", e);
    }
  }
}