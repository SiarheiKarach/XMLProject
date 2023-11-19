package com.karach.xmlProject.parser.impl;

import com.karach.xmlProject.model.TouristVoucher;
import com.karach.xmlProject.parser.TouristVoucherParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.InputStream;
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

      // Реализация извлечения данных из XML и создания объектов TouristVoucher
      // ...

      // Пример: Добавление фиктивного объекта TouristVoucher в список
      TouristVoucher voucher = new TouristVoucher();
      vouchers.add(voucher);

    } catch (ParserConfigurationException e) {
      logger.error("Error creating DocumentBuilder", e);
    } catch (Exception e) {
      logger.error("Error parsing XML", e);
    }

    return vouchers;
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
