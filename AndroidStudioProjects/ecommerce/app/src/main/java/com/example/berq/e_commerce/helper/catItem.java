package com.example.berq.e_commerce.helper;

public class catItem {
    int background;
    int catNumber;

    public int getCatNumber() {
        return catNumber;
    }

    public void setCatNumber(int catNumber) {
        this.catNumber = catNumber;
    }

    public catItem(int background, int catNumber) {
        this.background = background;
        this.catNumber = catNumber;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }
}
