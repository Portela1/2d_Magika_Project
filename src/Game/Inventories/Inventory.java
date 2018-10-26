package Game.Inventories;

import Game.Items.Item;
import Game.SpellCast.FireBallSpell;
import Resources.Images;
import UI.UIInventory;
import UI.UIManager;
import Main.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by Elemental on 1/3/2017.
 */
public class Inventory {

    private Handler handler;
    private boolean active = false;
    private UIManager uiManager;
    private static ArrayList<Item> inventoryItems;
 
    public Inventory(Handler handler){

        this.handler=handler;
        inventoryItems = new ArrayList<>();

        uiManager = new UIManager(handler);

        uiManager.addObjects(new UIInventory(0,0, 329, 265, Images.inventory,() -> {
        }));
    }

    public void tick() {

        for(Item i : inventoryItems){
            if(i.getCount()==0){
                inventoryItems.remove(inventoryItems.indexOf(i));
                return;
            }
        }

        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_Q)){
            active=!active;
            handler.getWorld().getEntityManager().getPlayer().getSpellGUI().setActive(false);

        }

        if(!active){
            return;
        }
       
        

        handler.getMouseManager().setUimanager(uiManager);
        uiManager.tick();



    }

    public void render(Graphics g) {

        if(!active){
            uiManager.isActive(uiManager.getObjects(),false);
            return;
        }



        uiManager.isActive(uiManager.getObjects(),true);
        uiManager.Render(g);
        g.setColor(Color.white);
        renderItems(g);


    }

    //Inventory Methods
    private void renderItems(Graphics g) {
    	
    		int count1 = 0;
    		int count2 = 0;
    		int count3 = 0;
    		int count4 = 0;
    		
    		
    		for(int i = 0; i < inventoryItems.size(); i++) {
    			if(i<5) {
    			g.drawImage(inventoryItems.get(i).getTexture(), 25 + count1, 24, inventoryItems.get(i).getWidth(), inventoryItems.get(i).getHeight(), null);
    			g.drawString(String.valueOf(inventoryItems.get(i).getCount()), 25+count1+33,25+35);
    			count1+=61;
    		}else if(i<10) {
    			g.drawImage(inventoryItems.get(i).getTexture(), 25 + count2, 24+61, inventoryItems.get(i).getWidth(), inventoryItems.get(i).getHeight(), null);
    			g.drawString(String.valueOf(inventoryItems.get(i).getCount()), 25+count2+33,25+61+35);
    			count2+=61;
    		}else if(i<15) {
    			g.drawImage(inventoryItems.get(i).getTexture(), 25 + count3, 24+(2*61), inventoryItems.get(i).getWidth(), inventoryItems.get(i).getHeight(), null);
    			g.drawString(String.valueOf(inventoryItems.get(i).getCount()), 25+count3+33,25+(2*61)+35);
    			count3+=61;
    		}else if(i<20) {
    			g.drawImage(inventoryItems.get(i).getTexture(), 25 + count4, 24+(3*61), inventoryItems.get(i).getWidth(), inventoryItems.get(i).getHeight(), null);
    			g.drawString(String.valueOf(inventoryItems.get(i).getCount()), 25+count4+33,25+(3*61)+35);
    			count4+=61;
    		}}}

    public void addItem(Item item){
    	
        for(Item i : inventoryItems){
            if(i.getId() == item.getId()){
                i.setCount(i.getCount()+1);
               
                return;
            }
            
           
        }
        if(item.getId()==2){
            handler.getWorld().getEntityManager().getPlayer().getSpellGUI().addSpell(new FireBallSpell(handler));
        }
        inventoryItems.add(item);	
        if(item.getCount() == 0) {
        	item.setCount(item.getCount()+1);
        }
        
    }
    
    public static int ItemCount(Item item) {
    	int count = 0;
    	for (Item i : inventoryItems) {
    		if(i.getId() == item.getId()) {
    		count = i.getCount();
    		}
    	}
		return count;
    }
    
    public static void ItemSetCount(Item item, int set) {
    	for (Item i : inventoryItems) {
    		if(i.getId() == item.getId()) {
    			i.setCount(set);
    		}
    		}
    }

    //GET SET
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ArrayList<Item> getInventoryItems(){
        return inventoryItems;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
