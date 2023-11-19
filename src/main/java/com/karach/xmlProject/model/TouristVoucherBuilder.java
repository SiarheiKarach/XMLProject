package com.karach.xmlProject.model;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class TouristVoucherBuilder {
  private TouristVoucher touristVoucher;

  public TouristVoucherBuilder() {
    this.touristVoucher = new TouristVoucher();
  }

  public TouristVoucher getTouristVoucher() {
    return touristVoucher;
  }

  public void setTouristVoucher(TouristVoucher touristVoucher) {
    this.touristVoucher = touristVoucher;
  }

  public TouristVoucherBuilder generateRandomId() {
    Random random = new Random();
    touristVoucher.setId(String.valueOf(random.nextInt(1, 1000)));
    return this;

  }

  public TouristVoucherBuilder setCurrentDateTime() {
    ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
    DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
    touristVoucher.setStartDate(formatter.format(zonedDateTime));
    return this;
  }

  public TouristVoucherBuilder setType(String type) {
    touristVoucher.setType(type);
    return this;
  }

  public TouristVoucherBuilder setCountry(String country) {
    touristVoucher.setCountry(country);
    return this;
  }

  public TouristVoucherBuilder setDaysNights (String minDays, String maxDays) {
    touristVoucher.setDaysNights(minDays);
    return this;
  }

  public TouristVoucherBuilder setTransport(String transport) {
    touristVoucher.setTransport(transport);
    return this;
  }

  public TouristVoucherBuilder setStars(String stars) {
    touristVoucher.setStars(stars);
    return this;
  }

  public TouristVoucherBuilder setFood(String food) {
    touristVoucher.setFood(food);
    return this;
  }

  public TouristVoucherBuilder setRoomType(String roomType) {
    touristVoucher.setRoomType(roomType);
    return this;
  }

  public TouristVoucherBuilder setAmenities(String amenities) {
    touristVoucher.setAmenities(amenities);
    return this;
  }

  public TouristVoucherBuilder setCost(String cost) {
    touristVoucher.setCost(cost);
    return this;
  }

  public TouristVoucherBuilder setCurrency(String currency) {
    touristVoucher.setCurrency(currency);
    return this;

  }
  public TouristVoucher build() {
    return touristVoucher;
  }
}