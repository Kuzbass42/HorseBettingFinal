package model.view;

import service.DateConverter;

import java.util.Date;

/**
 * Created by ZENIT on 25.05.2016.
 */
public class OddView {
    private int          transId;
    private Date         transDate;
    private UserView     user;
    private double       amount;
    private RaceLineView raceLine;
    private RaceView     race;

    public OddView(int transId, Date transDate, UserView userId, double amount, RaceLineView raceLine, RaceView race) {
        this.transId = transId;
        this.transDate = transDate;
        this.user = userId;
        this.amount = amount;
        this.raceLine = raceLine;
        this.race = race;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public UserView getUser() {
        return user;
    }

    public void setUser(UserView userId) { this.user = userId; }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public RaceLineView getRaceLine() {
        return raceLine;
    }

    public void setRaceLine(RaceLineView raceLine) {
        this.raceLine = raceLine;
    }

    public RaceView getRace() {
        return race;
    }

    public void setRace(RaceView race) {
        this.race = race;
    }

    public String getOddFormatDate () {
        return DateConverter.converdDatetoStr(transDate);
    }
}
