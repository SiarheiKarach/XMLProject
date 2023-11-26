package com.karach.xmlproject.parser;

import com.karach.xmlproject.exception.TouristVoucherException;
import com.karach.xmlproject.model.TouristVoucher;
import org.w3c.dom.Element;

import java.io.InputStream;
import java.util.List;

public interface DomTouristVoucherParser {
  List<TouristVoucher> parse(Element rootElement) throws TouristVoucherException;
}