package com.example.weathergraph;

import java.util.Random;

/*
Class AnnualTemperature stores all the average temperatures in a particular year.
 */
public class AnnualTemperature {

    /*Declaration of instance variables*/
    private int year;
    private int[] Temperatures;

    /*
     Construct a new AnnualTemperature.
     The constructor gets an int parameter represents the year its average temperatures are shown
     and an array of int that stores the average temperatures of each month in this year.
     */
    public AnnualTemperature(int year, int[] Temperatures) {
        this.year = year;
        this.Temperatures = Temperatures;
    }

    /*The method returns an array of int that stores the
    average temperatures of each month in the current year.*/
    public int[] getTemperatures() {
        return this.Temperatures;
    }

    /*The method returns the current year.*/
    public String getYear() {
        return "" + this.year;
    }
}

