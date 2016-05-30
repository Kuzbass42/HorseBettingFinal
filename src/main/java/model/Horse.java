package model;

import model.view.HorseView;

/**
 * Created by ZENIT on 23.05.2016.
 */
public class Horse {
    private int     horseId;
    private String  horseName;
    private String  color;

    public Horse(String horseName, String color) {
        this.horseName = horseName;
        this.color = color;
    }

    public Horse(int horseId, String horseName, String color) {
        this.horseId = horseId;
        this.horseName = horseName;
        this.color = color;
    }

    public int getHorseId() {
        return horseId;
    }

    public void setHorseId(int horseId) {
        this.horseId = horseId;
    }

    public String getHorseName() {
        return horseName;
    }

    public void setHorseName(String horseName) {
        this.horseName = horseName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public static Horse newInstanceFromHorseView (HorseView horseView) {
        return new Horse(horseView.getHorseId(), horseView.getHorseName(), horseView.getColor());
    };
}
