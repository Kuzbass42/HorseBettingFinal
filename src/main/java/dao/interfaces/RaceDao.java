package dao.interfaces;

import model.Race;
import model.view.HorseView;
import model.view.RaceView;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ZENIT on 23.05.2016.
 */
public interface RaceDao extends Dao {
    ArrayList<RaceView> getRaceList(Date fromDate);
    RaceView getRacebyId(int id);
    ArrayList<HorseView> getHorsesListByRaceId(int raceId);

    int insert(Race race);
    int update(Race race);
    int delete(int raceId);
}
