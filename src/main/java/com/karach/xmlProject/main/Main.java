package com.karach.xmlProject.main;
import com.karach.xmlProject.creator.TouristVoucherXMLCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
  private static final Logger logger = LogManager.getLogger();

  public static void main(String[] args) {
    TouristVoucherXMLCreator.createXmlDocument();
  }
}