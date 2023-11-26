package com.karach.xmlproject.parser.impl;
import com.karach.xmlproject.model.Type;
import com.karach.xmlproject.exception.TouristVoucherException;
import com.karach.xmlproject.model.TouristVoucher;
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
  public void startElement(String uri, String localName, String qName, Attributes attributes) {
    if ("TouristVoucher".equals(qName)) {
      currentVoucher = new TouristVoucher();
      if (touristVouchers == null) {
        touristVouchers = new ArrayList<>();
      }
      String id = attributes.getValue("id");
      currentVoucher.setId(Integer.parseInt(id));
    }

    data = new StringBuilder();
  }

  @Override
  public void characters(char[] ch, int start, int length) {
    data.append(ch, start, length);
  }

  @Override
  public void endElement(String uri, String localName, String qName) {
    if ("Type".equals(qName)) {
      currentVoucher.setType(Type.valueOf(data.toString()));
    } else if ("Country".equals(qName)) {
      currentVoucher.setCountry(data.toString());
    }
    else if ("TouristVoucher".equals(qName)) {
      touristVouchers.add(currentVoucher);
      currentVoucher = null;
    }
  }

  public List<TouristVoucher> getTouristVouchers() {
    return touristVouchers;
  }
}