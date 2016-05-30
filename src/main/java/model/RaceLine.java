package model;

import model.view.RaceLineView;

/**
 * Created by ZENIT on 23.05.2016.
 */
public class RaceLine {
    private int     raceId;
    private int     lineNum;
    private int     horseId;
    private double  odd;

    public RaceLine(int raceId, int horseId, double odd) {
        this.raceId = raceId;
        this.lineNum = 0;
        this.horseId = horseId;
        this.odd = odd;
    }

    public RaceLine(int raceId, int lineNum, int horseId, double odd) {
        this.raceId = raceId;
        this.lineNum = lineNum;
        this.horseId = horseId;
        this.odd = odd;
    }

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public int getLineNum() {
        return lineNum;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }

    public int getHorseId() {
        return horseId;
    }

    public void setHorseId(int horseId) {
        this.horseId = horseId;
    }

    public double getOdd() {
        return odd;
    }

    public void setOdd(double odd) {
        this.odd = odd;
    }

    public static RaceLine newInstanceFromRaceLineView (RaceLineView raceLineView) {
        return new RaceLine(raceLineView.getRaceId(), raceLineView.getLineNum(), raceLineView.getHorse().getHorseId(), raceLineView.getOdd());
    };
}
