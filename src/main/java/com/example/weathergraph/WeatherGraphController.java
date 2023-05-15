package com.example.weathergraph;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.Random;

/*
 Class WeatherGraphController represents a cash register and has a
 variety of methods that are used to operate the cash register.
 author Moshe Yakubov
 version 21/03/23
 */
public class WeatherGraphController {

    @FXML
    private Canvas canvas;

    @FXML
    private Label currentYear;
    private GraphicsContext gc;

    /*Declaration of instance variables*/
    private static int year = -1;
    private final int RECT_X_COORDINATE_BALANCE = 35;
    private final int RECT_Y_COORDINATE_BALANCE = 50;
    private final int LINE_COORDINATE_START = 30;
    private final int BALANCE_TEMP_COORDINATE = 5;
    private final int HORIZONTAL_LINE_BALANCE = 10;
    private final int VERTICAL_LINE_BALANCE = 25;
    private final int COLUMN_COORDINATE_BALANCE = 2;
    private final int RECT_WIDTH = 20;
    private final int MONTHS = 12;
    private final int DEGREES = 12;
    private int Temperatures1[] = {10, 32, 6, 4, 7, 22, 16, 28, 35, 2, 15, 24};
    private int Temperatures2[] = {14, 28, 7, 35, 26, 19, 20, 17, 20, 10, 36, 36};
    private int Temperatures3[] = {37, 22, 11, 11, 23, 29, 16, 25, 29, 32, 24, 4};
    private int Temperatures4[] = {10, 9, 8, 7, 6, 5, 28, 29, 40, 6, 5, 28};
    private int Temperatures5[] = {30, 1, 15, 30, 1, 15, 30, 1, 15, 30, 1, 15};
    private AnnualTemperature[] temp = {
            new AnnualTemperature(2017, Temperatures1),
            new AnnualTemperature(2018, Temperatures2),
            new AnnualTemperature(2019, Temperatures3),
            new AnnualTemperature(2020, Temperatures4),
            new AnnualTemperature(2021, Temperatures5)};
    private final int NUMBER_OF_YEARS = temp.length;

    /*The method prints the vertical axis and the horizontal axis.*/
    private void printAxisXAndY() {
        gc.strokeLine(LINE_COORDINATE_START, canvas.getHeight() - LINE_COORDINATE_START, LINE_COORDINATE_START, 0);
        gc.strokeLine(LINE_COORDINATE_START, canvas.getHeight() - LINE_COORDINATE_START, canvas.getWidth(), canvas.getHeight() - LINE_COORDINATE_START);
    }

    /*The method prints the temperatures on
    the vertical axis (in increments of 5).*/
    private void printTemperatures() {
        for (int i = 0; i <= DEGREES; i++) {
            gc.strokeText("" + (i * BALANCE_TEMP_COORDINATE), 0, canvas.getHeight() - i * VERTICAL_LINE_BALANCE - RECT_Y_COORDINATE_BALANCE);
        }
    }

    /*The method receives an index of the year of which the diagram is
    displayed, checks what the maximum average temperature is among all
    months in that year and returns it (the maximum temperature).*/
    private int findMaxTemp(int currentColumn) {
        int maxTemp = temp[currentColumn].getTemperatures()[0];
        for (int i = 0; i < MONTHS; i++) {
            if (maxTemp < temp[currentColumn].getTemperatures()[i])
                maxTemp = temp[currentColumn].getTemperatures()[i];
        }
        return maxTemp;
    }

    /*The method receives an index of the year of which the diagram is
    displayed, checks what the minimum average temperature is among all
    months in that year and returns it (the minimum temperature).*/
    private int findMinTemp(int currentColumn) {
        int minTemp = temp[currentColumn].getTemperatures()[0];
        for (int i = 0; i < MONTHS; i++) {
            if (minTemp > temp[currentColumn].getTemperatures()[i])
                minTemp = temp[currentColumn].getTemperatures()[i];
        }
        return minTemp;
    }

    /*The method receives an index of the year of which the diagram is displayed
    and shows the graphs (which printed with grey) showing the average temperatures
    of all the months of this year.*/
    private void printGraphsTemp(int currentColumn) {
        gc.setFill(Color.GREY);
        for (int i = 0; i < MONTHS; i++) {
            gc.fillRect(RECT_X_COORDINATE_BALANCE + RECT_WIDTH * i * COLUMN_COORDINATE_BALANCE, canvas.getHeight() - RECT_Y_COORDINATE_BALANCE - temp[currentColumn].getTemperatures()[i] * BALANCE_TEMP_COORDINATE, RECT_WIDTH, temp[currentColumn].getTemperatures()[i] * BALANCE_TEMP_COORDINATE);
            gc.strokeText("" + (i + 1), RECT_X_COORDINATE_BALANCE + RECT_WIDTH * i * COLUMN_COORDINATE_BALANCE, canvas.getHeight() - HORIZONTAL_LINE_BALANCE);
        }
    }


    /*
    The method receives an index of the year of which the diagram
    is displayed, the maximum and minimum temperature among the average
    temperature of each month in this year and colors in red all the
    graphs where the average temperature of the month is the maximum
    for that year. It also colors in blue all the graphs where the
    average temperature of the month is the minimum for that year.
    */
    private void printMinMaxGraph(int maxTemp, int minTemp, int currentColumn) {
        for (int i = 0; i < MONTHS; i++) {
            if (maxTemp == temp[currentColumn].getTemperatures()[i]) {
                gc.setFill(Color.RED);
                gc.fillRect(RECT_X_COORDINATE_BALANCE + RECT_WIDTH * i * COLUMN_COORDINATE_BALANCE, canvas.getHeight() - RECT_Y_COORDINATE_BALANCE - temp[currentColumn].getTemperatures()[i] * BALANCE_TEMP_COORDINATE, RECT_WIDTH, temp[currentColumn].getTemperatures()[i] * BALANCE_TEMP_COORDINATE);
            }
            if (minTemp == temp[currentColumn].getTemperatures()[i]) {
                gc.setFill(Color.BLUE);
                gc.fillRect(RECT_X_COORDINATE_BALANCE + RECT_WIDTH * i * COLUMN_COORDINATE_BALANCE, canvas.getHeight() - RECT_Y_COORDINATE_BALANCE - temp[currentColumn].getTemperatures()[i] * BALANCE_TEMP_COORDINATE, RECT_WIDTH, temp[currentColumn].getTemperatures()[i] * BALANCE_TEMP_COORDINATE);
            }
        }
    }

    /*This method initialize the value of gc*/
    public void initialize() {
        gc = canvas.getGraphicsContext2D();
    }

    /*The method performs all the actions that are performed after clicking
    the "Next" button that displays the graphs of the next year shown.*/
    @FXML
    void nextYearPressed(ActionEvent event) {
        int minTemp, maxTemp;
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        year++;
        int currentColumn = year % NUMBER_OF_YEARS;
        currentYear.setText(temp[currentColumn].getYear());
        printAxisXAndY();
        printTemperatures();
        maxTemp = findMaxTemp(currentColumn);
        minTemp = findMinTemp(currentColumn);
        printGraphsTemp(currentColumn);
        printMinMaxGraph(maxTemp, minTemp, currentColumn);
    }
}