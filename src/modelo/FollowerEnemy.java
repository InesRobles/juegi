package modelo;

import java.util.ArrayList;

import utiles.Direction;

public class FollowerEnemy extends Enemy {

	public FollowerEnemy(int x, int y, int room, int health) {
		super();
		this.health = health;
		originalHealth = health;
		speed = 3;
		this.x = x;
		this.y = y;
		this.room = room;
		originalx = x;
		originaly = y;
		alive = true;
		direction = Direction.UP;
		canMove = true;
		damage = 1;
	}

	@Override
	public void update(Player player, ArrayList<Item> items, Rooms room, ArrayList<Projectile> projectiles) {
		if (this.room == room.getSalaActual()) {
			if (!dying && alive) {
				lastx = x;
				lasty = y;
				directionCollision = null;
				Detection(player);
				checkCollisionsEnemy(room);
				Attacked(player);
				if (canMove) {
					walktimer++;
					if (walktimer % 10 == 0) {
						walkframes = !walkframes;
						walktimer = 0;
					}
				}
				if (beingAttacked) {
					attackedTimer--;
					if (attackedTimer % 5 == 0) {
						attackedFrames = !attackedFrames;
					}
					if (attackedTimer == 0) {
						beingAttacked = false;
						canMove = true;
					}
					lastx = x;
					lasty = y;
					switch (directionAttacked) {
					case LEFT:
						y -= 3;
						break;
					case RIGHT:
						y += 3;
						break;
					case DOWN:
						x += 3;
						break;
					case UP:
						x -= 3;
						break;
					}
					checkCollisionsEnemy(room);
				}
				if (health <= 0) {
					dying = true;
				}
			} else if (dying = true && alive) {
				if (dyingTimer == 0) {
					sound.play("/Resources/SFX/Soundeffects/EnemyDie.wav");
					alive = false;
					Item i = generateItem();
					if (i != null) {
						items.add(i);
					}
				}
				dyingTimer--;
				if (dyingTimer % 5 == 0) {
					dyingFrames = (dyingFrames + 1) % 4;
				}
			}
		} else {
			reset();
		}
	}

	public void Detection(Player player) {
		double diffX = player.getX() - x;
		double diffY = player.getY() - y;
		double angle = Math.atan2(player.getY(), player.getX());
		double dx = Math.cos(angle) * speed;
		double dy = Math.sin(angle) * speed;

		int distance = (int) Math.sqrt(diffX * diffX + diffY * diffY);
		if (distance < DETECTION_RANGE && canMove) {
			moving = true;
			double movX = (diffX / distance) * speed;
			double movY = (diffY / distance) * speed;

			if ((diffX > 0 && directionCollision != Direction.DOWN)	|| (diffX < 0 && directionCollision != Direction.UP)) {
				x += movX;
			}

			if ((diffY > 0 && directionCollision != Direction.RIGHT) || (diffY < 0 && directionCollision != Direction.LEFT)) {
				y += movY;
			}

			if (Math.abs(diffX) > Math.abs(diffY)) {
				direction = (diffX > 0) ? Direction.DOWN : Direction.UP;
			} else {
				direction = (diffY > 0) ? Direction.RIGHT : Direction.LEFT;
			}
		} else {
			moving = false;
		}
	}

	public void reset() {
		x = originalx;
		y = originaly;
		alive = true;
		health = originalHealth;
		beingAttacked = false;
		canMove = true;
		dyingTimer = 20;
		dying = false;
	}

	public Direction getDirectionCollision() {
		return directionCollision;
	}

	public void setDirectionCollision(Direction directionCollision) {
		this.directionCollision = directionCollision;
	}

	@Override
	public int getEyeFrame() {
		// TODO Auto-generated method stub
		return 0;
	}

}
