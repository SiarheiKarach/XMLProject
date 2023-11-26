package com.karach.xmlproject.model;
import com.karach.xmlproject.model.Type;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class TouristVoucher {
  private int id;
  private Type type;
  private String country;
  private int minDays;
  private int maxDays;
  private Transport transport;
  private Stars stars;
  private Food food;
  private RoomType roomType;
  private String amenities;
  private float cost;
  private Currency currency;
  private String startDate;

  public static DateTimeFormatter getDateTimeFormatter() {
    return DateTimeFormatter.ISO_INSTANT;
  }

  public void setHotelCharacteristics(Stars stars, Food food, RoomType roomType, String amenities) {
    this.stars = stars;
    this.food = food;
    this.roomType = roomType;
    this.amenities = amenities;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
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

  public Transport getTransport() {
    return transport;
  }

  public void setTransport(Transport transport) {
    this.transport = transport;
  }

  public Stars getStars() {
    return stars;
  }

  public void setStars(Stars stars) {
    this.stars = stars;
  }

  public Food getFood() {
    return food;
  }

  public void setFood(Food food) {
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
            "id=" + id +
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