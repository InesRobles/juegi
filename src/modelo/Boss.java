package modelo;

import java.util.ArrayList;

import utiles.Direction;

public class Boss extends Enemy {

	private boolean eyeOpen;
	private int eyeTimer;
	private int eyeFrame;
	private int movement;
	private int contProjectiles;
	private int attackTimer;

	public Boss(int x, int y, int room, int health) {
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
		direction=Direction.RIGHT;
		canMove=true;
		damage=1;
		movement=0;
		eyeTimer=0;
		eyeFrame=0;
		eyeOpen=true;
		movement=70;
		contProjectiles=0;
		attackTimer=0;
		walkframes=false;
	}

	public int getEyeFrame() {
		return eyeFrame;
	}

	public void setEyeFrame(int eyeFrame) {
		this.eyeFrame = eyeFrame;
	}
	@Override
	public void Attacked(Player player) {
		if(attackable && player.getDirection()!=Direction.DOWN && player.isAttacking()) {
			int columnaEnemy=(y + GAP/2)/ GAP; 
			int filaEnemy=(x+ GAP/2) /GAP;
			int columnaAttack=(player.getY() + GAP/2)/ GAP;
			int filaAttack=(player.getX()+ GAP/2) /GAP;
			int columnaPlayer=columnaAttack;
			int filaPlayer=filaAttack;
			switch(player.getDirection()) {
			case UP:
				filaAttack--;
				break;
			case DOWN:
				filaAttack++;
				break;
			case RIGHT:
				columnaAttack++;
				break;
			case LEFT:
				columnaAttack--;
				break;
			}

			if(((columnaAttack==columnaEnemy && filaAttack==filaEnemy++) ||(columnaAttack==columnaEnemy && filaAttack==filaEnemy) || (filaPlayer==filaEnemy && columnaPlayer==columnaEnemy)) && !beingAttacked) {
				sound.play("/Resources/SFX/Soundeffects/EnemyHit.wav");
				int damage=player.getDamage();
				health-=damage;
				beingAttacked=true;
				attackedTimer=30;
			}
		}
	}
	public void AttackedProjectile(Player player, StraightProjectile projectile) {
		int columnaEnemy=(y + GAP/2)/ GAP; 
		int filaEnemy=(x+ GAP/2) /GAP;
		int columnaProjectile=(projectile.getY() + GAP/2)/ GAP; 
		int filaProjectile=(projectile.getX()+ GAP/2) /GAP;
		if((columnaProjectile==columnaEnemy && filaProjectile==filaEnemy) ||(columnaProjectile==columnaEnemy+1 && filaProjectile==filaEnemy)  && !beingAttacked) {
			if(eyeOpen) {
				sound.play("/Resources/SFX/Soundeffects/EnemyHit.wav");
				int damage=projectile.getDamage();
				health-=damage;
				beingAttacked=true;
				attackedTimer=30;
				directionAttacked=player.getDirection();
				canMove=false;
			}
			projectile.setActive(false);
		}
	}
	public void move() {	
		movement++;
		if(movement%140==0){
			changeDirection();
		}
		switch(direction) {
		case RIGHT:
			y+=speed;
			break;
		case LEFT:
			y-=speed;
			break;
		default:
			break;
		}
	}
	public void changeDirection() {
		switch(direction) {
		case RIGHT:
			direction=Direction.LEFT;
			break;
		case LEFT:
			direction=Direction.RIGHT;
			break;
		default:
			break;
		}
	}
	@Override
	protected void update(Player player, ArrayList<Item> items, Rooms room, ArrayList<Projectile> projectiles) {
		if(this.room==room.getSalaActual()) {
			if(!dying && alive) {
				move();
				checkCollisionsEnemy(room);
				if(eyeOpen) {
					eyeTimer++;
					if(eyeTimer>130) {

					}
					if(eyeTimer%130==0) {
						eyeOpen=false;
						eyeTimer=0;
						eyeFrame=0;
						attackable=false;
					}
				}else {
					eyeTimer++;
					if(eyeTimer>130) {
						if(eyeTimer%8==0) {
							eyeFrame=(eyeFrame+1)%4;
						}
						if(eyeFrame==3) {
							eyeOpen=true;
							eyeTimer=0;
							attackable=true;
						}
					}
				}
				if(beingAttacked) {
					attackedTimer--;
					if(attackedTimer%5==0) {
						attackedFrames=!attackedFrames;
					}
					if(attackedTimer==0) {
						beingAttacked=false;
					}
				}
				attackTimer++;
				if(attackTimer%50==0) {
					attack(player, projectiles);
				}
				walktimer++;
				if(walktimer%10==0) {
					walkframes=!walkframes;
				}
				if(health<=0) {
					dying=true;
				}
				Attacked(player);
			}else if(dying=true && alive) {
				if(dyingTimer==0) {
					sound.play("/Resources/SFX/Soundeffects/EnemyDie.wav");
					alive=false;

				}
				dyingTimer--;
				if(dyingTimer%5==0) {
					dyingFrames=(dyingFrames+1)%4;
				}
			}
		}
	}

	public void attack(Player player, ArrayList<Projectile> projectiles) {
		int cont=0;
		int side=(int) ((Math.random()*2) + 1);
		if(contProjectiles<=6) {
			if(side==1) {
				projectiles.add(new TargetProjectile(x, y+GAP, player.getX(), player.getY(), room));
			}else {
				projectiles.add(new TargetProjectile(x, y-GAP, player.getX(), player.getY(), room));
			}
		}
		for(Projectile t : projectiles) {
			if(t.isActive()) {
				cont++;	
			}
		}
		contProjectiles=cont;
	}
	@Override
	protected void reset() {


	}

}
