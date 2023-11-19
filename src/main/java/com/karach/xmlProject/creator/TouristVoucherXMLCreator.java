package com.karach.xmlProject.creator;

import com.karach.xmlProject.model.TouristVoucher;
import com.karach.xmlProject.model.TouristVoucherBuilder;
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
import java.util.Random;

public class TouristVoucherXMLCreator {

  private static final Logger logger = LogManager.getLogger();
  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_INSTANT;
  private static final String XML_NAMESPACE = "http://www.karach.com/tourist-voucher";
  private static final String XSD_SCHEMA_LOCATION = "TouristVoucher.xsd";

  public static void createXmlDocument() {
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
              .setDaysNights("7", "50")
              .setTransport("Plane")
              .setStars("5")
              .setFood("All Inclusive")
              .setRoomType("2")
              .setAmenities("included")
              .setCost("5000")
              .setCurrency("EUR")
              .build();
      addTouristVoucherElement(document, rootElement, vacation);

      TouristVoucher excursion = new TouristVoucherBuilder()
              .generateRandomId()
              .setCurrentDateTime()
              .setType("excursion")
              .setCountry("Poland")
              .setDaysNights("2", "3")
              .setTransport("Bus")
              .setStars("2")
              .setFood("BB")
              .setRoomType("2")
              .setAmenities("included")
              .setCost("500")
              .build();
      addTouristVoucherElement(document, rootElement, excursion);

      TouristVoucher weekend = new TouristVoucherBuilder()
              .generateRandomId()
              .setCurrentDateTime()
              .setType("weekend")
              .setCountry("Spain")
              .setDaysNights("3", "4")
              .setTransport("Plane")
              .setStars("4")
              .setFood("HB")
              .setRoomType("3")
              .setAmenities("included")
              .setCost("3000")
              .build();
      addTouristVoucherElement(document, rootElement, weekend);

      TouristVoucher pilgrimage = new TouristVoucherBuilder()
              .generateRandomId()
              .setCurrentDateTime()
              .setType("pilgrimage")
              .setCountry("Israel")
              .setDaysNights("14", "21")
              .setTransport("Diverse")
              .setStars("3")
              .setFood("All Inclusive")
              .setRoomType("4")
              .setAmenities("included")
              .setCost("10000")
              .setCurrency("ILS")
              .build();
      addTouristVoucherElement(document, rootElement, pilgrimage);

      Transformer transformer = TransformerFactory.newInstance().newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");

      try (FileOutputStream fos = new FileOutputStream("TouristVoucher.xml")) {
        transformer.transform(new DOMSource(document), new StreamResult(fos));
      } catch (IOException e) {
        logger.error("Error closing FileOutputStream", e);
      }

      logger.info("TouristVoucher.xml created successfully.");
    } catch (ParserConfigurationException | TransformerException e) {
      logger.error("Error creating XML document", e);
    }
  }

  private static void addTouristVoucherElement(Document document, Element parentElement, TouristVoucher voucher) {
    Element touristVoucherElement = document.createElement("TouristVoucher");
    parentElement.appendChild(touristVoucherElement);

    touristVoucherElement.setAttribute("id", voucher.getId());
    addChildElement(document, touristVoucherElement, "Type", voucher.getType());
    addChildElement(document, touristVoucherElement, "Country", voucher.getCountry());
    addDaysNightsElement(document, touristVoucherElement, voucher.getMinDays(), voucher.getMaxDays());
    addChildElement(document, touristVoucherElement, "Transport", voucher.getTransport());
    addHotelCharacteristics(document, touristVoucherElement, voucher.getStars(), voucher.getFood(), voucher.getRoomType(), voucher.getAmenities());
    addChildElement(document, touristVoucherElement, "Cost", voucher.getCost());
    if (voucher.getCurrency() == null) {
      touristVoucherElement.setAttribute("currency", "USD");
    } else {
      touristVoucherElement.setAttribute("currency", voucher.getCurrency());
    }
    addChildElement(document, touristVoucherElement, "StartDate", voucher.getStartDate());
  }

  private static void addChildElement(Document document, Element parentElement, String elementName, String textContent) {
    Element childElement = document.createElement(elementName);
    childElement.setTextContent(textContent);
    parentElement.appendChild(childElement);
  }

  private static void addDaysNightsElement(Document document, Element parentElement, String minDays, String maxDays) {
    Element daysNightsElement = document.createElement("DaysNights");

    int maxDaysValue = (maxDays != null) ? Integer.parseInt(maxDays) : 0;
    if (maxDaysValue > 30) {
      maxDaysValue = 30;
    }
    daysNightsElement.setAttribute("MinDays", String.valueOf(minDays));
    daysNightsElement.setAttribute("MaxDays", String.valueOf(maxDaysValue));
    parentElement.appendChild(daysNightsElement);
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
