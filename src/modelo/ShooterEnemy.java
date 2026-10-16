package modelo;

import java.util.ArrayList;

import utiles.Direction;

public class ShooterEnemy extends Enemy{
	private StraightProjectile projectile;
	private int AttackTimer;
	public ShooterEnemy(int x, int y, int room, int health) {
		super();
		this.x=x;
		this.y=y;
		this.room=room;
		speed=2;
		this.health=health;
		originalHealth=health;
		alive =true;
		projectile=new StraightProjectile(x, y, direction, "enemy", room);
		projectile.setActive(false);
		changeDirection();
		canMove=true;
		originalx=x;
		originaly=y;
		damage=1;
		moving=true;
		AttackTimer=90;
	}


	public StraightProjectile getProjectile() {
		return projectile;
	}


	public void setProjectile(StraightProjectile projectile) {
		this.projectile = projectile;
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

	public void Detection(Player player, ArrayList<Projectile> projectiles) {

		int columnaEnemy=(y + GAP/2)/ GAP; 
		int filaEnemy=(x+ GAP/2) /GAP;
		int columnaPlayer=(player.getY() + GAP/2)/ GAP;
		int filaPlayer=(player.getX()+ GAP/2) /GAP;
		int dx=player.getX()-x;
		int dy=player.getY()-y;
		if(columnaEnemy==columnaPlayer) {
			if(dx>0) {
				AttackTimer=90;
				direction=Direction.DOWN;
				projectiles.add(new StraightProjectile(x, y, Direction.DOWN, "enemy", room));
			}else{
				AttackTimer=90;
				direction=Direction.UP;
				projectiles.add(new StraightProjectile(x, y, Direction.UP, "enemy", room));
				}
		}else if(filaEnemy==filaPlayer) {
			if(dy>0) {
				AttackTimer=90;
				direction=Direction.RIGHT;
				projectiles.add(new StraightProjectile(x, y, Direction.RIGHT, "enemy", room));
			}else{
				AttackTimer=90;
				direction=Direction.LEFT;
				projectiles.add(new StraightProjectile(x, y, Direction.LEFT, "enemy", room));
			}
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

	protected void update(Player player, ArrayList<Item> items, Rooms room, ArrayList<Projectile> projectiles) {
		if(this.room==room.getSalaActual()) {
		if(!dying && alive) {
			lastx=x;
			lasty=y;
			checkCollisionsEnemy(room);
				AttackTimer--;
				if(AttackTimer<=0) {
					Detection(player, projectiles);
				}


			if (moving) {
				walktimer++;
				if (walktimer % 10 == 0) {
					walkframes = !walkframes;
					walktimer = 0;
				}
				movingTimer++;
				if (movingTimer >= 60) { 
					movingTimer = 0;
					changeDirection(); 
				}

				move();
				checkCollisionsEnemy(room);
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
			Attacked(player);
			if(health<=0) {
				dying=true;
				projectile.setActive(false);
			}
		}else if(dying && alive) {
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


	@Override
	public int getEyeFrame() {
		// TODO Auto-generated method stub
		return 0;
	}

}