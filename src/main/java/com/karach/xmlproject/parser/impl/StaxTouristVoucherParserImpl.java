package com.karach.xmlproject.parser.impl;

import com.karach.xmlproject.exception.TouristVoucherException;
import com.karach.xmlproject.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StaxTouristVoucherParserImpl {

  private static final Logger logger = LogManager.getLogger();
  private static final String XML_FILE_PATH = "./src/main/resources/TouristVoucher.xml";

  public static List<TouristVoucher> parseXmlFile() throws TouristVoucherException {
    List<TouristVoucher> touristVouchers = new ArrayList<>();
    try (FileInputStream fileInputStream = new FileInputStream(XML_FILE_PATH)) {
      XMLInputFactory factory = XMLInputFactory.newInstance();
      XMLStreamReader reader = factory.createXMLStreamReader(fileInputStream);

      TouristVoucherBuilder voucherBuilder = null;
      String elementName = null;

      while (reader.hasNext()) {
        int event = reader.next();

        switch (event) {
          case XMLStreamConstants.START_ELEMENT:
            elementName = reader.getLocalName();
            if ("TouristVoucher".equals(elementName)) {
              voucherBuilder = new TouristVoucherBuilder();
              voucherBuilder.setId(Integer.parseInt(reader.getAttributeValue(null, "id")));
            }
            break;

          case XMLStreamConstants.CHARACTERS:
            String text = reader.getText().trim();
            if (!text.isEmpty()) {
              switch (elementName) {
                case "Type":
                  voucherBuilder.setType(Type.valueOf(text));
                  break;
                case "Country":
                  voucherBuilder.setCountry(text);
                  break;
                case "MinDays":
                  voucherBuilder.setMinDays(Integer.parseInt(text));
                  break;
                case "MaxDays":
                  voucherBuilder.setMaxDays(Integer.parseInt(text));
                  break;
                case "Transport":
                  voucherBuilder.setTransport(Transport.valueOf(text));
                  break;
                case "Stars":
                  voucherBuilder.setStars(Stars.valueOf(text));
                  break;
                case "Food":
                  voucherBuilder.setFood(Food.valueOf(text));
                  break;
                case "RoomType":
                  voucherBuilder.setRoomType(RoomType.valueOf(text));
                  break;
                case "Amenities":
                  voucherBuilder.setAmenities(text);
                  break;
                case "Cost":
                  voucherBuilder.setCost(Float.parseFloat(text));
                  break;
                case "Currency":
                  voucherBuilder.setCurrency(Currency.valueOf(text));
                  break;
                case "StartDate":
                  voucherBuilder.setCurrentDateTime();
                  break;
              }
            }
            break;

          case XMLStreamConstants.END_ELEMENT:
            elementName = reader.getLocalName();
            if ("TouristVoucher".equals(elementName)) {
              TouristVoucher voucher = voucherBuilder.build();
              touristVouchers.add(voucher);
            }
            break;
        }
      }
    } catch (IOException | XMLStreamException e) {
      logger.error("Error parsing XML file", e);
      throw new TouristVoucherException("Error parsing XML file", e);
    }
    return touristVouchers;
  }
}