package com.karach.xmlproject.model;

public enum XmlElement {
  TOURIST_VOUCHER("TouristVoucher"),
  TYPE("Type"),
  COUNTRY("Country"),
  MIN_DAYS("MinDays"),
  MAX_DAYS("MaxDays"),
  TRANSPORT("Transport"),
  HOTEL_CHARACTERISTICS("HotelCharacteristics"),
  STARS("Stars"),
  FOOD("Food"),
  ROOM_TYPE("RoomType"),
  AMENITIES("Amenities"),
  COST("Cost"),
  CURRENCY("currency"),
  START_DATE("StartDate"),
  ID("id");

  @Override
  public String toString() {
    return "XmlElement{" +
            "value='" + value + '\'' +
            '}';
  }

  private final String value;

  XmlElement(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}