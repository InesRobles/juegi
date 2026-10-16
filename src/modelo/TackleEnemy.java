package modelo;

import java.util.ArrayList;

import utiles.Direction;

public class TackleEnemy extends Enemy {

	public TackleEnemy(int x, int y, int room, int health) {
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
		damage=2;
		tackling=false;
		movingTimer = 0;
		moving=true;
	}


	public void tacklingEnemy() {
		switch(direction) {
		case UP:
			x-=(speed+3);
			break;
		case DOWN:
			x+=(speed+3);
			break;
		case RIGHT:
			y+=(speed+3);
			break;
		case LEFT:
			y-=(speed+3);
			break;
		}
		moving=true;
	}
	@Override
	public void update(Player player, ArrayList<Item> items, Rooms room, ArrayList<Projectile> projectiles) {
		if(this.room==room.getSalaActual()) {
			if(!dying && alive) {
				lastx=x;
				lasty=y;
				directionCollision=null;
				if(tackling) {
					tacklingEnemy();
				}else {	
					movingTimer++;
					if (movingTimer >= 60) { 
						movingTimer = 0;
						changeDirection(); 
					}

					move();
					Detection(player);
				}
				checkCollisionsEnemy(room);
				Attacked(player);	
				if(!tackling) {
					walktimer++;
					if(walktimer%10==0) {
						walkframes= !walkframes;
						walktimer=0;
					}
				}else if(tackling) {
					walktimer++;
					if(walktimer%5==0) {
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
					tackling=false;
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
		int columnaEnemy=(y + GAP/2)/ GAP; 
		int filaEnemy=(x+ GAP/2) /GAP;
		int columnaPlayer=(player.getY() + GAP/2)/ GAP;
		int filaPlayer=(player.getX()+ GAP/2) /GAP;
		int dx=player.getX()-x;
		int dy=player.getY()-y;
		int distance=(int)Math.sqrt(dx*dx+dy*dy);
		int detection=DETECTION_RANGE;
		if(distance<detection) {
			if(columnaEnemy==columnaPlayer) {
				if(dx>0) {
					tackling=true;
					direction=Direction.DOWN;
				}else{
					tackling=true;
					direction=Direction.UP;
				}
			}else if(filaEnemy==filaPlayer) {
				if(dy>0) {
					tackling=true;
					direction=Direction.RIGHT;
				}else{
					tackling=true;
					direction=Direction.LEFT;
				}
			}
		}
	}

	public void changeDirection() {
		int num=((int)(Math.random()*4)+1);
		switch(num) {
		case 1:
			direction=Direction.UP;
			break;
		case 2:
			direction=Direction.DOWN;
			break;
		case 3:
			direction=Direction.RIGHT;
			break;
		case 4:
			direction=Direction.LEFT;
			break;
		}
	}
	public void move() {
		switch(direction) {
		case UP:
			x-=speed;
			break;
		case DOWN:
			x+=speed;
			break;
		case RIGHT:
			y+=speed;
			break;
		case LEFT:
			y-=speed;
			break;
		}
		moving=true;
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
