package com.karach.xmlproject.creator;

import com.karach.xmlproject.exception.TouristVoucherException;
import com.karach.xmlproject.model.Currency;
import com.karach.xmlproject.model.RoomType;
import com.karach.xmlproject.model.TouristVoucher;
import com.karach.xmlproject.model.TouristVoucherBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TouristVoucherXMLCreator {

  private static final Logger logger = LogManager.getLogger();
  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_INSTANT;
  private static final String XML_NAMESPACE = "http://www.karach.com/tourist-voucher";
  private static final String XSD_SCHEMA_LOCATION = "TouristVoucher.xsd";

  public static void createXmlDocument() throws TouristVoucherException {
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.newDocument();

      Element rootElement = document.createElementNS(XML_NAMESPACE, "TouristVouchers");
      rootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
      rootElement.setAttribute("xsi:schemaLocation", XML_NAMESPACE + " " + XSD_SCHEMA_LOCATION);
      document.appendChild(rootElement);

      TouristVoucher vacation = new TouristVoucherBuilder()
              .generateRandomId()
              .setCurrentDateTime()
              .setType("vacation")
              .setCountry("Spain")
              .setMinDays(7)
              .setMaxDays(8)
              .setTransport("Plane")
              .setStars(5)
              .setFood("All Inclusive")
              .setRoomType(RoomType.DOUBLE)
              .setAmenities("included")
              .setCost(5000)
              .setCurrency(Currency.EUR)
              .build();
      addTouristVoucherElement(document, rootElement, vacation);

      TouristVoucher excursion = new TouristVoucherBuilder()
              .generateRandomId()
              .setCurrentDateTime()
              .setType("excursion")
              .setCountry("Poland")
              .setMinDays(2)
              .setMaxDays(3)
              .setTransport("Bus")
              .setStars(2)
              .setFood("BB")
              .setRoomType(RoomType.SINGLE)
              .setAmenities("included")
              .setCost(500)
              .setCurrency(Currency.EUR)
              .build();
      addTouristVoucherElement(document, rootElement, excursion);

      TouristVoucher weekend = new TouristVoucherBuilder()
              .generateRandomId()
              .setCurrentDateTime()
              .setType("weekend")
              .setCountry("Spain")
              .setMinDays(7)
              .setMaxDays(14)
              .setTransport("Plane")
              .setStars(4)
              .setFood("HB")
              .setRoomType(RoomType.DOUBLE)
              .setAmenities("included")
              .setCost(3000)
              .setCurrency(Currency.USD)
              .build();

      addTouristVoucherElement(document, rootElement, weekend);

      TouristVoucher pilgrimage = new TouristVoucherBuilder()
              .generateRandomId()
              .setCurrentDateTime()
              .setType("pilgrimage")
              .setCountry("Israel")
              .setMinDays(14)
              .setMaxDays(21)
              .setTransport("Diverse")
              .setStars(3)
              .setFood("All Inclusive")
              .setRoomType(RoomType.DOUBLE)
              .setAmenities("included")
              .setCost(10000)
              .setCurrency(Currency.ILS)
              .build();
      addTouristVoucherElement(document, rootElement, pilgrimage);

      Transformer transformer = TransformerFactory.newInstance().newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");

      try (FileOutputStream fos = new FileOutputStream("TouristVoucher.xml")) {
        transformer.transform(new DOMSource(document), new StreamResult(fos));
      } catch (TransformerException | IOException e) {
        logger.error("Error closing FileOutputStream", e);
        throw new TouristVoucherException("Error creating XML document", e);
      }

      logger.info("TouristVoucher.xml created successfully.");
    } catch (ParserConfigurationException | TransformerException | TouristVoucherException e) {
      logger.error("Error creating XML document", e);
      throw new TouristVoucherException("Error creating XML document", e);
    }
  }

  private static void addTouristVoucherElement(Document document, Element parentElement, TouristVoucher voucher) throws TouristVoucherException {
    try {
      Element touristVoucherElement = document.createElement("TouristVoucher");
      parentElement.appendChild(touristVoucherElement);

      touristVoucherElement.setAttribute("ID", String.valueOf(voucher.getID()));
      addChildElement(document, touristVoucherElement, "Type", voucher.getType());
      addChildElement(document, touristVoucherElement, "Country", voucher.getCountry());
      addChildElement(document, touristVoucherElement, "MinDays", String.valueOf(voucher.getMinDays()));
      addChildElement(document, touristVoucherElement, "MaxDays", String.valueOf(voucher.getMaxDays()));
      addChildElement(document, touristVoucherElement, "Transport", voucher.getTransport());
      addHotelCharacteristics(document, touristVoucherElement, String.valueOf(voucher.getStars()), voucher.getFood(), voucher.getRoomType().name(), voucher.getAmenities());
      addChildElement(document, touristVoucherElement, "Cost", String.valueOf(voucher.getCost()));
      touristVoucherElement.setAttribute("currency", voucher.getCurrency().name());
      addChildElement(document, touristVoucherElement, "StartDate", voucher.getStartDate());

      if (voucher.getCurrency() == null) {
        touristVoucherElement.setAttribute("currency", "USD");
      } else {
        touristVoucherElement.setAttribute("currency", voucher.getCurrency().name());
      }

    } catch (Exception e) {
      logger.error("Error adding TouristVoucher element", e);
      throw new TouristVoucherException("Error adding TouristVoucher element", e);
    }
  }

  private static void addChildElement(Document document, Element parentElement, String elementName, String textContent) {
    Element childElement = document.createElement(elementName);
    childElement.setTextContent(textContent);
    parentElement.appendChild(childElement);
  }

  private static void addHotelCharacteristics(Document document, Element parentElement, String stars, String food, String roomType, String amenities) {
    Element hotelCharacteristicsElement = document.createElement("HotelCharacteristics");
    addChildElement(document, hotelCharacteristicsElement, "Stars", stars);
    addChildElement(document, hotelCharacteristicsElement, "Food", food);
    addChildElement(document, hotelCharacteristicsElement, "RoomType", roomType);
    addChildElement(document, hotelCharacteristicsElement, "Amenities", amenities);
    parentElement.appendChild(hotelCharacteristicsElement);
  }

  private static String getCurrentDateTime() {
    ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
    return DATE_TIME_FORMATTER.format(zonedDateTime);
  }
}
