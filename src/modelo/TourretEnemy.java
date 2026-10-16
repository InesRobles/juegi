package modelo;

import java.util.ArrayList;

import utiles.Direction;

public class TourretEnemy extends Enemy{
	private boolean atSight;
	private int hideTimer;
	private boolean apearing;
	private boolean apearingFrames;
	public TourretEnemy(int x, int y, int room, int health) {
		super();
		this.x=x;
		this.y=y;
		this.room=room;
		speed=2;
		this.health=health;
		originalHealth=health;
		alive =true;
		changeDirection();
		canMove=true;
		originalx=x;
		originaly=y;
		damage=1;
		moving=true;
		atSight=false;	
		hideTimer=100;
		attackable=false;
		apearing=false;
		direction=Direction.DOWN;
	}


	public boolean isAtSight() {
		return atSight;
	}


	public boolean isApearing() {
		return apearing;
	}


	public boolean isApearingFrames() {
		return apearingFrames;
	}


	public void move(Rooms rooms) {
		int[][][] room = rooms.getSalas();
	    int sala = rooms.getSalaActual();
	    
	    //Creo un ArrayList de casillas para guardar las casillas donde puede aparecer el enemigo
	    ArrayList<int[]> validTiles = new ArrayList<>();
	    for (int fila = 0; fila < room[sala].length; fila++) {
	        for (int columna = 0; columna < room[sala][fila].length; columna++) {
	    		if(room[sala][fila][columna]==8 || room[sala][fila][columna]==9 || room[sala][fila][columna]==10 || room[sala][fila][columna]==11 || room[sala][fila][columna]==12 || room[sala][fila][columna]==13 || room[sala][fila][columna]==14 || room[sala][fila][columna]==15 || room[sala][fila][columna]==16) {
	              //Esta guardando matriz en el que el primer valor es la fila y el segundo es la columna
	    			validTiles.add(new int[]{fila, columna});
	            }
	        }
	    }
	    
	    //Eligo una casilla al azar y el enemigo aparece ahi
	    if (!validTiles.isEmpty()) {
	        int[] tile = validTiles.get((int)(Math.random() * validTiles.size()));
	        x = tile[0] * GAP;
	        y = tile[1] * GAP;
	    }
	}
	private void getDirection(Player player) {
		int playerX=player.getX();
		
		if(playerX>x) {
			direction=Direction.DOWN;
		}else {
			direction=Direction.UP;
		}
	}
	@Override
	protected void update(Player player, ArrayList<Item> items, Rooms room, ArrayList<Projectile> projectiles) {
		if(this.room==room.getSalaActual()) {
			if(!dying && alive) {
				if(atSight) {
					hideTimer--;
				getDirection(player);
				if((hideTimer<=160 && hideTimer>=130) || (hideTimer<=30 && hideTimer>=0)) {
					apearing=true;
					if(hideTimer%8==0) {
						apearingFrames=!apearingFrames;
					}
				}else {
					apearing=false;
				}
				if(hideTimer==80) {
					projectiles.add(new TargetProjectile(x, y, player.getX(), player.getY(), this.room));
				}
					if(hideTimer==0) {
						attackable=false;
						hideTimer=100;
						atSight=false;
					}
				}else {
					hideTimer--;
					if(hideTimer==0) {
						move(room);
						attackable=true;
						hideTimer=160;
						atSight=true;
					}
				}
				Attacked(player);
				if(beingAttacked) {
					attackedTimer--;
					if(attackedTimer%5==0) {
						attackedFrames=!attackedFrames;
					}
					if(attackedTimer==0) {
						beingAttacked=false;
					}
				}
				if(health<=0) {
					dying=true;
				}
			}else if (dying && alive) {
				if(dyingTimer==0) {
					sound.play("/Resources/SFX/Soundeffects/EnemyDie.wav");
					alive=false;
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
	protected void reset() {
		x=originalx;
		y=originaly;
		alive=true;
		health=originalHealth;
		beingAttacked=false;
		dyingTimer=20;
		dying=false;
	}

	@Override
	public int getEyeFrame() {
		// TODO Auto-generated method stub
		return 0;
	}
}