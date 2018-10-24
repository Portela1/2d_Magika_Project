package Game.Entities.Statics;

import Game.Entities.Creatures.Player;
import Game.Entities.Creatures.SkelyEnemy;
import Game.GameStates.State;
import Game.Inventories.Inventory;
import Game.Inventories.Quest1Inv;
import Game.Items.Item;
import Main.Handler;
import Resources.Images;
import Worlds.BaseWorld;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by Elemental on 2/2/2017.
 */


public class FirstQuestHuman extends StaticEntity {

    private Rectangle ir = new Rectangle();
    public Boolean EP = false;
    public Boolean doorvis = false;
    private Quest1Inv inventory;
    private int coins = 3;

    public FirstQuestHuman(Handler handler, float x, float y) {
        super(handler, x, y, 64, 100);
     
        health=10000000;
        bounds.x=0;
        bounds.y=0;
        bounds.width = 100;
        bounds.height = 64;

        ir.width = bounds.width;
        ir.height = bounds.height;
        int irx=(int)(bounds.x-handler.getGameCamera().getxOffset()+x);
        int iry= (int)(bounds.y-handler.getGameCamera().getyOffset()+height);
        ir.y=iry;
        ir.x=irx;
        
        inventory = new Quest1Inv(handler);

    }

    @Override
    public void tick() {

        if(isBeinghurt()){
            setHealth(10000000);
        }

        if(handler.getKeyManager().attbut){
            EP=true;

        }else if(!handler.getKeyManager().attbut){
            EP=false;
        }
        inventory.tick();
        
    }

    @Override
    public void render(Graphics g) {
 
    	
        g.drawImage(Images.quest1,(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);
    	
        g.setColor(Color.black);
        checkForPlayer(g, handler.getWorld().getEntityManager().getPlayer());
    	
    }

    private void checkForPlayer(Graphics g, Player p) {
        Rectangle pr = p.getCollisionBounds(0,0);
        
       if(ir.contains(pr)){
            g.drawImage(Images.inventory,(int) x+width,(int) y+60,300,300,null);
            g.setFont(new Font("ComicSans", Font.BOLD, 20));
            g.setColor(Color.CYAN);
            g.drawString("You are missing", (int) x+width,(int) y+20);
            g.drawImage(Images.Coin, (int )x+width+18,(int) y+82, 40, 40, null);
            g.drawString(String.valueOf(coins), (int) x+width+18+33,(int) y+135);
            
       }
       

    }

    @Override
    public void die() {

    }
    
    
    public Quest1Inv getInventory() {
        return inventory;
    }

    public void setInventory(Quest1Inv inventory) {
        this.inventory = inventory;
    }
    
}