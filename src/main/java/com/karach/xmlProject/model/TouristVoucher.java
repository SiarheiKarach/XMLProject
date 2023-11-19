package com.karach.xmlProject.model;

import java.time.format.DateTimeFormatter;

public class TouristVoucher {
  private String id;
  private String type;
  private String country;
  private String minDays;
  private String maxDays;
  private String transport;
  private String stars;
  private String food;
  private String roomType;
  private String amenities;
  private String cost;
  private String currency;
  private String startDate;

  // Геттеры и сеттеры

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public String getMinDays() {
    return minDays;
  }

  public void setMinDays(String minDays) {
    this.minDays = minDays;
  }

  public String getMaxDays() {
    return maxDays;
  }

  public void setMaxDays(String maxDays) {
    this.maxDays = maxDays;
  }

  public String getTransport() {
    return transport;
  }

  public void setTransport(String transport) {
    this.transport = transport;
  }

  public String getStars() {
    return stars;
  }

  public void setStars(String stars) {
    this.stars = stars;
  }

  public String getFood() {
    return food;
  }

  public void setFood(String food) {
    this.food = food;
  }

  public String getRoomType() {
    return roomType;
  }

  public void setRoomType(String roomType) {
    this.roomType = roomType;
  }

  public String getAmenities() {
    return amenities;
  }

  public void setAmenities(String amenities) {
    this.amenities = amenities;
  }

  public String getCost() {
    return cost;
  }

  public void setCost(String cost) {
    this.cost = cost;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
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
            "id='" + id + '\'' +
            ", type='" + type + '\'' +
            ", country='" + country + '\'' +
            ", minDays='" + minDays + '\'' +
            ", maxDays='" + maxDays + '\'' +
            ", transport='" + transport + '\'' +
            ", stars='" + stars + '\'' +
            ", food='" + food + '\'' +
            ", roomType='" + roomType + '\'' +
            ", amenities='" + amenities + '\'' +
            ", cost='" + cost + '\'' +
            ", currency='" + currency + '\'' +
            ", startDate='" + startDate + '\'' +
            '}';
  }
}