package model;

import model.view.RaceView;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by ZENIT on 23.05.2016.
 */
public class Race {
    private int     raceId;
    private String  raceName;
    private Date    raceDate;
    private int     raceWinner;

    public Race(String raceName, Date raceDate) {
        this.raceId = 0;
        this.raceName = raceName;
        this.raceDate = raceDate;
        this.raceWinner = 0;
    }

    public Race(String raceName, Date raceDate, int raceWinner) {
        this.raceId = 0;
        this.raceName = raceName;
        this.raceDate = raceDate;
        this.raceWinner = raceWinner;
    }

    public Race(int raceId, String raceName, Date raceDate, int raceWinner) {
        this.raceId = raceId;
        this.raceName = raceName;
        this.raceDate = raceDate;
        this.raceWinner = raceWinner;
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

    public int getRaceWinner() {
        return raceWinner;
    }

    public void setRaceWinner(int raceWinner) {
        this.raceWinner = raceWinner;
    }

    public static Race newInstanceFromRaceView (RaceView raceView) {
        return new Race(raceView.getRaceId(), raceView.getRaceName(), raceView.getRaceDate(), raceView.getHorseWinner().getHorseId());
    };
}
