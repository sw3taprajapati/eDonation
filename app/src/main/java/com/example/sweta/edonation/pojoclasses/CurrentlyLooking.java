package com.example.sweta.edonation.pojoclasses;

public class CurrentlyLooking {


    boolean food;
    boolean clothes;
    boolean books;
    boolean stationery;

    public CurrentlyLooking() {

    }

    public CurrentlyLooking(boolean food, boolean clothes, boolean books, boolean stationery) {
        this.food = food;
        this.clothes = clothes;
        this.books = books;
        this.stationery = stationery;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public boolean isClothes() {
        return clothes;
    }

    public void setClothes(boolean clothes) {
        this.clothes = clothes;
    }

    public boolean isBooks() {
        return books;
    }

    public void setBooks(boolean books) {
        this.books = books;
    }

    public boolean isStationery() {
        return stationery;
    }

    public void setStationery(boolean stationery) {
        this.stationery = stationery;
    }

}
