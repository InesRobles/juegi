package modelo;

import utiles.Direction;

public abstract class Entity {
/*enum Direction{
	UP, DOWN, LEFT, RIGHT
}*/
	protected int x;
	protected int y;
	protected int lastx;
	protected int lasty;
	protected int health;
	protected int damage;
	protected double speed;
	protected Direction direction;
	protected Direction directionAttacked;
	protected boolean moving;
	protected boolean attacking;
	protected boolean cooldown;
	protected boolean walkframes;
	protected int cooldowntimer;
	protected int attackframes;
	protected int attacktimer;
	protected int walktimer;
	protected boolean alive;
	protected int attackedTimer;
	protected boolean beingAttacked;
	protected boolean canMove;
	protected boolean attackedFrames;
	protected boolean dying;
	protected int dyingTimer;
	protected int dyingFrames;
	public boolean isBeingAttacked() {
		return beingAttacked;
	}
	public void setBeingAttacked(boolean beingAttacked) {
		this.beingAttacked = beingAttacked;
	}
	public boolean isCanMove() {
		return canMove;
	}
	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public boolean isMoving() {
		return moving;
	}
	public void setMoving(boolean moving) {
		this.moving=moving;
	}
	public boolean isAttacking() {
		return attacking;
	}
	public void setAttacking(boolean Attacking) {
		this.attacking=Attacking;
	}
	public boolean getWalkFrames() {
		return walkframes;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive=alive;
	}
	public int getAttackFrames() {
		return attackframes; 
	}
	public boolean getAttackedFrames() {
		return attackedFrames;
	}
	public Direction getDirectionAttacked() {
		return directionAttacked;
	}
	public void setDirectionAttacked(Direction directionAttacked) {
		this.directionAttacked = directionAttacked;
	}
	public int getAttackedTimer() {
		return attackedTimer;
	}
	public void setAttackedTimer(int attackedTimer) {
		this.attackedTimer = attackedTimer;
	}
	public boolean isDying() {
		return dying;
	}

	public void setDying(boolean dying) {
		this.dying = dying;
	}	

	public int getDyingFrames() {
		return dyingFrames;
	}
	public void setDyingFrames(int dyingFrames) {
		this.dyingFrames = dyingFrames;
	}
	
}
