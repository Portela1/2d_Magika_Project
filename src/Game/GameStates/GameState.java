package Game.GameStates;

import Game.Entities.Creatures.Companion;
import Game.Entities.Creatures.FinalBoss;
import Game.Entities.Creatures.Player;
import Game.Entities.Creatures.SkelyEnemy;
import Main.Handler;
import Worlds.BaseWorld;
import Worlds.World1;

import java.awt.*;

/**
 * Created by Elemental on 12/10/2016.
 */
public class GameState extends State {

    public GameState(Handler handler){
        super(handler);
        Player player = new Player(handler, 100, 100);
        SkelyEnemy skely= new SkelyEnemy(handler, 100, 100);
        Companion compy= new Companion(handler, 0, 0);
        FinalBoss bossy= new FinalBoss(handler, 5000, 5000);
        BaseWorld world = new World1(handler, "res/Maps/map1.map", player, skely, compy, bossy);
        handler.setWorld(world);
        handler.getWorld().getEntityManager().setPlayer(player);
        
    }


    @Override
    public void tick() {
        handler.getWorld().tick();

    }

    @Override
    public void render(Graphics g) {
        handler.getWorld().render(g);

    }

}
