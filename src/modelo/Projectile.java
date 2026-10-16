package modelo;

import java.util.ArrayList;

import utiles.Direction;

public abstract class Projectile {
	protected int x;
	protected int y;
	protected int damage;
	protected Direction direction;
	protected int speed;
	protected boolean active;
	protected int lastx;
	protected int lasty;
	protected int frames;
	protected int frameTimer;
	protected boolean beingDeflected;
	protected int deflectedTimer;
	protected double dx;
	protected double dy;
	protected int playerX;
	protected int playerY;
	protected String owner;
	protected int room;
	protected abstract void update(Rooms rooms, Player player, ArrayList<Enemy> enemies);
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	
	public String getOwner() {
		return owner;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	public int getFrames() {
		return frames;
	}
	public void setFrames(int frames) {
		this.frames = frames;
	}
}
