package service;

import model.view.RaceView;

import java.util.Comparator;

public class RaceViewComparatorByRaceDate implements Comparator<RaceView> {
    @Override
    public int compare(RaceView o1, RaceView o2) {
        return o1.getRaceDate().compareTo(o2.getRaceDate());
    }
}
