package com.karach.xmlproject.model;

import java.time.format.DateTimeFormatter;

public class TouristVoucher {
  private int ID;
  private String type;
  private String country;
  private int minDays;
  private int maxDays;
  private String transport;
  private int stars;
  private String food;
  private RoomType roomType;
  private String amenities;
  private float cost;
  private Currency currency;
  private String startDate;

  public static DateTimeFormatter getDateTimeFormatter() {
    return DateTimeFormatter.ISO_INSTANT;
  }

  public void setHotelCharacteristics(int stars, String food, RoomType roomType, String amenities) {
    this.stars = stars;
    this.food = food;
    this.roomType = roomType;
    this.amenities = amenities;
  }

  public int getID() {
    return ID;
  }

  public void setID(int ID) {
    this.ID = ID;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public int getMinDays() {
    return minDays;
  }

  public void setMinDays(int minDays) {
    this.minDays = minDays;
  }

  public int getMaxDays() {
    return maxDays;
  }

  public void setMaxDays(int maxDays) {
    this.maxDays = maxDays;
  }

  public String getTransport() {
    return transport;
  }

  public void setTransport(String transport) {
    this.transport = transport;
  }

  public int getStars() {
    return stars;
  }

  public void setStars(int stars) {
    this.stars = stars;
  }

  public String getFood() {
    return food;
  }

  public void setFood(String food) {
    this.food = food;
  }

  public RoomType getRoomType() {
    return roomType;
  }

  public void setRoomType(RoomType roomType) {
    this.roomType = roomType;
  }

  public String getAmenities() {
    return amenities;
  }

  public void setAmenities(String amenities) {
    this.amenities = amenities;
  }

  public float getCost() {
    return cost;
  }

  public void setCost(float cost) {
    this.cost = cost;
  }

  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  @Override
  public String toString() {
    return "TouristVoucher{" +
            "ID=" + ID +
            ", type='" + type + '\'' +
            ", country='" + country + '\'' +
            ", minDays=" + minDays +
            ", maxDays=" + maxDays +
            ", transport='" + transport + '\'' +
            ", stars=" + stars +
            ", food='" + food + '\'' +
            ", roomType=" + roomType +
            ", amenities='" + amenities + '\'' +
            ", cost=" + cost +
            ", currency=" + currency +
            ", startDate='" + startDate + '\'' +
            '}';
  }
}