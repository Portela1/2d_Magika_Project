package Game.Entities.Creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;

import Game.Entities.EntityBase;
import Game.Inventories.Inventory;
import Game.Items.Item;
import Main.Handler;
import Resources.Animation;
import Resources.Images;
import Worlds.World1;

public class Companion extends CreatureBase{

    private Animation animDown, animUp, animLeft, animRight;

    private Boolean attacking=false;
    public static Boolean stat = false;
    
    private int animWalkingSpeed = 150;
    private Inventory Compyinventory;
    private Rectangle CompyCam;

    private int healthcounter = 0;

    private Random randint;
    private int moveCount=0;
    private int direction;
    public boolean visual= false;
    
    
    


	public Companion(Handler handler, float x, float y) {
        super(handler, x, y, CreatureBase.DEFAULT_CREATURE_WIDTH, CreatureBase.DEFAULT_CREATURE_HEIGHT);
        bounds.x=8*2;
        bounds.y=18*2;
        bounds.width=16*2;
        bounds.height=14*2;
        speed=1.5f;
        health=50;

        CompyCam= new Rectangle();
//        attack= 0;


        randint = new Random();
        direction = randint.nextInt(4) + 1;

        animDown = new Animation(animWalkingSpeed,Images.player_front);
        animLeft = new Animation(animWalkingSpeed,Images.player_left);
        animRight = new Animation(animWalkingSpeed,Images.player_right);
        animUp = new Animation(animWalkingSpeed,Images.player_back);
        Compyinventory= new Inventory(handler);
    }

    @Override
    public void tick() {
        animDown.tick();
        animUp.tick();
        animRight.tick();
        animLeft.tick();

        moveCount ++;
        if(moveCount>=60){
            moveCount=0;
            direction = randint.nextInt(4) + 1;
        }
        checkIfMove();

        move();


        if(isBeinghurt()){
            healthcounter++;
            if(healthcounter>=120){
                setBeinghurt(false);
                System.out.print(isBeinghurt());
            }
        }
        if(healthcounter>=120&& !isBeinghurt()){
            healthcounter=0;
        }

        if(getVisual() == false) {
             	this.x= 5000;
             	this.y= 5000;
        	}
        
       
        Compyinventory.tick();


    }


