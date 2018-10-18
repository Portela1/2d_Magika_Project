package Worlds;
import Game.Entities.Creatures.Player;
import Game.Entities.Creatures.SecondEnemy;
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

    public CaveWorld(Handler handler, String path, Player player) {
        super(handler,path,player);
        this.handler = handler;
        this.player=player;
       
        
        
        
        entityManager.addEntity(new Tree(handler, 100, 250));
        entityManager.addEntity(new Rock(handler, 100, 450));
        entityManager.addEntity(new Tree(handler, 533, 276));
        entityManager.addEntity(new Rock(handler, 684, 1370));
        entityManager.addEntity(new Tree(handler, 765, 888));
        entityManager.addEntity(new Rock(handler, 88, 1345));
        entityManager.addEntity(new Tree(handler, 77, 700));
        entityManager.addEntity(new Rock(handler, 700, 83));
        entityManager.addEntity(new SecondEnemy(handler, 1250, 200));

        
        
        
        
        entityManager.addEntity(new FireRock(handler, 750, 83));
        entityManager.addEntity(new FireRock(handler, 1000, 83));
        
        entityManager.addEntity(new CoinRock(handler, 750, 300));
        entityManager.addEntity(new CoinRock(handler, 750, 400));
        
        entityManager.addEntity(new SpeedRock(handler, 234, 122));
        
        
        entityManager.addEntity(new AtaRock(handler, 432, 543));
       
        
        entityManager.addEntity(new DefRock(handler, 342, 343));
        entityManager.addEntity(new DefRock(handler, 543, 432));
    }


}