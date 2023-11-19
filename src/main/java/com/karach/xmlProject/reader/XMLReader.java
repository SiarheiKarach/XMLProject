package com.karach.xmlProject.reader;

import com.karach.xmlProject.model.TouristVoucher;
import com.karach.xmlProject.model.TouristVoucherBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.time.format.DateTimeParseException;

public class XMLReader {

  private static final Logger logger = LogManager.getLogger();

  public static void main(String[] args) {
    try {
      File xmlFile = new File("TouristVoucher.xml");
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(xmlFile);
      doc.getDocumentElement().normalize();

      NodeList nodeList = doc.getElementsByTagName("TouristVoucher");

      for (int i = 0; i < nodeList.getLength(); i++) {
        Node node = nodeList.item(i);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          Element element = (Element) node;
          TouristVoucher voucher = createTouristVoucherFromElement(element);
          logger.info("Tourist Voucher ID: " + voucher.getId());
          logger.info("Type: " + voucher.getType());
          logger.info("Country: " + voucher.getCountry());
        }
      }
    } catch (Exception e) {
      logger.error("Error reading XML file", e);
    }
  }

  private static TouristVoucher createTouristVoucherFromElement(Element element) {
    try {
      TouristVoucherBuilder builder = new TouristVoucherBuilder()
              .generateRandomId()
              .setCurrentDateTime()
              .setType(getElementTextContent(element, "Type"))
              .setCountry(getElementTextContent(element, "Country"))
              .setMinDays(getElementTextContent(element, "MinDays"))
              .setMaxDays(getElementTextContent(element, "MaxDays"))
              .setTransport(getElementTextContent(element, "Transport"))
              .setStars(getElementTextContent(element, "Stars"))
              .setFood(getElementTextContent(element, "Food"))
              .setRoomType(getElementTextContent(element, "RoomType"))
              .setAmenities(getElementTextContent(element, "Amenities"))
              .setCost(getElementTextContent(element, "Cost"))
              .setCurrency(element.getAttribute("currency"));
      return builder.build();
    } catch (NumberFormatException | DateTimeParseException e) {
      logger.error("Error parsing XML content", e);
      return null;
    }
  }

  private static String getElementTextContent(Element element, String tagName) {
    NodeList nodeList = element.getElementsByTagName(tagName);
    if (nodeList.getLength() > 0 && nodeList.item(0) != null) {
      return nodeList.item(0).getTextContent();
    } else {
      return "_";
    }
  }
}