  package com.karach.xmlproject.parser.impl;

  import com.karach.xmlproject.exception.TouristVoucherException;
  import com.karach.xmlproject.model.*;
  import com.karach.xmlproject.parser.DomTouristVoucherParser;
  import org.apache.logging.log4j.LogManager;
  import org.apache.logging.log4j.Logger;
  import org.w3c.dom.Element;
  import org.w3c.dom.NodeList;

  import java.io.IOException;
  import java.io.InputStream;
  import java.util.ArrayList;
  import java.util.List;

  public class DomTouristVoucherParserImpl implements DomTouristVoucherParser {

    private static final Logger logger = LogManager.getLogger();
    private final InputStream inputStream;

    public DomTouristVoucherParserImpl(InputStream inputStream) {
      this.inputStream = inputStream;
    }

    @Override
    public List<TouristVoucher> parse(Element rootElement) throws TouristVoucherException {
      List<TouristVoucher> touristVouchers = new ArrayList<>();
      NodeList voucherNodes = rootElement.getElementsByTagName(XmlElement.TOURIST_VOUCHER.getValue());
      for (int i = 0; i < voucherNodes.getLength(); i++) {
        Element voucherElement = (Element) voucherNodes.item(i);
        try {
          TouristVoucher voucher = parseVoucherElement(voucherElement);
          touristVouchers.add(voucher);
        } catch (IOException e) {
          logger.error("Error parsing TouristVoucher element", e);
          throw new TouristVoucherException("Error parsing TouristVoucher element", e);
        }
      }

      return touristVouchers;
    }

    private TouristVoucher parseVoucherElement(Element voucherElement) throws IOException, TouristVoucherException {
      TouristVoucher voucher = new TouristVoucher();

      voucher.setId(Integer.parseInt(voucherElement.getAttribute(XmlElement.TYPE.getValue())));
      voucher.setType(Type.valueOf(voucherElement.getAttribute(XmlElement.TYPE.getValue())));
      voucher.setCountry(getElementTextContent(voucherElement, XmlElement.COUNTRY.getValue()));
      voucher.setMinDays(Integer.parseInt(voucherElement.getAttribute(XmlElement.MIN_DAYS.getValue())));
      voucher.setMaxDays(Integer.parseInt(voucherElement.getAttribute(XmlElement.MAX_DAYS.getValue())));
      voucher.setTransport(Transport.valueOf(getElementTextContent(voucherElement, XmlElement.TRANSPORT.getValue())));

      Element hotelCharacteristicsElement = (Element) voucherElement.getElementsByTagName(XmlElement.HOTEL_CHARACTERISTICS.getValue()).item(0);
      String stars = getElementTextContent(hotelCharacteristicsElement, XmlElement.STARS.getValue());
      String food = getElementTextContent(hotelCharacteristicsElement, XmlElement.FOOD.getValue());
      RoomType roomType = RoomType.valueOf(getElementTextContent(hotelCharacteristicsElement, XmlElement.ROOM_TYPE.getValue()));
      String amenities = getElementTextContent(hotelCharacteristicsElement, XmlElement.AMENITIES.getValue());
      voucher.setHotelCharacteristics(Stars.valueOf(stars), Food.valueOf(food), roomType, amenities);
      voucher.setCost(Float.parseFloat(getElementTextContent(voucherElement, XmlElement.COST.getValue())));
      voucher.setCurrency(Currency.valueOf(voucherElement.getAttribute(XmlElement.CURRENCY.getValue())));
      voucher.setStartDate(getElementTextContent(voucherElement, XmlElement.START_DATE.getValue()));

      return voucher;
    }

    private String getElementTextContent(Element parentElement, String elementName) throws TouristVoucherException {
      NodeList nodeList = parentElement.getElementsByTagName(elementName);
      if (nodeList.getLength() == 0) {
        throw new TouristVoucherException("Element not found: " + elementName);
      }
      return nodeList.item(0).getTextContent();
    }
  }