    protected void checkIfMove() {
        xMove = 0;
        yMove = 0;

        CompyCam.x = (int) (x - handler.getGameCamera().getxOffset() - (64 * 3));
        CompyCam.y = (int) (y - handler.getGameCamera().getyOffset() - (64 * 3));
        CompyCam.width = 64 * 7;
        CompyCam.height = 64 * 7;
        int adj = 0;
        if(getVisual() == true) {
        	if(CompyCam.contains(handler.getWorld().getEntityManager().getBossy().getX() - handler.getGameCamera().getxOffset(), handler.getWorld().getEntityManager().getBossy().getY() - handler.getGameCamera().getyOffset())
                    || CompyCam.contains(handler.getWorld().getEntityManager().getBossy().getX() - handler.getGameCamera().getxOffset() + handler.getWorld().getEntityManager().getBossy().getWidth(), handler.getWorld().getEntityManager().getBossy().getY() - handler.getGameCamera().getyOffset() + handler.getWorld().getEntityManager().getBossy().getHeight())) {

                Rectangle cb = getCollisionBounds(0, 0);
                Rectangle ar = new Rectangle();
                int arSize = 13;
                ar.width = arSize;
                ar.height = arSize;

                if (lu) {
                    ar.x = cb.x + cb.width / 2 - arSize / 2;
                    ar.y = cb.y - arSize;
                } else if (ld) {
                    ar.x = cb.x + cb.width / 2 - arSize / 2;
                    ar.y = cb.y + cb.height;
                } else if (ll) {
                    ar.x = cb.x - arSize;
                    ar.y = cb.y + cb.height / 2 - arSize / 2;
                } else if (lr) {
                    ar.x = cb.x + cb.width;
                    ar.y = cb.y + cb.height / 2 - arSize / 2;
                }

                for (EntityBase e : handler.getWorld().getEntityManager().getEntities()) {
                    if (e.equals(this))
                        continue;
                    if(e.getCollisionBounds(0, 0).intersects(ar)){
                    	checkAttacks();
                        return;
                    }
                }


                if (x >= handler.getWorld().getEntityManager().getBossy().getX() - 8 && x <= handler.getWorld().getEntityManager().getBossy().getX() + 8) {//nada
                	xMove = 0;
                } else if (x < handler.getWorld().getEntityManager().getBossy().getX()) {//move right

                    xMove = speed;

                } else if (x > handler.getWorld().getEntityManager().getBossy().getX()) {//move left

                    xMove = -speed;
                }

                if (y >= handler.getWorld().getEntityManager().getBossy().getY() - 8 && y <= handler.getWorld().getEntityManager().getBossy().getY() + 8) {//nada
                    yMove = 0;
                } else if (y < handler.getWorld().getEntityManager().getBossy().getY()) {//move down
                    yMove = speed;

                } else if (y > handler.getWorld().getEntityManager().getBossy().getY()) {//move up
                    yMove = -speed;
                }


            } else if(CompyCam.contains(handler.getWorld().getEntityManager().getSkely().getX() - handler.getGameCamera().getxOffset(), handler.getWorld().getEntityManager().getSkely().getY() - handler.getGameCamera().getyOffset())
                    || CompyCam.contains(handler.getWorld().getEntityManager().getSkely().getX() - handler.getGameCamera().getxOffset() + handler.getWorld().getEntityManager().getSkely().getWidth(), handler.getWorld().getEntityManager().getSkely().getY() - handler.getGameCamera().getyOffset() + handler.getWorld().getEntityManager().getSkely().getHeight())) {

                Rectangle cb = getCollisionBounds(0, 0);
                Rectangle ar = new Rectangle();
                int arSize = 13;
                ar.width = arSize;
                ar.height = arSize;

                if (lu) {
                    ar.x = cb.x + cb.width / 2 - arSize / 2;
                    ar.y = cb.y - arSize;
                } else if (ld) {
                    ar.x = cb.x + cb.width / 2 - arSize / 2;
                    ar.y = cb.y + cb.height;
                } else if (ll) {
                    ar.x = cb.x - arSize;
                    ar.y = cb.y + cb.height / 2 - arSize / 2;
                } else if (lr) {
                    ar.x = cb.x + cb.width;
                    ar.y = cb.y + cb.height / 2 - arSize / 2;
                }

                for (EntityBase e : handler.getWorld().getEntityManager().getEntities()) {
                    if (e.equals(this))
                        continue;
                    if(e.getCollisionBounds(0, 0).intersects(ar)){
                    	checkAttacks();
                        return;
                    }
                }


                if (x >= handler.getWorld().getEntityManager().getSkely().getX() - 8 && x <= handler.getWorld().getEntityManager().getSkely().getX() + 8) {//nada
                	xMove = 0;
                } else if (x < handler.getWorld().getEntityManager().getSkely().getX()) {//move right

                    xMove = speed;

                } else if (x > handler.getWorld().getEntityManager().getSkely().getX()) {//move left

                    xMove = -speed;
                }

                if (y >= handler.getWorld().getEntityManager().getSkely().getY() - 8 && y <= handler.getWorld().getEntityManager().getSkely().getY() + 8) {//nada
                    yMove = 0;
                } else if (y < handler.getWorld().getEntityManager().getSkely().getY()) {//move down
                    yMove = speed;

                } else if (y > handler.getWorld().getEntityManager().getSkely().getY()) {//move up
                    yMove = -speed;
                }


            } else if (CompyCam.contains(handler.getWorld().getEntityManager().getPlayer().getX() - handler.getGameCamera().getxOffset()+ adj, handler.getWorld().getEntityManager().getPlayer().getY() - handler.getGameCamera().getyOffset()+ adj)
                    || CompyCam.contains(handler.getWorld().getEntityManager().getPlayer().getX() - handler.getGameCamera().getxOffset()+adj + handler.getWorld().getEntityManager().getPlayer().getWidth(), handler.getWorld().getEntityManager().getPlayer().getY() - handler.getGameCamera().getyOffset()+adj + handler.getWorld().getEntityManager().getPlayer().getHeight())) {

                Rectangle cb = getCollisionBounds(0, 0);
                Rectangle ar = new Rectangle();
                int arSize = 13;
                ar.width = arSize;
                ar.height = arSize;

                if (lu) {
                    ar.x = cb.x + cb.width / 2 - arSize / 2;
                    ar.y = cb.y - arSize;
                } else if (ld) {
                    ar.x = cb.x + cb.width / 2 - arSize / 2;
                    ar.y = cb.y + cb.height;
                } else if (ll) {
                    ar.x = cb.x - arSize;
                    ar.y = cb.y + cb.height / 2 - arSize / 2;
                } else if (lr) {
                    ar.x = cb.x + cb.width;
                    ar.y = cb.y + cb.height / 2 - arSize / 2;
                }

                for (EntityBase e : handler.getWorld().getEntityManager().getEntities()) {
                    if (e.equals(this))
                        continue;
                    if(e.getCollisionBounds(0, 0).intersects(ar)){
                       // checkAttacks();
                        return;
                    }
                }

                
                if (x >= handler.getWorld().getEntityManager().getPlayer().getX() - 8 && x <= handler.getWorld().getEntityManager().getPlayer().getX() + 8) {//nada
                    xMove = 0;
                } else if (x < handler.getWorld().getEntityManager().getPlayer().getX()) {//move right

                    xMove = speed;

                } else if (x > handler.getWorld().getEntityManager().getPlayer().getX()) {//move left
                    xMove = -speed;
                }

                if (y >= handler.getWorld().getEntityManager().getPlayer().getY() - 8 && y <= handler.getWorld().getEntityManager().getPlayer().getY() + 8) {//nada
                    yMove = 0;
                } else if (y < handler.getWorld().getEntityManager().getPlayer().getY()) {//move down
                    yMove = speed;

                } else if (y > handler.getWorld().getEntityManager().getPlayer().getY()) {//move up
                    yMove = -speed;
                }
            }
            

        }
       
         
        
    }


    @Override
    public void render(Graphics g) {
    	if(visual == true) {
        g.drawImage(getCurrentAnimationFrame(animDown,animUp,animLeft,animRight,Images.player_front,Images.player_back,Images.player_left,Images.player_right), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        if(isBeinghurt() && healthcounter<=120){
            g.setColor(Color.white);
            g.drawString("CompyHealth: " + getHealth(),(int) (x-handler.getGameCamera().getxOffset()),(int) (y-handler.getGameCamera().getyOffset()-20));
        }
    	
    	}
    }




    @Override
    public void die() {
    	 stat=true;
    	 this.x=0;
    	 this.y=0;
    }


    public boolean getVisual() {
		return visual;
	}

	public void setVisual(boolean visual) {
		this.visual = visual;
	}
}
