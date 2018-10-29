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
public class NewWorld extends BaseWorld{
    private Handler handler;
    private Player player;
    
    
    
    
    public NewWorld(Handler handler, String path, Player player, SkelyEnemy skely, Companion compy) {
        super(handler,path,player, skely, compy);
        this.handler = handler;
        this.player=player;
        
        entityManager.addEntity(new SpeedRock(handler, 100, 200));
        
        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
        
        

        
        
        
       
    }


}