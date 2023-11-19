package com.karach.xmlProject.parser.impl;

import com.karach.xmlProject.model.TouristVoucher;
import com.karach.xmlProject.parser.TouristVoucherParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class TouristVoucherParserImpl implements TouristVoucherParser {

  private static final Logger logger = LogManager.getLogger();
  private final InputStream inputStream;

  private TouristVoucherParserImpl(Builder builder) {
    this.inputStream = builder.inputStream;
  }

  @Override
  public List<TouristVoucher> parse() {
    List<TouristVoucher> vouchers = new ArrayList<>();

    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder documentBuilder = factory.newDocumentBuilder();
      Document document = documentBuilder.parse(inputStream);

      NodeList voucherNodes = document.getElementsByTagName("TouristVoucher");

      for (int i = 0; i < voucherNodes.getLength(); i++) {
        Element voucherElement = (Element) voucherNodes.item(i);
        TouristVoucher voucher = parseVoucherElement(voucherElement);
        vouchers.add(voucher);
      }

    } catch (ParserConfigurationException e) {
      logger.error("Error creating DocumentBuilder", e);
    } catch (Exception e) {
      logger.error("Error parsing XML", e);
    }

    return vouchers;
  }

  private TouristVoucher parseVoucherElement(Element voucherElement) {
    TouristVoucher voucher = new TouristVoucher();

    voucher.setId(voucherElement.getAttribute("id"));
    voucher.setType(getElementTextContent(voucherElement, "Type"));
    voucher.setCountry(getElementTextContent(voucherElement, "Country"));
    voucher.setDaysNights(getElementTextContent(voucherElement, "MinDays"));
    voucher.setDaysNights(getElementTextContent(voucherElement, "MaxDays"));
    voucher.setTransport(getElementTextContent(voucherElement, "Transport"));

    Element hotelCharacteristicsElement = getFirstChildElement(voucherElement, "HotelCharacteristics");
    if (hotelCharacteristicsElement != null) {
      voucher.setHotelCharacteristics(
              getElementTextContent(hotelCharacteristicsElement, "Stars"),
              getElementTextContent(hotelCharacteristicsElement, "Food"),
              getElementTextContent(hotelCharacteristicsElement, "RoomType"),
              getElementTextContent(hotelCharacteristicsElement, "Amenities")
      );
    }

    voucher.setCost(getElementTextContent(voucherElement, "Cost"));
    voucher.setCurrency(voucherElement.getAttribute("currency"));
    voucher.setStartDate(String.valueOf(ZonedDateTime.parse(getElementTextContent(voucherElement, "StartDate"))));

    return voucher;
  }

  private String getElementTextContent(Element parentElement, String elementName) {
    NodeList nodeList = parentElement.getElementsByTagName(elementName);
    if (nodeList.getLength() > 0) {
      return nodeList.item(0).getTextContent();
    }
    return null;
  }

  private String getAttribute(Element element, String attributeName) {
    return element.getAttribute(attributeName);
  }

  private String getAttribute(Element element, String parentElementName, String attributeName) {
    Element parentElement = getFirstChildElement(element, parentElementName);
    if (parentElement != null) {
      return parentElement.getAttribute(attributeName);
    }
    return null;
  }

  private Element getFirstChildElement(Element parentElement, String elementName) {
    NodeList nodeList = parentElement.getElementsByTagName(elementName);
    if (nodeList.getLength() > 0) {
      return (Element) nodeList.item(0);
    }
    return null;
  }

  public static class Builder {
    private InputStream inputStream;

    public Builder(InputStream inputStream) {
      this.inputStream = inputStream;
    }

    public TouristVoucherParserImpl build() {
      return new TouristVoucherParserImpl(this);
    }
  }
}