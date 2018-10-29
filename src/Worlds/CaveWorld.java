package Worlds;
import Game.Entities.Creatures.Companion;
import Game.Entities.Creatures.Player;
import Game.Entities.Creatures.SecondEnemy;
import Game.Entities.Creatures.SkelyEnemy;
import Game.Entities.Statics.AtaRock;
import Game.Entities.Statics.CoinRock;
import Game.Entities.Statics.DefRock;
import Game.Entities.Statics.Door;
import Game.Entities.Statics.FireRock;
import Game.Entities.Statics.Rock;
import Game.Entities.Statics.SpeedRock;
import Game.Entities.Statics.Tree;
import Main.Handler;

/**
 * Created by Elemental on 2/10/2017.
 */
public class CaveWorld extends BaseWorld{
    private Handler handler;
    private Player player;
    public static BaseWorld newWorld;
    
    public CaveWorld(Handler handler, String path, Player player, SkelyEnemy skely) {
        super(handler,path,player, skely);
        this.handler = handler;
        this.player=player;
        
        newWorld = new NewWorld(handler,"res/Maps/newWorld.map",player, skely);
        
        entityManager.addEntity(new Door(handler, 800, 0,newWorld));
        
        entityManager.addEntity(new SecondEnemy(handler, 1250, 200));
        
        
        entityManager.addEntity(new DefRock(handler, 300, 600));
        entityManager.addEntity(new DefRock(handler, 100, 900));
        
        
        
        
        
       
    }


}