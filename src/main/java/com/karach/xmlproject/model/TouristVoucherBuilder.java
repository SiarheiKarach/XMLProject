package com.karach.xmlproject.model;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
    touristVoucher.setID(random.nextInt(1,1000));
    return this;
  }

  public TouristVoucherBuilder setCurrentDateTime() {
    ZonedDateTime now = Instant.now().atZone(ZoneId.systemDefault());
    touristVoucher.setStartDate(now.format(TouristVoucher.getDateTimeFormatter()));
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

  public TouristVoucherBuilder setMinDays(int minDays) {
    touristVoucher.setMinDays(minDays);
    return this;
  }

  public TouristVoucherBuilder setMaxDays(int maxDays) {
    touristVoucher.setMaxDays(maxDays);
    return this;
  }

  public TouristVoucherBuilder setTransport(String transport) {
    touristVoucher.setTransport(transport);
    return this;
  }

  public TouristVoucherBuilder setStars(int stars) {
    touristVoucher.setStars(stars);
    return this;
  }

  public TouristVoucherBuilder setFood(String food) {
    touristVoucher.setFood(food);
    return this;
  }

  public TouristVoucherBuilder setRoomType(RoomType roomType) {
    touristVoucher.setRoomType(roomType);
    return this;
  }

  public TouristVoucherBuilder setAmenities(String amenities) {
    touristVoucher.setAmenities(amenities);
    return this;
  }

  public TouristVoucherBuilder setCost(float cost) {
    touristVoucher.setCost(cost);
    return this;
  }

  public TouristVoucherBuilder setCurrency(Currency currency) {
    touristVoucher.setCurrency(currency);
    return this;
  }

  public TouristVoucher build() {
    return touristVoucher;
  }
}