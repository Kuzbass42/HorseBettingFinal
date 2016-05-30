package model.view;

/**
 * Created by ZENIT on 25.05.2016.
 */
public class RaceLineView {
    private int         raceId;
    private int         lineNum;
    private HorseView   horse;
    private double      odd;

    public RaceLineView(int raceId, int lineNum, HorseView horse, double odd) {
        this.raceId = raceId;
        this.lineNum = lineNum;
        this.horse = horse;
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

    public HorseView getHorse() {
        return horse;
    }

    public void setHorse(HorseView horse) {
        this.horse = horse;
    }

    public double getOdd() {
        return odd;
    }

    public void setOdd(double odd) {
        this.odd = odd;
    }

    public String getHorseName() {
        return horse.getHorseName();
    }
    public int getHorseId() {
        return horse.getHorseId();
    }
}
