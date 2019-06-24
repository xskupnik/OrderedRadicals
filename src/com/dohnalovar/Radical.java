package com.dohnalovar;

/**
 * Created by dohnalovar on 6/24/2019
 */
public class Radical implements Comparable {

    private int Number;
    private int Value;


    public Radical(int number, int value) {
        Number = number;
        Value = value;
    }


    public int getNumber() {
        return Number;
    }

    public int getValue() {
        return Value;
    }

    @Override
    public int compareTo(Object o) {
        Radical other = (Radical)o;
        if (this.Value < other.getValue()) return -1;
        if (this.Value > other.getValue()) return 1;

        if (this.Number < other.getNumber()) return -1;
        if (this.Number > other.getNumber()) return 1;

        return 0;
    }

    @Override
    public String toString() {
        return Number + " [rad("+Number+") = " + Value + "]";
    }
}
