package Game.Entities.Creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import Game.Entities.EntityBase;
import Game.GameStates.State;
import Game.Inventories.Inventory;
import Main.Handler;
import Resources.Animation;
import Resources.Images;
import Worlds.CaveWorld;

public class FinalBoss extends CreatureBase {
    private Animation animDown, animUp, animLeft, animRight;
    private Boolean attacking=false;
    private int animWalkingSpeed = 150;
    private Inventory finalBossinventory;
    private Rectangle finalBossCam;
    private int healthcounter = 0;
    private Random randint;
    private int moveCount=0;
    private int direction;
    private boolean visible= false;
	
	public FinalBoss(Handler handler, float x, float y) {
		super(handler, x, y, 100, 100);
		bounds.x=8;
        bounds.y=18;
        bounds.width=16*5;
        bounds.height=14*7;
        speed=1.5f*2;
        health=120;
        finalBossCam= new Rectangle();
        x=this.x;
        y=this.y;
        randint = new Random();
        direction = randint.nextInt(4) + 1;
        animDown = new Animation(animWalkingSpeed, Images.SkelyEnemy_front);
        animLeft = new Animation(animWalkingSpeed,Images.SkelyEnemy_left);
        animRight = new Animation(animWalkingSpeed,Images.SkelyEnemy_right);
        animUp = new Animation(animWalkingSpeed,Images.SkelyEnemy_back);
        finalBossinventory= new Inventory(handler);
		
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
        if(handler.getWorld().equals(CaveWorld.newWorld) && !isVisible()){
        	spawnFinal();
        	setVisible(true);
        }
        if(!handler.getWorld().equals(CaveWorld.newWorld)){
        	setVisible(false);
        	this.x=	4000;
        	this.y=	4000;
        }

        finalBossinventory.tick();

		
	}
		@Override
		@SuppressWarnings("Duplicates")
	    public void checkAttacks(){
	        attackTimer += System.currentTimeMillis() - lastAttackTimer;
	        lastAttackTimer = System.currentTimeMillis();
	        if(attackTimer < attackCooldown)
	            return;

	        Rectangle cb = getCollisionBounds(0, 0);
	        Rectangle ar = new Rectangle();
	        int arSize = 40;
	        ar.width = arSize;
	        ar.height = arSize*3;

	        if(lu){
	            ar.x = cb.x + cb.width / 2 - arSize / 2;
	            ar.y = cb.y - arSize;
	        }else if(ld){
	            ar.x = cb.x + cb.width / 2 - arSize / 2;
	            ar.y = cb.y + cb.height;
	        }else if(ll){
	            ar.x = cb.x - arSize;
	            ar.y = cb.y + cb.height / 2 - arSize / 2;
	        }else if(lr){
	            ar.x = cb.x + cb.width;
	            ar.y = cb.y + cb.height / 2 - arSize / 2;
	        }else{
	            return;
	        }

	        attackTimer = 0;

	        for(EntityBase e : handler.getWorld().getEntityManager().getEntities()){
	            if(e.equals(this))
	                continue;
	            if(e.getCollisionBounds(0, 0).intersects(ar)){
	                e.hurt(attack);
	                System.out.println(e + " has " + e.getHealth() + " lives.");
	                return;
	            }
	        }

	    }
	
		private void spawnFinal(){
			this.x = 900;
        	this.y = 800;
		}
	 
