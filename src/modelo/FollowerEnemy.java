package modelo;
import java.util.ArrayList;

import utiles.Direction;

public class FollowerEnemy extends Enemy{

	public FollowerEnemy(int x, int y, int room, int health) {
		super();
		this.health=health;
		originalHealth=health;
		speed=2;
		this.x=x;
		this.y=y;
		this.room=room;
		originalx=x;
		originaly=y;
		alive=true;
		direction=Direction.UP;
		canMove=true;
		damage=1;
	}


	@Override
	public void update(Player player, ArrayList<Item> items, Rooms room, ArrayList<Projectile> projectiles) {
		if(this.room==room.getSalaActual()) {
		if(!dying && alive) {
			lastx=x;
			lasty=y;
			directionCollision=null;
			Detection(player);
			checkCollisionsEnemy(room);
			Attacked(player);	
			if(canMove) {
				walktimer++;
				if(walktimer%10==0) {
					walkframes= !walkframes;
					walktimer=0;
				}
			}
			if(beingAttacked) {
				attackedTimer--;
				if(attackedTimer%5==0) {
					attackedFrames=!attackedFrames;
				}
				if(attackedTimer==0) {
					beingAttacked=false;
					canMove=true;
				}
				lastx=x;
				lasty=y;
				switch(directionAttacked) {
				case LEFT:
					y-=3;
					break;
				case RIGHT:
					y+=3;
					break;
				case DOWN:
					x+=3;
					break;
				case UP:
					x-=3;
					break;
				}
				checkCollisionsEnemy(room);
			}
			if(health<=0) {
				dying=true;
			}
		}else if(dying=true && alive) {
			if(dyingTimer==0) {
				sound.play("/Resources/SFX/Soundeffects/EnemyDie.wav");
				alive=false;
				Item i=generateItem();
				if(i!=null) {
					items.add(i);
				}
			}
			dyingTimer--;
			if(dyingTimer%5==0) {
				dyingFrames=(dyingFrames+1)%4;
			}
		}
		}else {
			reset();
		}
	}

	public void Detection(Player player) {

		int dx=player.getX()-x;
		int dy=player.getY()-y;
		//Con el teorema de pitagoras calculamos la distancia al calcular la hipotenusa
		int distance=(int)Math.sqrt(dx*dx+dy*dy);
		int detection=DETECTION_RANGE;
		if(distance<detection && canMove) {
			moving=true;
			if(dx>0 && directionCollision!=Direction.DOWN) {
				direction=Direction.DOWN;
				x+=speed;
			}else if(dx<0 && directionCollision!=Direction.UP) {
				direction=Direction.UP;
				x-=speed;
			}
			if(dy>0 && directionCollision!=Direction.RIGHT) {
				direction=Direction.RIGHT;
				y+=speed;
			}else if(dy<0 && directionCollision!=Direction.LEFT) {
				direction=Direction.LEFT;
				y-=speed;
			}
		}else {
			moving=false;
		}
	}

	
	public void reset() {
		x=originalx;
		y=originaly;
		alive=true;
		health=originalHealth;
		beingAttacked=false;
		canMove=true;
		dyingTimer=20;
		dying=false;
	}

	public Direction getDirectionCollision() {
		return directionCollision;
	}

	public void setDirectionCollision(Direction directionCollision) {
		this.directionCollision=directionCollision;
	}


	@Override
	public int getEyeFrame() {
		// TODO Auto-generated method stub
		return 0;
	}




}
