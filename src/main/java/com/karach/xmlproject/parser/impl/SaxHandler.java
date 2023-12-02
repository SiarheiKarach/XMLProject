package com.karach.xmlproject.parser.impl;

import com.karach.xmlproject.exception.TouristVoucherException;
import com.karach.xmlproject.model.TouristVoucher;
import com.karach.xmlproject.model.Type;
import com.karach.xmlproject.model.XmlElement;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SaxHandler extends DefaultHandler {

  private List<TouristVoucher> touristVouchers;
  private TouristVoucher currentVoucher;
  private StringBuilder data;

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes)  {
    if (XmlElement.TOURIST_VOUCHER.getValue().equals(qName)) {
      currentVoucher = new TouristVoucher();
      if (touristVouchers == null) {
        touristVouchers = new ArrayList<>();
      }
      String id = attributes.getValue(XmlElement.ID.getValue());
      if (id == null || id.trim().isEmpty()) {
        try {
          throw new TouristVoucherException("Attribute 'id' is null or empty");
        } catch (TouristVoucherException e) {
          throw new RuntimeException(e);
        }
      }
      try {
        currentVoucher.setId(parseInteger(id));
      } catch (TouristVoucherException e) {
        throw new RuntimeException(e);
      }
    }
    data = new StringBuilder();
  }

  @Override
  public void characters(char[] ch, int start, int length) {
    data.append(ch, start, length);
  }

  @Override
  public void endElement(String uri, String localName, String qName) {
    if (XmlElement.TYPE.getValue().equals(qName)) {
      currentVoucher.setType(Type.valueOf(data.toString()));
    } else if (XmlElement.COUNTRY.getValue().equals(qName)) {
      currentVoucher.setCountry(data.toString());
    } else if (XmlElement.TOURIST_VOUCHER.getValue().equals(qName)) {
      touristVouchers.add(currentVoucher);
      currentVoucher = null;
    }
  }

  public List<TouristVoucher> getTouristVouchers() {
    return touristVouchers;
  }

  private Integer parseInteger(String value) throws TouristVoucherException {
    if (value != null && !value.trim().isEmpty()) {
      try {
        return Integer.parseInt(value);
      } catch (NumberFormatException e) {
        throw new TouristVoucherException("Invalid integer format for value: " + value, e);
      }
    } else {
      throw new TouristVoucherException("Attribute value is null or empty");
    }
  }
}