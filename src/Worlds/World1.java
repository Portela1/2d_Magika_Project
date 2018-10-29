package Worlds;

import Game.Entities.EntityBase;
import Game.Entities.Creatures.Companion;
import Game.Entities.Creatures.FinalBoss;
import Game.Entities.Creatures.Player;
import Game.Entities.Creatures.SkelyEnemy;
import Game.Entities.Creatures.SecondEnemy;
import Game.Entities.Statics.*;
import Main.Handler;

/**
 * Created by Elemental on 1/2/2017.
 */
public class World1 extends BaseWorld{

    private Handler handler;
    public static BaseWorld caveWorld;

    public World1(Handler handler, String path, Player player, SkelyEnemy skely, Companion compy){
        super(handler,path,player, skely, compy);
        this.handler = handler;
        caveWorld = new CaveWorld(handler,"res/Maps/caveMap.map",player, skely, compy);

        
        entityManager.addEntity(new Door(handler, 175, 0,caveWorld));
        entityManager.addEntity(new FirstQuestHuman(handler, 75, 0));
        
       
  
        entityManager.addEntity(new FireRock(handler, 750, 83));
        entityManager.addEntity(new FireRock(handler, 1000, 1000));
        
        entityManager.addEntity(new CoinRock(handler, 750, 300));
        entityManager.addEntity(new CoinRock(handler, 100, 600));
        entityManager.addEntity(new CoinRock(handler, 1000, 400));
        entityManager.addEntity(new CoinRock(handler, 1300, 1000));
        entityManager.addEntity(new CoinRock(handler, 50, 1000));
        
        entityManager.addEntity(new FinalBoss(handler, 200, 500));
        
        
        entityManager.addEntity(new AtaRock(handler, 432, 543));
       
        
        
        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
        entityManager.getSkely().setX(1150);
        entityManager.getSkely().setY(100);
    }

}