		private void checkIfMove() {
	        xMove = 0;
	        yMove = 0;

	        finalBossCam.x = (int) (x - handler.getGameCamera().getxOffset() - (64 * 3));
	        finalBossCam.y = (int) (y - handler.getGameCamera().getyOffset() - (64 * 3));
	        finalBossCam.width = 64 * 7;
	        finalBossCam.height = 64 * 7;
	       if(isVisible()){
	        if (finalBossCam.contains(handler.getWorld().getEntityManager().getCompy().getX() - handler.getGameCamera().getxOffset(), handler.getWorld().getEntityManager().getCompy().getY() - handler.getGameCamera().getyOffset())
	                || finalBossCam.contains(handler.getWorld().getEntityManager().getCompy().getX() - handler.getGameCamera().getxOffset() + handler.getWorld().getEntityManager().getCompy().getWidth(), handler.getWorld().getEntityManager().getCompy().getY() - handler.getGameCamera().getyOffset() + handler.getWorld().getEntityManager().getCompy().getHeight())) {

	            Rectangle cb = getCollisionBounds(0, 0);
	            Rectangle ar = new Rectangle();
	            int arSize = 50;
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
	                if (e.getCollisionBounds(0, 0).intersects(ar) && e.equals(handler.getWorld().getEntityManager().getCompy())) {

	                    checkAttacks();
	                    return;
	                }
	            }


	            if (x >= handler.getWorld().getEntityManager().getCompy().getX() - 8 && x <= handler.getWorld().getEntityManager().getCompy().getX() + 8) {//nada

	                xMove = 0;
	            } else if (x < handler.getWorld().getEntityManager().getCompy().getX()) {//move right

	                xMove = speed;

	            } else if (x > handler.getWorld().getEntityManager().getCompy().getX()) {//move left

	                xMove = -speed;
	            }

	            if (y >= handler.getWorld().getEntityManager().getCompy().getY() - 8 && y <= handler.getWorld().getEntityManager().getCompy().getY() + 8) {//nada
	                yMove = 0;
	            } else if (y < handler.getWorld().getEntityManager().getCompy().getY()) {//move down
	                yMove = speed;

	            } else if (y > handler.getWorld().getEntityManager().getCompy().getY()) {//move up
	                yMove = -speed;
	            }


	        }	else if (finalBossCam.contains(handler.getWorld().getEntityManager().getPlayer().getX() - handler.getGameCamera().getxOffset(), handler.getWorld().getEntityManager().getPlayer().getY() - handler.getGameCamera().getyOffset())
	                || finalBossCam.contains(handler.getWorld().getEntityManager().getPlayer().getX() - handler.getGameCamera().getxOffset() + handler.getWorld().getEntityManager().getPlayer().getWidth(), handler.getWorld().getEntityManager().getPlayer().getY() - handler.getGameCamera().getyOffset() + handler.getWorld().getEntityManager().getPlayer().getHeight())) {

	            Rectangle cb = getCollisionBounds(0, 0);
	            Rectangle ar = new Rectangle();
	            int arSize = 50;
	            ar.width = arSize;
	            ar.height = arSize;

	            if (lu) {
	                ar.x = cb.x + cb.width / 2 ;
	                ar.y = cb.y - arSize;
	            } else if (ld) {
	                ar.x = cb.x + cb.width / 2 ;
	                ar.y = cb.y + cb.height;
	            } else if (ll) {
	                ar.x = cb.x - arSize;
	                ar.y = cb.y + cb.height / 2 ;
	            } else if (lr) {
	                ar.x = cb.x + cb.width;
	                ar.y = cb.y + cb.height / 2 ;
	            }

	            for (EntityBase e : handler.getWorld().getEntityManager().getEntities()) {
	                if (e.equals(this))
	                    continue;
	                if (e.getCollisionBounds(0, 0).intersects(ar) && e.equals(handler.getWorld().getEntityManager().getPlayer())) {

	                    checkAttacks();
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


	        } else {


	            switch (direction) {
	                case 1://up
	                    yMove = -speed;
	                    break;
	                case 2://down
	                    yMove = speed;
	                    break;
	                case 3://left
	                    xMove = -speed;
	                    break;
	                case 4://right
	                    xMove = speed;
	                    break;

	            	}
	        	}
	       }
	    }
	@Override
	public void render(Graphics g) {
		if(isVisible()){
		g.drawImage(getCurrentAnimationFrame(animDown,animUp,animLeft,animRight,Images.SkelyEnemy_front,Images.SkelyEnemy_back,Images.SkelyEnemy_left,Images.SkelyEnemy_right), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        	if(isBeinghurt() && healthcounter<=120){
        		g.setColor(Color.white);
        		g.drawString("SkelyHealth: " + getHealth(),(int) (x-handler.getGameCamera().getxOffset()),(int) (y-handler.getGameCamera().getyOffset()-20));
        	}
		}
	}
	@Override
	public void die() {
		State.setState(handler.getGame().gameBeatenState);
		
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
