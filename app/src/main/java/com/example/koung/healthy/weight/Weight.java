package com.example.koung.healthy.weight;

public class Weight {
    private String date;
    private Double weight;
    private String status;

    public Weight() {
    }

    public Weight(String date, Double weight, String status) {

        this.date = date;
        this.weight = weight;
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
