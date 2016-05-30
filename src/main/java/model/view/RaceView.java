package model.view;

import service.DateConverter;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by ZENIT on 25.05.2016.
 */
public class RaceView {
    private int                         raceId;
    private String                      raceName;
    private Date                        raceDate;
    private HorseView                   horseWinner;
    private ArrayList<RaceLineView>     raceLineList = new ArrayList<>();

    public RaceView(int raceId, String raceName, Date raceDate, HorseView horseWinner, ArrayList<RaceLineView> raceLineList) {
        this.raceId = raceId;
        this.raceName = raceName;
        this.raceDate = raceDate;
        this.horseWinner = horseWinner;
        this.raceLineList = raceLineList;
    }

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public Date getRaceDate() {
        return raceDate;
    }

    public void setRaceDate(Date raceDate) {
        this.raceDate = raceDate;
    }

    public HorseView getHorseWinner() {
        return horseWinner;
    }

    public void setHorseWinner(HorseView horseWinner) {
        this.horseWinner = horseWinner;
    }

    public ArrayList<RaceLineView> getRaceLineList() {
        return raceLineList;
    }

    public void setRaceLineList(ArrayList<RaceLineView> raceLineList) {
        this.raceLineList = raceLineList;
    }

    public void addRaceLine (RaceLineView raceLineView) {
        raceLineList.add(raceLineView);
    }

    public boolean hasRaceLine () {
        return raceLineList.size() > 0;
    }

    public String getRaceFormatDate () {
        return DateConverter.converdDatetoStr(raceDate);
    }

    public String getHorseWinnerName() {
        return horseWinner.getHorseName();
    }

}
