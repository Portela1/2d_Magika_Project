package Game.Entities.Statics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import Game.Entities.Creatures.Player;
import Game.Inventories.Inventory;

import Game.Items.Item;
import Main.Handler;
import Resources.Images;

/**
 * Created by Elemental on 2/2/2017.
 */


public class SecondQuestHuman extends StaticEntity {

    private Rectangle ir = new Rectangle();
    public Boolean EP = false;
    public Boolean doorvis = false;
    public static Boolean stat = false;
    private int rocks = 3;
    private int sCoins = 2;

    public SecondQuestHuman(Handler handler, float x, float y) {
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
            g.drawString("Press E to Give", (int) x+width,(int) y+50);
            g.drawImage(Images.Coin, (int )x+width+18,(int) y+82, 40, 40, null);
            g.drawString(String.valueOf(rocks), (int) x+width+18+33,(int) y+135);
            g.drawImage(Images.key, (int )x+width+18+56,(int) y+82, 40, 40, null);
            g.drawString(String.valueOf(sCoins), (int) x+width+18+33+56,(int) y+135);
            if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)){
            	if(Inventory.ItemCount(Item.rockItem) >= 3 && rocks > 0) {
            		rocks -= 3;
            		Inventory.ItemSetCount(Item.rockItem, Inventory.ItemCount(Item.rockItem) - 3);
            	}
            	else if (rocks > 0){
            		rocks -= Inventory.ItemCount(Item.rockItem);
            		Inventory.ItemSetCount(Item.coin, 0);
            	}
            	
            
               	if(Inventory.ItemCount(Item.LessDame) >= 1 && sCoins > 0) {
                	sCoins -= 1;
                	Inventory.ItemSetCount(Item.LessDame, Inventory.ItemCount(Item.LessDame) - 1);
                }
                else if (sCoins > 0){
                	sCoins -= Inventory.ItemCount(Item.LessDame);
                	Inventory.ItemSetCount(Item.LessDame, 0);
                }	
            		
            		
            }     	
            if(rocks < 0) { rocks = 0; }
            if(sCoins < 0) { sCoins = 0; }
            
            
            if(rocks == 0 && rocks == 0) {
            	stat = true;
            }
           
            
            
            
            
            }}
       

    

	@Override
	public void die() {
		
		
	}

    
    
    
   

   
    
}