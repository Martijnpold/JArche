package com.mpolder.jarche.model;

import com.google.gson.annotations.SerializedName;

public class Flower {
    private String name;
    @SerializedName("round_length")
    private int roundLength;
    @SerializedName("start_price")
    private double startPrice;

    public Flower() {
    }

    public Flower(String name, int roundLength, double startPrice) {
        this.name = name;
        this.roundLength = roundLength;
        this.startPrice = startPrice;
    }

    public String getName() {
        return name;
    }

    public int getRoundLength() {
        return roundLength;
    }

    public double getStartPrice() {
        return startPrice;
    }

    public boolean validate() {
        boolean valid = this.name != null && !this.name.equals("");
        valid &= roundLength > 0;
        valid &= startPrice > 0;
        return valid;
    }
}
