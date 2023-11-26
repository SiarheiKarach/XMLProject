package com.karach.xmlproject.parser;
import com.karach.xmlproject.model.TouristVoucher;
import com.karach.xmlproject.exception.TouristVoucherException;
import org.xml.sax.Attributes;

import java.io.InputStream;
import java.util.List;

public interface SaxTouristVoucherParser  {
  List<TouristVoucher> parse(InputStream inputStream) throws TouristVoucherException;
}