package Worlds;

import Game.Entities.EntityBase;
import Game.Entities.Creatures.Companion;
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

    public World1(Handler handler, String path, Player player, SkelyEnemy skely){
        super(handler,path,player, skely);
        this.handler = handler;
        caveWorld = new CaveWorld(handler,"res/Maps/caveMap.map",player, skely);

        
        entityManager.addEntity(new Door(handler, 175, 0,caveWorld));
        entityManager.addEntity(new FirstQuestHuman(handler, 75, 0));
        
        entityManager.addEntity(new Tree(handler, 1000, 250));
        entityManager.addEntity(new Rock(handler, 1000, 450));
        entityManager.addEntity(new Tree(handler, 533, 276));
        entityManager.addEntity(new Rock(handler, 684, 1370));
        entityManager.addEntity(new Tree(handler, 765, 888));
        entityManager.addEntity(new Rock(handler, 880, 1345));
        entityManager.addEntity(new Tree(handler, 770, 700));
        entityManager.addEntity(new Rock(handler, 700, 83));
  
        entityManager.addEntity(new FireRock(handler, 750, 83));
        entityManager.addEntity(new FireRock(handler, 1000, 83));
        
        entityManager.addEntity(new CoinRock(handler, 750, 300));
        entityManager.addEntity(new CoinRock(handler, 100, 600));
        entityManager.addEntity(new CoinRock(handler, 1000, 400));
        entityManager.addEntity(new CoinRock(handler, 1300, 1000));
        entityManager.addEntity(new CoinRock(handler, 50, 1000));
        
      
        
        
        entityManager.addEntity(new AtaRock(handler, 432, 543));
       
        
        
      
        entityManager.addEntity(new Companion(handler, 0, 0));
        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
        entityManager.getSkely().setX(1150);
        entityManager.getSkely().setY(100);
    }

}