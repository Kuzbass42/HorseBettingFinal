package dao.interfaces;

import model.Odd;
import model.view.HorseView;
import model.view.OddView;

import java.util.ArrayList;

/**
 * Created by ZENIT on 23.05.2016.
 */
public interface OddDao extends Dao {
    ArrayList<OddView> getOddListByUser(int userId);
    ArrayList<OddView> getOddList();

    int insert(Odd odd);
}
