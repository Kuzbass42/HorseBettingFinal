package dao.interfaces;

import model.RaceLine;
import model.view.RaceLineView;

import java.util.Map;

/**
 * Created by ZENIT on 23.05.2016.
 */
public interface RaceLineDao extends Dao {
    Map<Integer, RaceLine> getRaceLineMap();
    RaceLineView getRaceLinebyId(int raceId, int lineNum);

    int insert(RaceLine raceLine);
    int update(RaceLine raceLine);
    int delete(int raceId, int lineNum);
}
