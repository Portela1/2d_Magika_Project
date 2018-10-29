package Game.Entities.Creatures;

import Game.Entities.EntityBase;
import Game.Inventories.Inventory;
import Game.Items.Item;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.util.Random;

/**
 * Created by Elemental on 2/7/2017.
 */
public class SecondEnemy extends SkelyEnemy  {


    private Animation animDown, animUp, animLeft, animRight;
    private Boolean attacking=false;
    public static Boolean stat = false;
    private int animWalkingSpeed = 150;
    private Inventory Skelyinventory;
    private Rectangle SkelyCam;
    private int healthcounter = 0;
    private Random randint;
    private int moveCount=0;
    private int direction;
    
    

    public SecondEnemy(Handler handler, float x, float y) {
        super(handler, x, y);
        bounds.x=8*2;
        bounds.y=18*2;
        bounds.width=16*2;
        bounds.height=14*2;
        speed=1.5f;
        health=50;

        SkelyCam= new Rectangle();
        randint = new Random();
        direction = randint.nextInt(4) + 1;
        animDown = new Animation(animWalkingSpeed, Images.SkelyEnemy_front);
        animLeft = new Animation(animWalkingSpeed,Images.SkelyEnemy_left);
        animRight = new Animation(animWalkingSpeed,Images.SkelyEnemy_right);
        animUp = new Animation(animWalkingSpeed,Images.SkelyEnemy_back);

        Skelyinventory= new Inventory(handler);
    }

   




    @Override
    public void die() {
    	 handler.getWorld().getItemManager().addItem(Item.CompPower.createNew((int)x + bounds.x,(int)y + bounds.y,1));
    	 stat = true;
    }
}
