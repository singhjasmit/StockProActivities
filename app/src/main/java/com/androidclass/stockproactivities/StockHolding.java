package com.androidclass.stockproactivities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jsingh on 8/10/16.
 */
public class StockHolding implements Parcelable{

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public StockHolding createFromParcel(Parcel in) {
            return new StockHolding(in);
        }

        public StockHolding[] newArray(int size) {
            return new StockHolding[size];
        }
    };
    String symbol;
    int qty;

    public StockHolding(Parcel in) {

        this.symbol = in.readString();
        this.qty = in.readInt();

    }

    public StockHolding(String symbol, int qty) {
        this.symbol = symbol;
        this.qty = qty;
    }

    public StockHolding() {

    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.symbol);
        parcel.writeInt(this.qty);

    }

    @Override
    public String toString() {
        return "StockHolding{" +
                "symbol='" + symbol + '\'' +
                ", qty=" + qty +
                '}';
    }
}
