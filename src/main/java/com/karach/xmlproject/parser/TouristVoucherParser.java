package com.karach.xmlproject.parser;

import com.karach.xmlproject.exception.TouristVoucherException;
import com.karach.xmlproject.model.TouristVoucher;
import org.w3c.dom.Element;

import java.util.List;

public interface TouristVoucherParser {
  List<TouristVoucher> parse(Element rootElement) throws TouristVoucherException;
}