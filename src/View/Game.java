package View;

import Model.Player;

import javax.swing.plaf.basic.BasicTreeUI;
import java.io.Serializable;

public class Game implements Serializable {
    Player myPlayer;
    PlayerManager myPlayerManager;
    QuestionRecord myQuesitonRecord;
    AssignTileToObject myAssignTiler;
    Keyboard myKeyboard;

    public Game(final GamePanel theGame){


    }

}
