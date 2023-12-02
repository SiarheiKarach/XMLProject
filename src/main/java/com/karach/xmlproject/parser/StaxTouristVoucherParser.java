package com.karach.xmlproject.parser;

import com.karach.xmlproject.exception.TouristVoucherException;
import com.karach.xmlproject.model.TouristVoucher;

import java.io.InputStream;
import java.util.List;

public interface StaxTouristVoucherParser {
  List<TouristVoucher> parse(InputStream inputStream) throws TouristVoucherException;
}

