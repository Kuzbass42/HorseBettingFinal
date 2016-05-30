package model.view;

/**
 * Created by ZENIT on 25.05.2016.
 */
public class HorseView {
    private int     horseId;
    private String  horseName;
    private String  color;

    public HorseView(String horseName, String color) {
        this.horseId = 0;
        this.horseName = horseName;
        this.color = color;
    }

    public HorseView(int horseId, String horseName, String color) {
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
}