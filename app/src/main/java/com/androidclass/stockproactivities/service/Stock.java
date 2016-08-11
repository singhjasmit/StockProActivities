package com.androidclass.stockproactivities.service;

/**
 * Created by jsingh on 8/10/16.
 */
public class Stock  {




    public Stock(String symbol, String name, double price, double change, double high, double low, long volume) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
        this.change = change;
        this.high = high;
        this.low = low;
        this.volume = volume;
    }


    private String symbol;
    private String name;
    private double price;
    private double change;
    private double high;
    private double low;
    private String info;
    private long volume;



    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }


}
