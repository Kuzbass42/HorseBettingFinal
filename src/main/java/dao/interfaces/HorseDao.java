package dao.interfaces;

import model.Horse;
import model.view.HorseView;

import java.util.ArrayList;

/**
 * Created by ZENIT on 23.05.2016.
 */
public interface HorseDao extends Dao {
    ArrayList<HorseView> getHorsesList();
    HorseView getHorsebyId(int id);
    int insert(Horse horse);
    int update(Horse horse);
    int delete(int horseId);
}
