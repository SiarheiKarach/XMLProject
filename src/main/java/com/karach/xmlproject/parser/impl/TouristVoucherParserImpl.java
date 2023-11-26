package com.karach.xmlproject.parser.impl;

import com.karach.xmlproject.exception.TouristVoucherException;
import com.karach.xmlproject.model.*;
import com.karach.xmlproject.parser.TouristVoucherParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TouristVoucherParserImpl implements TouristVoucherParser {

  private static final Logger logger = LogManager.getLogger();
  private final InputStream inputStream;

  private TouristVoucherParserImpl(Builder builder) {
    this.inputStream = builder.inputStream;
  }

  @Override
  public List<TouristVoucher> parse(Element rootElement) throws TouristVoucherException {
    List<TouristVoucher> touristVouchers = new ArrayList<>();
    NodeList voucherNodes = rootElement.getElementsByTagName("TouristVoucher");
    for (int i = 0; i < voucherNodes.getLength(); i++) {
      Element voucherElement = (Element) voucherNodes.item(i);
      TouristVoucher voucher = parseVoucherElement(voucherElement);
      touristVouchers.add(voucher);
    }

    return touristVouchers;
  }

  private TouristVoucher parseVoucherElement(Element voucherElement) throws TouristVoucherException {
    TouristVoucher voucher = new TouristVoucher();

    try {
      voucher.setId(Integer.parseInt(voucherElement.getAttribute("id")));
      voucher.setType(Type.valueOf(voucherElement.getAttribute("Type")));
      voucher.setCountry(getElementTextContent(voucherElement, "Country"));
      voucher.setMinDays(Integer.parseInt(voucherElement.getAttribute("MinDays")));
      voucher.setMaxDays(Integer.parseInt(voucherElement.getAttribute("MaxDays")));
      voucher.setTransport(Transport.valueOf(getElementTextContent(voucherElement, "Transport")));

      Element hotelCharacteristicsElement = (Element) voucherElement.getElementsByTagName("HotelCharacteristics").item(0);
      String stars = getElementTextContent(hotelCharacteristicsElement, "Stars");
      String food = getElementTextContent(hotelCharacteristicsElement, "Food");
      RoomType roomType = RoomType.valueOf(getElementTextContent(hotelCharacteristicsElement, "RoomType"));
      String amenities = getElementTextContent(hotelCharacteristicsElement, "Amenities");
      voucher.setHotelCharacteristics(Stars.valueOf(stars), Food.valueOf(food), roomType, amenities);
      voucher.setCost(Float.parseFloat(getElementTextContent(voucherElement, "Cost")));
      voucher.setCurrency(Currency.valueOf(voucherElement.getAttribute("currency")));
      voucher.setStartDate(getElementTextContent(voucherElement, "StartDate"));
    } catch (Exception e) {
      throw new TouristVoucherException("Error parsing TouristVoucher element", e);
    }

    return voucher;
  }

  private String getElementTextContent(Element parentElement, String elementName) throws TouristVoucherException {
    NodeList nodeList = parentElement.getElementsByTagName(elementName);
    if (nodeList.getLength() == 0) {
      throw new TouristVoucherException("Element not found: " + elementName);
    }
    return nodeList.item(0).getTextContent();
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