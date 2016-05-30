package model;

import java.util.Date;

/**
 * Created by ZENIT on 23.05.2016.
 */
public class Odd {
    private int     transId;
    private Date    transDate;
    private int     userId;
    private double  amount;
    private int     raceId;
    private int     lineNum;

    public Odd(Date transDate, int userId, double amount, int raceId, int lineNum) {
        this.transId = 0;
        this.transDate = transDate;
        this.userId = userId;
        this.amount = amount;
        this.raceId = raceId;
        this.lineNum = lineNum;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
}
