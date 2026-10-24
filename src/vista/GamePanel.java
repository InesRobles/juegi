package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import modelo.Boss;
import modelo.Bow;
import modelo.Enemy;
import modelo.FollowerEnemy;
import modelo.GameModel;
import modelo.Heart;
import modelo.HeartContainer;
import modelo.Item;
import modelo.Key;
import modelo.Player;
import modelo.Projectile;
import modelo.Rooms;
import modelo.Rupee;
import modelo.ShooterEnemy;
import modelo.StraightProjectile;
import modelo.TackleEnemy;
import modelo.TargetProjectile;
import modelo.TourretEnemy;
import utiles.BackgroundMusic;
import utiles.SoundEffects;
public class GamePanel extends JPanel {
	private final int TILES_RES=16;
	private final int HEIGHT=11;
	private final int WIDTH=16;
	private final int SCALE=4;


	private final int GAP=TILES_RES*SCALE;
	private final int SCREEN_HEIGHT=TILES_RES*HEIGHT*SCALE;
	private final int SCREEN_WIDTH=TILES_RES*WIDTH*SCALE;


	//Imagenes mapa Overworld
	private Image imgGround, imgGround2, imgRock1, imgRock4, imgRock2, imgRock3, imgRock5, imgRock6, imgRock7; 
	private Image imgLake1, imgLake2, imgLake3, imgLake4, imgLake5, imgLake6, imgLake7, imgLake8, imgLake9, imgIsland1, imgIsland2, imgIsland3, imgIsland4, imgBridge, imgBridgeVertical;
	private Image imgGreenTree, imgBrownTree, imgGrave, imgGuard;
	private Image imgTreeDoor1,imgTreeDoor2,imgTreeDoor3,imgTreeDoor4,imgTreeDoor5;
	private Image imgEntrance, imgStairs;

	//Imagenes mapa cuevas
	private Image imgCaveGround, imgCaveRock1, imgCaveRock2;
	private Image imgFire1, imgFire2;

	//Imagenes mapa Dungeon
	private Image imgDungeonGround, imgDungeonWallHorizontal, imgDungeonWallVertical, imgDungeonCorner1, imgDungeonCorner2, imgDungeonCorner3, imgDungeonCorner4, imgDungeonWallUpPartHorizontal, imgDungeonWallUpPartVertical;
	private Image imgDungeonDoorDecoration1, imgDungeonDoorDecoration2, imgDungeonLockDoor1, imgDungeonLockDoor2, imgDungeonDoorUpPart, imgDungeonOpenDoor1, imgDungeonOpenDoor2;
	private Image imgDungeonStatue1, imgDungeonStatue2;

	//Imagenes jugador
	private Image imgPlayerUp1, imgPlayerUp2, imgPlayerDown1, imgPlayerDown2, imgPlayerHorizontal1, imgPlayerHorizontal2;
	private Image imgSwordUp1, imgSwordUp2, imgSwordUp3, imgSwordHorizontal1, imgSwordHorizontal2, imgSwordHorizontal3, imgSwordDown1, imgSwordDown2, imgSwordDown3;
	private Image imgPlayerActionDown, imgPlayerActionUp, imgPlayerActionHorizontal;
	private Image imgArrowHorizontal, imgArrowVertical;

	//Imagenes enemigos
	private Image imgDuckH1, imgDuckH2, imgDuckV1, imgDuckV2, imgRockProjectile;
	private Image imgGatoKnight;
	private Image imgEnemyDeath1,imgEnemyDeath2,imgEnemyDeath3,imgEnemyDeath4;
	private Image imgGohmaLeg1, imgGohmaLeg2, imgGohmaEye1, imgGohmaEye2, imgGohmaEye3, imgGohmaEye4;
	private Image imgFrogUp, imgFrogDown, imgZoraAppearing1, imgZoraAppearing2;
	private Image imgDiagonalProjectile1,imgDiagonalProjectile2,imgDiagonalProjectile3,imgDiagonalProjectile4;
	private Image imgGoatDown, imgGoatUp, imgGoatHorizontal1, imgGoatHorizontal2;
	//Imagenes HUD
	private Image imgHeartHUD, imgEmptyHeartHUD, imgHalfHeartHUD, imgKeyHUD, imgLemonHUD;

	//Imagenes Items
	private Image imgHeart, imgHalfHeart, imgRupee, imgBlueRupee, imgKey, imgHeartContainer, imgBow;

	private Player player;
	private GameModel modelo;

	//Parte de matriz de salas
	private Rooms rooms;
	private int[][][]map;
	private int SalaActual;

	//Otros
	private BackgroundMusic music;
	private SoundEffects sound;
	private Font font;
	private int fireTimer;
	private boolean fireFrame;
	private boolean preOverworldEnded;
	private String currentMusic;
	private boolean preOverworldStarted;

	public GamePanel(GameModel g) {
		modelo=g;
		player=modelo.getPlayer();
		rooms=modelo.getRooms();
		SalaActual=rooms.getSalaActual();
		map=rooms.getSalas();
		setBackground(Color.black);
		setSize(SCREEN_HEIGHT, SCREEN_WIDTH);
		setLayout(null);
		//Colocamos al jugador
		g.getPlayer().setX(480);
		g.getPlayer().setY(320);

		//Iniciamos la musica
		music=new BackgroundMusic();
		sound=new SoundEffects();
		preOverworldEnded=false;
		currentMusic="";

		// Le paso una fuente personalizada
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/Resources/Fonts/menuFont.ttf"));
			setFont(font.deriveFont(22f));
		} catch (Exception e) {
			//Si por lo que sea no se encuentra el archivo se le da uina fuente por defecto
			setFont(new Font("Arial", Font.BOLD, 30));
		}

		//Introducción imagenes
		imgGround=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/Ground.PNG")).getImage();
		imgGround2=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/Ground2.PNG")).getImage();
		imgRock1=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/Rock1.PNG")).getImage();
		imgRock2=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/Rock2.PNG")).getImage();
		imgRock3=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/Rock3.PNG")).getImage();
		imgRock4=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/Rock4.PNG")).getImage();
		imgRock5=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/Rock5.PNG")).getImage();
		imgRock6=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/Rock6.PNG")).getImage();
		imgRock7=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/Rock7.PNG")).getImage();
		imgGreenTree=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/Tree.PNG")).getImage();
		imgLake1=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/Lake1.PNG")).getImage();
		imgLake2=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/Lake2.PNG")).getImage();
		imgLake3=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/Lake3.PNG")).getImage();
		imgLake4=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/Lake4.PNG")).getImage();
		imgLake5=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/Lake5.PNG")).getImage();
		imgLake6=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/Lake6.PNG")).getImage();
		imgLake7=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/Lake7.PNG")).getImage();
		imgLake8=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/Lake8.PNG")).getImage();
		imgLake9=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/Lake9.PNG")).getImage();
		imgIsland1=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/Island1.PNG")).getImage();
		imgIsland2=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/Island2.PNG")).getImage();
		imgIsland3=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/Island3.PNG")).getImage();
		imgIsland4=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/Island4.PNG")).getImage();
		imgGrave=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/Grave.PNG")).getImage();
		imgBridge=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/Bridge.PNG")).getImage();
		imgGuard=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/Guard.PNG")).getImage();
		imgStairs=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/Stairs.PNG")).getImage();
		imgPlayerUp1=new ImageIcon(getClass().getResource("/Resources/Sprites/Player/WalkUp1.PNG")).getImage();
		imgPlayerUp2=new ImageIcon(getClass().getResource("/Resources/Sprites/Player/WalkUp2.PNG")).getImage();
		imgPlayerDown1=new ImageIcon(getClass().getResource("/Resources/Sprites/Player/WalkDown1.PNG")).getImage();
		imgPlayerDown2=new ImageIcon(getClass().getResource("/Resources/Sprites/Player/WalkDown2.PNG")).getImage();
		imgPlayerHorizontal1=new ImageIcon(getClass().getResource("/Resources/Sprites/Player/WalkHorizontal1.PNG")).getImage();
		imgPlayerHorizontal2=new ImageIcon(getClass().getResource("/Resources/Sprites/Player/WalkHorizontal2.PNG")).getImage();
		imgSwordUp1=new ImageIcon(getClass().getResource("/Resources/Sprites/Player/SwordUp1.PNG")).getImage();
		imgSwordUp2=new ImageIcon(getClass().getResource("/Resources/Sprites/Player/SwordUp2.PNG")).getImage();
		imgSwordUp3=new ImageIcon(getClass().getResource("/Resources/Sprites/Player/SwordUp3.PNG")).getImage();
		imgSwordHorizontal1=new ImageIcon(getClass().getResource("/Resources/Sprites/Player/SwordHorizontal1.PNG")).getImage();
		imgSwordHorizontal2=new ImageIcon(getClass().getResource("/Resources/Sprites/Player/SwordHorizontal2.PNG")).getImage();
		imgSwordHorizontal3=new ImageIcon(getClass().getResource("/Resources/Sprites/Player/SwordHorizontal3.PNG")).getImage();
		imgSwordDown1=new ImageIcon(getClass().getResource("/Resources/Sprites/Player/SwordDown1.PNG")).getImage();
		imgSwordDown2=new ImageIcon(getClass().getResource("/Resources/Sprites/Player/SwordDown2.PNG")).getImage();
		imgSwordDown3=new ImageIcon(getClass().getResource("/Resources/Sprites/Player/SwordDown3.PNG")).getImage();
		imgPlayerActionDown=new ImageIcon(getClass().getResource("/Resources/Sprites/Player/ActionDown.PNG")).getImage();
		imgPlayerActionHorizontal=new ImageIcon(getClass().getResource("/Resources/Sprites/Player/ActionHorizontal.PNG")).getImage();
		imgPlayerActionUp=new ImageIcon(getClass().getResource("/Resources/Sprites/Player/ActionUp.PNG")).getImage();
		imgGatoKnight=new ImageIcon(getClass().getResource("/Resources/Sprites/Enemy/Skeleton/GatoKnight.PNG")).getImage();
		imgDuckH1=new ImageIcon(getClass().getResource("/Resources/Sprites/Enemy/Octorok/DuckHorizontal1.PNG")).getImage();
		imgDuckH2=new ImageIcon(getClass().getResource("/Resources/Sprites/Enemy/Octorok/DuckHorizontal2.PNG")).getImage();
		imgDuckV1=new ImageIcon(getClass().getResource("/Resources/Sprites/Enemy/Octorok/DuckVertical1.PNG")).getImage();
		imgDuckV2=new ImageIcon(getClass().getResource("/Resources/Sprites/Enemy/Octorok/DuckVertical2.PNG")).getImage();
		imgRockProjectile=new ImageIcon(getClass().getResource("/Resources/Sprites/Enemy/Octorok/Rock.PNG")).getImage();
		imgHeartHUD=new ImageIcon(getClass().getResource("/Resources/Sprites/HUD/HeartHUD.PNG")).getImage();
		imgEmptyHeartHUD=new ImageIcon(getClass().getResource("/Resources/Sprites/HUD/EmptyHeartHUD.PNG")).getImage();
		imgHalfHeartHUD=new ImageIcon(getClass().getResource("/Resources/Sprites/HUD/HalfHeartHUD.PNG")).getImage();
		imgKeyHUD=new ImageIcon(getClass().getResource("/Resources/Sprites/HUD/KeyHUD.PNG")).getImage();
		imgLemonHUD=new ImageIcon(getClass().getResource("/Resources/Sprites/HUD/LemonHUD.PNG")).getImage();
		imgHeart=new ImageIcon(getClass().getResource("/Resources/Sprites/Items/Heart.PNG")).getImage();
		imgKey=new ImageIcon(getClass().getResource("/Resources/Sprites/Items/Key.PNG")).getImage();
		imgHeartContainer=new ImageIcon(getClass().getResource("/Resources/Sprites/Items/HeartContainer.PNG")).getImage();
		imgHalfHeart=new ImageIcon(getClass().getResource("/Resources/Sprites/Items/HalfHeart.PNG")).getImage();
		imgRupee=new ImageIcon(getClass().getResource("/Resources/Sprites/Items/Rupee.PNG")).getImage();
		imgBlueRupee=new ImageIcon(getClass().getResource("/Resources/Sprites/Items/BlueRupee.PNG")).getImage();
		imgTreeDoor1=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/TreeDoor1.PNG")).getImage();
		imgTreeDoor2=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/TreeDoor2.PNG")).getImage();
		imgTreeDoor3=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/TreeDoor3.PNG")).getImage();
		imgTreeDoor4=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/TreeDoor4.PNG")).getImage();
		imgTreeDoor5=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/TreeDoor5.PNG")).getImage();
		imgEntrance=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/Entrance.PNG")).getImage();
		imgBrownTree=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/BrownTree.PNG")).getImage();
		imgBridgeVertical=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Overworld/BridgeVertical.PNG")).getImage();
		imgEnemyDeath1=new ImageIcon(getClass().getResource("/Resources/Sprites/Enemy/Die/EnemyDeath1.PNG")).getImage();
		imgEnemyDeath2=new ImageIcon(getClass().getResource("/Resources/Sprites/Enemy/Die/EnemyDeath2.PNG")).getImage();
		imgEnemyDeath3=new ImageIcon(getClass().getResource("/Resources/Sprites/Enemy/Die/EnemyDeath3.PNG")).getImage();
		imgEnemyDeath4=new ImageIcon(getClass().getResource("/Resources/Sprites/Enemy/Die/EnemyDeath4.PNG")).getImage();
		imgCaveGround=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Cave/Ground.PNG")).getImage();
		imgCaveRock1=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Cave/Rock1.PNG")).getImage();
		imgCaveRock2=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Cave/Rock2.PNG")).getImage();
		imgFire1=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Cave/Fire1.PNG")).getImage();
		imgFire2=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Cave/Fire2.PNG")).getImage();
		imgDungeonGround=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Dungeon/Ground.PNG")).getImage();
		imgDungeonWallHorizontal=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Dungeon/WallHorizontal.PNG")).getImage();
		imgDungeonWallVertical=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Dungeon/WallVertical.PNG")).getImage();
		imgDungeonCorner1=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Dungeon/Corner1.PNG")).getImage();
		imgDungeonCorner2=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Dungeon/Corner2.PNG")).getImage();
		imgDungeonCorner3=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Dungeon/Corner3.PNG")).getImage();
		imgDungeonCorner4=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Dungeon/Corner4.PNG")).getImage();
		imgDungeonWallUpPartHorizontal=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Dungeon/WallUpPartHorizontal.PNG")).getImage();
		imgDungeonWallUpPartVertical=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Dungeon/WallUpPartVertical.PNG")).getImage();
		imgDungeonDoorDecoration1=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Dungeon/DoorDecoration1.PNG")).getImage();
		imgDungeonDoorDecoration2=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Dungeon/DoorDecoration2.PNG")).getImage();
		imgDungeonLockDoor1=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Dungeon/LockDoor1.PNG")).getImage();
		imgDungeonLockDoor2=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Dungeon/LockDoor2.PNG")).getImage();
		imgDungeonDoorUpPart=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Dungeon/DoorUpPart.PNG")).getImage();
		imgDungeonOpenDoor1=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Dungeon/OpenDoor1.PNG")).getImage();
		imgDungeonOpenDoor2=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Dungeon/OpenDoor2.PNG")).getImage();
		imgDungeonStatue1=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Dungeon/Statue1.PNG")).getImage();
		imgDungeonStatue2=new ImageIcon(getClass().getResource("/Resources/Sprites/Map/Dungeon/Statue2.PNG")).getImage();
		imgGohmaLeg1=new ImageIcon(getClass().getResource("/Resources/Sprites/Enemy/Gohma/Leg1.PNG")).getImage();
		imgGohmaLeg2=new ImageIcon(getClass().getResource("/Resources/Sprites/Enemy/Gohma/Leg2.PNG")).getImage();
		imgGohmaEye1=new ImageIcon(getClass().getResource("/Resources/Sprites/Enemy/Gohma/Eye1.PNG")).getImage();
		imgGohmaEye2=new ImageIcon(getClass().getResource("/Resources/Sprites/Enemy/Gohma/Eye2.PNG")).getImage();
		imgGohmaEye3=new ImageIcon(getClass().getResource("/Resources/Sprites/Enemy/Gohma/Eye3.PNG")).getImage();
		imgGohmaEye4=new ImageIcon(getClass().getResource("/Resources/Sprites/Enemy/Gohma/Eye4.PNG")).getImage();
		imgDiagonalProjectile1=new ImageIcon(getClass().getResource("/Resources/Sprites/Enemy/Gohma/DiagonalProjectile1.PNG")).getImage();
		imgDiagonalProjectile2=new ImageIcon(getClass().getResource("/Resources/Sprites/Enemy/Gohma/DiagonalProjectile2.PNG")).getImage();
		imgDiagonalProjectile3=new ImageIcon(getClass().getResource("/Resources/Sprites/Enemy/Gohma/DiagonalProjectile3.PNG")).getImage();
		imgDiagonalProjectile4=new ImageIcon(getClass().getResource("/Resources/Sprites/Enemy/Gohma/DiagonalProjectile4.PNG")).getImage();
		imgFrogUp=new ImageIcon(getClass().getResource("/Resources/Sprites/Enemy/Zora/FrogUp.PNG")).getImage();
		imgFrogDown=new ImageIcon(getClass().getResource("/Resources/Sprites/Enemy/Zora/FrogDown.PNG")).getImage();
		imgZoraAppearing1=new ImageIcon(getClass().getResource("/Resources/Sprites/Enemy/Zora/Appearing1.PNG")).getImage();
		imgZoraAppearing2=new ImageIcon(getClass().getResource("/Resources/Sprites/Enemy/Zora/Appearing2.PNG")).getImage();
		imgBow=new ImageIcon(getClass().getResource("/Resources/Sprites/Items/Bow.PNG")).getImage();
		imgArrowVertical=new ImageIcon(getClass().getResource("/Resources/Sprites/Player/ArrowVertical.PNG")).getImage();
		imgArrowHorizontal=new ImageIcon(getClass().getResource("/Resources/Sprites/Player/ArrowHorizontal.PNG")).getImage();
		imgGoatDown=new ImageIcon(getClass().getResource("/Resources/Sprites/Enemy/Lynel/GoatDown.PNG")).getImage();
		imgGoatUp=new ImageIcon(getClass().getResource("/Resources/Sprites/Enemy/Lynel/GoatUp.PNG")).getImage();
		imgGoatHorizontal1=new ImageIcon(getClass().getResource("/Resources/Sprites/Enemy/Lynel/GoatHorizontal1.PNG")).getImage();
		imgGoatHorizontal2=new ImageIcon(getClass().getResource("/Resources/Sprites/Enemy/Lynel/GoatHorizontal2.PNG")).getImage();
	}
	@Override
	protected void paintComponent(Graphics g) {
		fireTimer++;
		//Es super poco probable que ocurra esto porque serian más de 400 horas de juego hasta que el int sobrepase su limite pero nunca esta de más depurar un poco
		if (fireTimer >= Integer.MAX_VALUE) {
			fireTimer = 0; // por seguridad
		}
		if (fireTimer % 10 == 0) {
			fireFrame = !fireFrame;
		}
		super.paintComponent(g);
		g.setColor(Color.black);
		SalaActual=rooms.getSalaActual();
		for (int i = 0; i < map[SalaActual].length; i++) {
			for (int j = 0; j < map[SalaActual][i].length; j++) {

				switch(map[SalaActual][i][j]) {
				case 0:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgGround, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 1:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgGround2, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 2:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgRock1, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 3:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgRock2, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 4:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgRock3, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 5:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgRock4, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 6:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgRock5, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 7:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgRock6, j*GAP, i*GAP,GAP,GAP, this);
					break;
				case 8:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgLake1, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 9:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgLake2, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 10:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgLake3, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 11:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgLake4, j*GAP, i*GAP, GAP, GAP, this);
					break;
				case 12:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgLake5, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 13:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgLake6, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 14:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgLake7, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 15:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgLake8, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 16:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgLake9, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 17:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgIsland1, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 18:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgIsland2, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 19:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgIsland3, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 20:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgIsland4, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 21:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgBridge, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 22:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgRock7, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 23:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgGreenTree, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 24:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgGuard, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 25:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgGrave, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 26:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgStairs, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 27:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgTreeDoor1, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 28:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgTreeDoor2, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 29:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgTreeDoor3, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 30:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgTreeDoor4, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 31:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgTreeDoor5, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 32:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgEntrance, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 33:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgBrownTree, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 34:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgBridgeVertical, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 35:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgCaveRock1, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 36:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgCaveRock2, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 37:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgCaveGround, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 38:
					if(fireFrame) {
						g.fillRect(j*GAP, i*GAP, GAP, GAP);	
						g.drawImage(imgFire1, j*GAP, i*GAP,GAP, GAP, this);
					}else {
						g.fillRect(j*GAP, i*GAP, GAP, GAP);	
						g.drawImage(imgFire2, j*GAP, i*GAP,GAP, GAP, this);
					}
					break;
				case 39:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgDungeonGround, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 40:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgDungeonDoorDecoration1, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 41:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgDungeonDoorDecoration2, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 42:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgDungeonLockDoor1, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 43:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgDungeonLockDoor2, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 44:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgDungeonOpenDoor1, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 45:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgDungeonOpenDoor2, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 46:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgDungeonDoorUpPart, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 47:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgDungeonWallUpPartHorizontal, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 48:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgDungeonWallUpPartVertical, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 49:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgDungeonWallUpPartHorizontal, j*GAP, i*GAP+GAP,GAP, -GAP, this);
					break;
				case 50:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgDungeonWallUpPartVertical, j*GAP+GAP, i*GAP,-GAP, GAP, this);
					break;
				case 51:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgDungeonWallHorizontal, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 52:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgDungeonWallVertical, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 53:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgDungeonCorner1, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 54:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgDungeonCorner2, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 55:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgDungeonCorner3, j*GAP, i*GAP+GAP,GAP, -GAP, this);
					break;
				case 56:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgDungeonCorner4, j*GAP, i*GAP+GAP,GAP, -GAP, this);
					break;
				case 57:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgDungeonCorner4, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 58:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgDungeonCorner3, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 59:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgDungeonCorner2, j*GAP, i*GAP+GAP,GAP, -GAP, this);
					break;
				case 60:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgDungeonCorner1, j*GAP, i*GAP+GAP,GAP, -GAP, this);
					break;
				case 61:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgDungeonStatue1, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 62:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgDungeonStatue2, j*GAP, i*GAP,GAP, GAP, this);
					break;
				case 63:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgDungeonWallHorizontal, j*GAP, i*GAP+GAP,GAP, -GAP, this);
					break;
				case 64:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgDungeonWallVertical, j*GAP+GAP, i*GAP,-GAP, GAP, this);
					break;
				case 65:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgDungeonDoorDecoration1, j*GAP, i*GAP+GAP,GAP, -GAP, this);
					break;
				case 66:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgDungeonDoorDecoration2, j*GAP, i*GAP+GAP,GAP, -GAP, this);
					break;
				case 67:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgDungeonLockDoor1, j*GAP, i*GAP+GAP,GAP, -GAP, this);
					break;
				case 68:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgDungeonLockDoor2, j*GAP, i*GAP+GAP,GAP, -GAP, this);
					break;
				case 69:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgDungeonOpenDoor1, j*GAP, i*GAP+GAP,GAP, -GAP, this);
					break;
				case 70:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgDungeonOpenDoor2, j*GAP, i*GAP+GAP,GAP, -GAP, this);
					break;
				case 71:
					g.fillRect(j*GAP, i*GAP, GAP, GAP);	
					g.drawImage(imgDungeonDoorUpPart, j*GAP, i*GAP+GAP,GAP, -GAP, this);
					break;
				}
			}	            
		}
		if(!player.isDying()) {
			playMusic();
			drawItems(g);
			drawEnemy(g);
			drawPlayer(g);
			drawHUD(g);
		}else {
			GameOver(g);
		}
	}


	private void playMusic() {
		if(SalaActual==0 || SalaActual==1 || SalaActual==2 || SalaActual==4 || SalaActual==7 || SalaActual==5 || SalaActual==6 || SalaActual==8 || SalaActual==9 || SalaActual==10 || SalaActual==11 || SalaActual==12 || SalaActual==13 || SalaActual==14 || SalaActual==15 || SalaActual==16 || SalaActual==17 || SalaActual==18 || SalaActual==20 || SalaActual==21) {
			if(!currentMusic.equals("overworld")) {
				music.stop();
				sound.stop();
				currentMusic="overworld";
				preOverworldStarted = false;
				preOverworldEnded = false;
			}
			if(!preOverworldStarted) {
				preOverworldStarted=true;
				sound.play("/Resources/SFX/Music/PreOverworld.wav");	
			}else if(!preOverworldEnded && !sound.isPlaying()) {
				preOverworldEnded=true;
				music.play("/Resources/SFX/Music/Overworld.wav");
			}
		}else if(SalaActual==23){
			if(!currentMusic.equals("boss")) {
				music.stop();
				sound.stop();
				currentMusic="boss";
				music.play("/Resources/SFX/Music/Boss.wav");
			}
		}else {
			if(!currentMusic.equals("cave"));
			currentMusic="cave";
			music.stop();
			sound.stop();
		}
		
		if(modelo.getGamefinished()) {
			music.stop();
			sound.stop();
		}
	
	}
	private void drawItems(Graphics g) {
		for (Item i : modelo.getItems()) {
			if(SalaActual==i.getRoom()) {
				if(i.isActive()) {
					if(i instanceof Heart) {
						if(i.getAmount()==2) {
							g.drawImage(imgHeart,i.getY(),i.getX(), 7*(SCALE+1), 8*(SCALE+1), this);
						}else if(i.getAmount()==1) {
							g.drawImage(imgHalfHeart,i.getY(),i.getX(), 7*(SCALE+1), 8*(SCALE+1), this);
						}
					}else if(i instanceof Rupee) {
						if(i.getAmount()==10) {
							g.drawImage(imgRupee,i.getY(),i.getX(), 4*(SCALE+1), 8*(SCALE+1), this);
						}else if(i.getAmount()==20) {
							g.drawImage(imgBlueRupee,i.getY(),i.getX(), 4*(SCALE+1), 8*(SCALE+1), this);
						}
					}else if(i instanceof Key) {
						g.drawImage(imgKey,i.getY(),i.getX(), 8*SCALE, 16*SCALE, this);
					}else if(i instanceof HeartContainer) {
						g.drawImage(imgHeartContainer,i.getY(),i.getX(), 13*SCALE, 13*SCALE, this);
					}else if (i instanceof Bow) {
						g.drawImage(imgBow,i.getY(),i.getX(), 14*SCALE, 16*SCALE, this);
					}
					if(i.getPrice()>0) {
						g.setColor(Color.WHITE);
						g.setFont(font);
						g.drawString(""+i.getPrice(), i.getY(), i.getX()+90);
					}
				}
			}
		}
	}
	private void drawPlayer(Graphics g) {
		if(player.isAction()) {
			switch(player.getDirection()) {
			case UP:
				g.drawImage(imgPlayerActionUp, player.getY(), player.getX(), GAP, GAP, this);
				break;
			case DOWN:
				g.drawImage(imgPlayerActionDown, player.getY(), player.getX(), GAP, GAP, this);
				break;
			case RIGHT:
				g.drawImage(imgPlayerActionHorizontal, player.getY(), player.getX(), GAP, GAP, this);
				break;
			case LEFT:
				g.drawImage(imgPlayerActionHorizontal, player.getY()+GAP, player.getX(), -GAP, GAP, this);
				break;
			}
		}else if(player.isAttacking()) {
			switch(player.getDirection()) {
			case UP:
				if(player.getAttackFrames()==0) {
					g.drawImage(imgPlayerUp1, player.getY(), player.getX(), GAP, GAP, this);
				}else if(player.getAttackFrames()==1) {
					g.drawImage(imgPlayerUp2, player.getY(), player.getX(), GAP, GAP, this);
					g.drawImage(imgSwordUp1, player.getY(), player.getX()-16*SCALE, GAP, GAP, this);
				}else if(player.getAttackFrames()==2) {
					g.drawImage(imgPlayerUp1, player.getY(), player.getX(), GAP, GAP, this);
					g.drawImage(imgSwordUp2, player.getY(), player.getX()-14*SCALE,GAP, GAP, this);
				}else if (player.getAttackFrames()==3 || player.getAttackFrames()==4 || player.getAttackFrames()==5 || player.getAttackFrames()==6 || player.getAttackFrames()==7) {
					g.drawImage(imgPlayerActionUp, player.getY(), player.getX(), GAP, GAP, this);
					g.drawImage(imgSwordUp3, player.getY(), player.getX()-14*SCALE, GAP, GAP, this);
				}
				break;
			case DOWN:
				if(player.getAttackFrames()==0) {
					g.drawImage(imgPlayerDown1, player.getY(), player.getX(), GAP, GAP, this);
				}else if(player.getAttackFrames()==1) {
					g.drawImage(imgPlayerDown2, player.getY(), player.getX(), GAP, GAP, this);
					g.drawImage(imgSwordDown1, player.getY(), player.getX()+15*SCALE, GAP, GAP, this);
				}else if(player.getAttackFrames()==2) {
					g.drawImage(imgPlayerDown1, player.getY(), player.getX(), GAP, GAP, this);
					g.drawImage(imgSwordDown2, player.getY(), player.getX()+15*SCALE, GAP, GAP, this);
				}else if (player.getAttackFrames()==3 || player.getAttackFrames()==4|| player.getAttackFrames()==5 || player.getAttackFrames()==6 || player.getAttackFrames()==7) {
					g.drawImage(imgPlayerActionDown, player.getY(), player.getX(), GAP, GAP, this);
					g.drawImage(imgSwordDown3, player.getY(), player.getX()+15*SCALE, GAP, GAP, this);
				}
				break;
			case RIGHT:
				if(player.getAttackFrames()==0) {
					g.drawImage(imgPlayerHorizontal1, player.getY(), player.getX(), GAP, GAP, this);
				}else if(player.getAttackFrames()==1) {
					g.drawImage(imgPlayerHorizontal1, player.getY(), player.getX(), GAP, GAP, this);
					g.drawImage(imgSwordHorizontal1, player.getY()+14*SCALE, player.getX(), GAP, GAP, this);
				}else if(player.getAttackFrames()==2) {
					g.drawImage(imgPlayerHorizontal2, player.getY(), player.getX(), GAP, GAP, this);
					g.drawImage(imgSwordHorizontal2, player.getY()+13*SCALE, player.getX(), GAP, GAP, this);
				}else if (player.getAttackFrames()==3 || player.getAttackFrames()==4 || player.getAttackFrames()==5 || player.getAttackFrames()==6 || player.getAttackFrames()==7) {
					g.drawImage(imgPlayerActionHorizontal, player.getY(), player.getX(), GAP, GAP, this);
					g.drawImage(imgSwordHorizontal3, player.getY()+14*SCALE, player.getX(), GAP, GAP, this);
				}
				break;
			case LEFT:
				//-GAP para invertir la imagen y le sumamos GAP para posicionarla bien
				if(player.getAttackFrames()==0) {
					g.drawImage(imgPlayerHorizontal1, player.getY()+GAP, player.getX(), -GAP, GAP, this);
				}else if(player.getAttackFrames()==1) {
					g.drawImage(imgPlayerHorizontal1, player.getY()+GAP, player.getX(), -GAP, GAP, this);
					g.drawImage(imgSwordHorizontal1, player.getY()+14, player.getX(), -GAP, GAP, this);
				}else if(player.getAttackFrames()==2) {
					g.drawImage(imgPlayerHorizontal2, player.getY()+GAP, player.getX(), -GAP, GAP, this);
					g.drawImage(imgSwordHorizontal2, player.getY()+13, player.getX(), -GAP, GAP, this);
				}else if (player.getAttackFrames()==3 || player.getAttackFrames()==4 || player.getAttackFrames()==5 || player.getAttackFrames()==6 || player.getAttackFrames()==7) {
					g.drawImage(imgPlayerActionHorizontal, player.getY()+GAP, player.getX(), -GAP, GAP, this);
					g.drawImage(imgSwordHorizontal3, player.getY()+14, player.getX(), -GAP, GAP, this);
				}
				break;
			}
		}else if(player.isBeingAttacked()) {
			switch(player.getDirection()) {
			case DOWN:
				if(player.getAttackedFrames()) {
					g.drawImage(imgPlayerDown1, player.getY(), player.getX(), GAP, GAP, this);
				}
				break;
			case UP:
				if(player.getAttackedFrames()) {
					g.drawImage(imgPlayerUp1, player.getY(), player.getX(), GAP, GAP, this);
				}
				break;
			case RIGHT:
				if(player.getAttackedFrames()) {
					g.drawImage(imgPlayerHorizontal1, player.getY(), player.getX(), GAP, GAP, this);
				}
				break;
			case LEFT:
				if(player.getAttackedFrames()) {
					g.drawImage(imgPlayerHorizontal1, player.getY()+GAP, player.getX(), -GAP, GAP, this);
				}
				break;
			}
		}else if(!player.isMoving()) {
			switch(player.getDirection()) {
			case UP:
				g.drawImage(imgPlayerUp1, player.getY(), player.getX(), GAP, GAP, this);
				break;
			case DOWN:
				g.drawImage(imgPlayerDown1, player.getY(), player.getX(), GAP, GAP, this);
				break;
			case RIGHT:
				g.drawImage(imgPlayerHorizontal1, player.getY(), player.getX(), GAP, GAP, this);
				break;
			case LEFT:
				//-GAP para invertir la imagen y le sumamos GAP para posicionarla bien
				g.drawImage(imgPlayerHorizontal1, player.getY()+GAP, player.getX(), -GAP, GAP, this);
				break;
			}
		}else {
			switch(player.getDirection()) {
			case UP:
				if(player.getWalkFrames()) {
					g.drawImage(imgPlayerUp1, player.getY(), player.getX(), GAP, GAP, this);
				}else {
					g.drawImage(imgPlayerUp2, player.getY(), player.getX(), GAP, GAP, this);
				}
				break;
			case DOWN:
				if(player.getWalkFrames()) {
					g.drawImage(imgPlayerDown1, player.getY(), player.getX(), GAP, GAP, this);
				}else {
					g.drawImage(imgPlayerDown2, player.getY(), player.getX(), GAP, GAP, this);
				}
				break;
			case RIGHT:
				if(player.getWalkFrames()) {
					g.drawImage(imgPlayerHorizontal1, player.getY(), player.getX(), GAP, GAP, this);
				}else {
					g.drawImage(imgPlayerHorizontal2, player.getY(), player.getX(), GAP, GAP, this);

				}
				break;
			case LEFT:
				if(player.getWalkFrames()) {
					g.drawImage(imgPlayerHorizontal1, player.getY()+GAP, player.getX(), -GAP, GAP, this);
				}else {
					//-GAP para invertir la imagen y le sumamos GAP para posicionarla bien
					g.drawImage(imgPlayerHorizontal2, player.getY()+GAP, player.getX(), -GAP, GAP, this);
				}
				break;
			}
		}
	}
	private void drawEnemy(Graphics g) {
		for(Enemy e: modelo.getEnemies()) {
			if(SalaActual==e.getRoom()) {
				if(e.isDying() && e.isAlive()) {
					if(e.getDyingFrames()==0) {
						g.drawImage(imgEnemyDeath1, e.getY(), e.getX(), 15*SCALE, 16*SCALE, this);
					}else if(e.getDyingFrames()==1) {
						g.drawImage(imgEnemyDeath2, e.getY(), e.getX(), 15*SCALE, 14*SCALE, this);
					}else if(e.getDyingFrames()==2) {
						g.drawImage(imgEnemyDeath3, e.getY(), e.getX(), 9*SCALE, 10*SCALE, this);
					}else if(e.getDyingFrames()==3) {
						g.drawImage(imgEnemyDeath4, e.getY(), e.getX(), 9*SCALE, 10*SCALE, this);
					}
				}else if(e.isAlive()) {
					if(e instanceof ShooterEnemy) { //Comprueba si es un shooterEnemy o un followerEnemy
						if(e.isBeingAttacked()) {
							switch(e.getDirection()) {
							case UP:
								if(e.getAttackedFrames()) {
									g.drawImage(imgDuckV1, e.getY(), e.getX(), 16*SCALE, 16*SCALE, this);
								}
								break;
							case DOWN:
								if(e.getAttackedFrames()) {
									g.drawImage(imgDuckV1, e.getY(), e.getX()+16*SCALE, 16*SCALE, -16*SCALE, this);
								}
								break;
							case RIGHT:
								if(e.getAttackedFrames()) {
									g.drawImage(imgDuckH1, e.getY()+16*SCALE, e.getX(), -16*SCALE, 16*SCALE, this);
								}
								break;
							case LEFT:
								if(e.getAttackedFrames()) {
									g.drawImage(imgDuckH1, e.getY(), e.getX(), 16*SCALE, 16*SCALE, this);
								}
								break;
							}
						}else if(e.isMoving()) {
								switch(e.getDirection()) {
								case UP:
									if(e.getWalkFrames()) {
										g.drawImage(imgDuckV1, e.getY(), e.getX()+16*SCALE, 16*SCALE, -16*SCALE, this);
									}else {
										g.drawImage(imgDuckV2, e.getY(), e.getX()+16*SCALE, 16*SCALE, -14*SCALE, this);
									}
									break;
								case DOWN:
									if(e.getWalkFrames()) {
										g.drawImage(imgDuckV1, e.getY(), e.getX(), 16*SCALE, 16*SCALE, this);
									}else {
										g.drawImage(imgDuckV2, e.getY(), e.getX(), 16*SCALE, 14*SCALE, this);
									}
									break;
								case RIGHT:
									if(e.getWalkFrames()) {
										g.drawImage(imgDuckH1, e.getY()+16*SCALE, e.getX(), -16*SCALE, 16*SCALE, this);
									}else {
										g.drawImage(imgDuckH2, e.getY()+14*SCALE, e.getX(), -14*SCALE, 16*SCALE, this);
									}
									break;
								case LEFT:
									if(e.getWalkFrames()) {
										g.drawImage(imgDuckH1, e.getY(), e.getX(), 16*SCALE, 16*SCALE, this);
									}else {
										g.drawImage(imgDuckH2, e.getY()+2*SCALE, e.getX(), 14*SCALE, 16*SCALE, this);
									}
									break;
								}
							}else {
								switch(e.getDirection()){
								case UP:
									g.drawImage(imgDuckV1, e.getY(), e.getX(), 16*SCALE, 16*SCALE, this);
									break;
								case DOWN:
									g.drawImage(imgDuckV1, e.getY(), e.getX()+16*SCALE, 16*SCALE, -16*SCALE, this);
									break;
								case RIGHT:
									g.drawImage(imgDuckH1, e.getY()+16*SCALE, e.getX(), -16*SCALE, 16*SCALE, this);
									break;
								case LEFT:
									g.drawImage(imgDuckH1, e.getY(), e.getX(), 16*SCALE, 16*SCALE, this);
									break;
								}
							}
						}else if(e instanceof FollowerEnemy) {
						if(e.isBeingAttacked()) {
							if(e.getAttackedFrames()) {
								g.drawImage(imgGatoKnight, e.getY(), e.getX(), 15*SCALE, 16*SCALE, this);
							}
						}else {
							if(e.getWalkFrames()) {
								g.drawImage(imgGatoKnight, e.getY(), e.getX(), 15*SCALE, 16*SCALE, this);
							}else {
								g.drawImage(imgGatoKnight, e.getY()+15*SCALE, e.getX(), -15*SCALE, 16*SCALE, this);
							}
						}
					}else if(e instanceof TourretEnemy){
						if(((TourretEnemy) e).isAtSight()) {
							if(((TourretEnemy) e).isApearing()) {
								if(((TourretEnemy) e).isApearingFrames()) {
									g.drawImage(imgZoraAppearing1, e.getY(), e.getX(), 12*SCALE, 4*SCALE, this);
								}else {
									g.drawImage(imgZoraAppearing1, e.getY(), e.getX(), 10*SCALE, 5*SCALE, this);
								}
							}else if(e.isBeingAttacked()){
								if(e.getAttackedFrames()) {
									g.drawImage(imgFrogUp, e.getY(), e.getX(), 16*SCALE, 16*SCALE, this);
								}
							}else {
								switch(e.getDirection()) {
								case UP:
									g.drawImage(imgFrogUp, e.getY(), e.getX(), 16*SCALE, 16*SCALE, this);
									break;
								case DOWN:
									g.drawImage(imgFrogDown, e.getY(), e.getX(), 16*SCALE, 16*SCALE, this);
									break;
								default:
									break;
								}
							}
						}
					}else if(e instanceof TackleEnemy) {
						if(e.isBeingAttacked()) {
							switch(e.getDirection()) {
							case UP:
								if(e.getAttackedFrames()) {
									g.drawImage(imgGoatUp, e.getY(), e.getX(), 14*SCALE, 16*SCALE, this);
								}
								break;
							case DOWN:
								if(e.getAttackedFrames()) {
									g.drawImage(imgGoatDown, e.getY(), e.getX(), 15*SCALE, 16*SCALE, this);
								}
								break;
							case RIGHT:
								if(e.getAttackedFrames()) {
									g.drawImage(imgGoatHorizontal1, e.getY()+16*SCALE, e.getX(), -16*SCALE, 15*SCALE, this);
								}
								break;
							case LEFT:
								if(e.getAttackedFrames()) {
									g.drawImage(imgGoatHorizontal1, e.getY()+16*SCALE, e.getX(), -16*SCALE, 15*SCALE, this);
								}
								break;
							}
						}else if(e.isMoving()) {
							switch(e.getDirection()) {
							case UP:
								if(e.getWalkFrames()) {
									g.drawImage(imgGoatUp, e.getY()+14*SCALE, e.getX(), -14*SCALE, 16*SCALE, this);
								}else {
									g.drawImage(imgGoatUp, e.getY(), e.getX(), 14*SCALE, 16*SCALE, this);
								}
								break;
							case DOWN:
								if(e.getWalkFrames()) {
									g.drawImage(imgGoatDown, e.getY(), e.getX(), 15*SCALE, 16*SCALE, this);
								}else {
									g.drawImage(imgGoatDown, e.getY()+15*SCALE, e.getX(), -15*SCALE, 16*SCALE, this);
								}
								break;
							case RIGHT:
								if(e.getWalkFrames()) {
									g.drawImage(imgGoatHorizontal1, e.getY(), e.getX(), 16*SCALE, 15*SCALE, this);
								}else {
									g.drawImage(imgGoatHorizontal2, e.getY(), e.getX(), 16*SCALE, 15*SCALE, this);
								}
								break;
							case LEFT:
								if(e.getWalkFrames()) {
									g.drawImage(imgGoatHorizontal1, e.getY()+16*SCALE, e.getX(), -16*SCALE, 15*SCALE, this);
								}else {
									g.drawImage(imgGoatHorizontal2, e.getY()+16*SCALE, e.getX(), -16*SCALE, 15*SCALE, this);
								}
								break;
							}
						}else {
							switch(e.getDirection()){
							case UP:
								g.drawImage(imgGoatUp, e.getY(), e.getX(), 14*SCALE, 16*SCALE, this);
								break;
							case DOWN:
								g.drawImage(imgGoatDown, e.getY(), e.getX(), 15*SCALE, 16*SCALE, this);
								break;
							case RIGHT:
								g.drawImage(imgGoatHorizontal1, e.getY(), e.getX(), 16*SCALE, 15*SCALE, this);
								break;
							case LEFT:
								g.drawImage(imgGoatHorizontal1, e.getY()+16*SCALE, e.getX(), -16*SCALE, 15*SCALE, this);
								break;
							}
						}
				}else if(e instanceof Boss) {
						if(e.isBeingAttacked()) {
							if(e.getAttackedFrames()) {
								g.drawImage(imgGohmaLeg1, e.getY()-(16*SCALE), e.getX(), 16*SCALE, 16*SCALE, this);
								g.drawImage(imgGohmaEye4, e.getY(), e.getX(), 16*SCALE, 16*SCALE, this);
								g.drawImage(imgGohmaLeg2, e.getY()+(16*SCALE), e.getX(), 16*SCALE, 16*SCALE, this);	
							}
						}else {
							if(e.getWalkFrames()) {
								g.drawImage(imgGohmaLeg1, e.getY()-(16*SCALE), e.getX(), 16*SCALE, 16*SCALE, this);
								g.drawImage(imgGohmaLeg2, e.getY()+(16*SCALE), e.getX(), 16*SCALE, 16*SCALE, this);	
							}else {
								g.drawImage(imgGohmaLeg2, e.getY()-(16*SCALE)+(16*SCALE), e.getX(), -16*SCALE, 16*SCALE, this);
								g.drawImage(imgGohmaLeg1, e.getY()+(16*SCALE)+(16*SCALE), e.getX(), -16*SCALE, 16*SCALE, this);	
							}
							if(e.getEyeFrame()==0) {
								g.drawImage(imgGohmaEye1, e.getY(), e.getX(), 16*SCALE, 16*SCALE, this);
							}else if(e.getEyeFrame()==1) {
								g.drawImage(imgGohmaEye2, e.getY(), e.getX(), 16*SCALE, 16*SCALE, this);
							}else if(e.getEyeFrame()==2) {
								g.drawImage(imgGohmaEye3, e.getY(), e.getX(), 16*SCALE, 16*SCALE, this);
							}else if(e.getEyeFrame()==3) {
								g.drawImage(imgGohmaEye4, e.getY(), e.getX(), 16*SCALE, 16*SCALE, this);
							}
						}
					}

				}
			}
		for(Projectile t : modelo.getProjectiles()) {
			if(t.isActive()) {
				String owner=t.getOwner();
				if(t instanceof TargetProjectile) {
					if(t.getFrames()==0) {
						g.drawImage(imgDiagonalProjectile1, t.getY(), t.getX()+10*SCALE, 8*SCALE, -10*SCALE, this);
					}else if(t.getFrames()==1) {
						g.drawImage(imgDiagonalProjectile2, t.getY(), t.getX()+10*SCALE, 8*SCALE, -10*SCALE, this);
					}else if(t.getFrames()==2) {
						g.drawImage(imgDiagonalProjectile3, t.getY(), t.getX()+10*SCALE, 8*SCALE, -10*SCALE, this);
					}else if(t.getFrames()==3) {
						g.drawImage(imgDiagonalProjectile4, t.getY(), t.getX()+10*SCALE, 8*SCALE, -10*SCALE, this);
					}
				}else if(t instanceof StraightProjectile) {
					if(owner.equals("player")) {
						switch(t.getDirection()) {
						case UP:
							g.drawImage(imgArrowVertical, t.getY(), t.getX(), 5*SCALE, 16*SCALE, this);
							break;
						case DOWN:
							g.drawImage(imgArrowVertical, t.getY(), t.getX()+(16*SCALE), 5*SCALE, -16*SCALE, this);
							break;
						case RIGHT:
							g.drawImage(imgArrowHorizontal, t.getY(), t.getX(), 16*SCALE, 5*SCALE, this);
							break;
						case LEFT:
							g.drawImage(imgArrowHorizontal, t.getY()+(16*SCALE), t.getX(), -16*SCALE, 5*SCALE, this);
							break;
						}
					}else if(owner.equals("enemy")) {
						if(t.getFrames()==0) {
							g.drawImage(imgRockProjectile, t.getY(), t.getX(), 8*SCALE, 10*SCALE, this);
						}else if(t.getFrames()==1){
							g.drawImage(imgRockProjectile, t.getY(), t.getX()+10*SCALE, 8*SCALE, -10*SCALE, this);
						}
					}
				}
			}
		}
	}
	}
	private void drawHUD(Graphics g) {
		//Vida
		g.setColor(new Color(0, 0, 0, 150));
		if(player.getMaxHealth()==6) {
			g.fillRect(5, 5, 31*SCALE, 12*SCALE);
		}else if(player.getMaxHealth()==8) {
			g.fillRect(5, 5, 41*SCALE, 12*SCALE);
		}
		switch(player.getHealth()) {
		case 1:
			g.drawImage(imgHalfHeartHUD,10,10, 7*(SCALE+1), 8*(SCALE+1), this);
			g.drawImage(imgEmptyHeartHUD,12*SCALE, 10, 7*(SCALE+1), 8*(SCALE+1), this);
			g.drawImage(imgEmptyHeartHUD,22*SCALE, 10, 7*(SCALE+1), 8*(SCALE+1), this);
			if(player.getMaxHealth()==8) {
				g.drawImage(imgEmptyHeartHUD,32*SCALE,10, 7*(SCALE+1), 8*(SCALE+1), this);
			}
			break;
		case 2:
			g.drawImage(imgHeartHUD,10,10, 7*(SCALE+1), 8*(SCALE+1), this);
			g.drawImage(imgEmptyHeartHUD,12*SCALE, 10, 7*(SCALE+1), 8*(SCALE+1), this);
			g.drawImage(imgEmptyHeartHUD,22*SCALE, 10, 7*(SCALE+1), 8*(SCALE+1), this);
			if(player.getMaxHealth()==8) {
				g.drawImage(imgEmptyHeartHUD,32*SCALE,10, 7*(SCALE+1), 8*(SCALE+1), this);
			}
			break;
		case 3:
			g.drawImage(imgHeartHUD,10,10, 7*(SCALE+1), 8*(SCALE+1), this);
			g.drawImage(imgHalfHeartHUD,12*SCALE,10, 7*(SCALE+1), 8*(SCALE+1), this);
			g.drawImage(imgEmptyHeartHUD,22*SCALE,10, 7*(SCALE+1), 8*(SCALE+1), this);
			if(player.getMaxHealth()==8) {
				g.drawImage(imgEmptyHeartHUD,32*SCALE,10, 7*(SCALE+1), 8*(SCALE+1), this);
			}
			break;
		case 4:
			g.drawImage(imgHeartHUD,10,10, 7*(SCALE+1), 8*(SCALE+1), this);
			g.drawImage(imgHeartHUD,12*SCALE,10, 7*(SCALE+1), 8*(SCALE+1), this);
			g.drawImage(imgEmptyHeartHUD,22*SCALE,10, 7*(SCALE+1), 8*(SCALE+1), this);
			if(player.getMaxHealth()==8) {
				g.drawImage(imgEmptyHeartHUD,32*SCALE,10, 7*(SCALE+1), 8*(SCALE+1), this);
			}
			break;
		case 5:
			g.drawImage(imgHeartHUD,10,10, 7*(SCALE+1), 8*(SCALE+1), this);
			g.drawImage(imgHeartHUD,12*SCALE,10, 7*(SCALE+1), 8*(SCALE+1), this);
			g.drawImage(imgHalfHeartHUD,22*SCALE,10, 7*(SCALE+1), 8*(SCALE+1), this);
			if(player.getMaxHealth()==8) {
				g.drawImage(imgEmptyHeartHUD,32*SCALE,10, 7*(SCALE+1), 8*(SCALE+1), this);
			}
			break;
		case 6:
			g.drawImage(imgHeartHUD,10,10, 7*(SCALE+1), 8*(SCALE+1), this);
			g.drawImage(imgHeartHUD,12*SCALE,10, 7*(SCALE+1), 8*(SCALE+1), this);
			g.drawImage(imgHeartHUD,22*SCALE,10, 7*(SCALE+1), 8*(SCALE+1), this);
			if(player.getMaxHealth()==8) {
				g.drawImage(imgEmptyHeartHUD,32*SCALE,10, 7*(SCALE+1), 8*(SCALE+1), this);
			}
			break;
		case 7:
			g.drawImage(imgHeartHUD,10,10, 7*(SCALE+1), 8*(SCALE+1), this);
			g.drawImage(imgHeartHUD,12*SCALE,10, 7*(SCALE+1), 8*(SCALE+1), this);
			g.drawImage(imgHeartHUD,22*SCALE,10, 7*(SCALE+1), 8*(SCALE+1), this);
			g.drawImage(imgHalfHeartHUD,32*SCALE,10, 7*(SCALE+1), 8*(SCALE+1), this);
			break;
		case 8:
			g.drawImage(imgHeartHUD,10,10, 7*(SCALE+1), 8*(SCALE+1), this);
			g.drawImage(imgHeartHUD,12*SCALE,10, 7*(SCALE+1), 8*(SCALE+1), this);
			g.drawImage(imgHeartHUD,22*SCALE,10, 7*(SCALE+1), 8*(SCALE+1), this);
			g.drawImage(imgHeartHUD,32*SCALE,10, 7*(SCALE+1), 8*(SCALE+1), this);
			break;
		}


		g.fillRect(SCREEN_WIDTH-180, 5, (2*SCALE*TILES_RES)+35, 22*SCALE);
		g.drawImage(imgLemonHUD, SCREEN_WIDTH-170,15, 8*SCALE, 8*SCALE, this);
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("x" + player.getMoney(), SCREEN_WIDTH-110, 37);

		// llaves
		g.drawImage(imgKeyHUD, SCREEN_WIDTH-170, 55, 8*SCALE, 8*SCALE, this);
		g.drawString("x" + player.getKeys(), SCREEN_WIDTH-110, 77);
	}
	public GamePanel getPanel() {
		return this; //Devolvemos el panel
	}

	public void GameOver(Graphics g) {
		//Para que al morir los valores de la musica esten reiniciados
		music.stop();
		preOverworldStarted = false;
	    preOverworldEnded = false;
	    currentMusic = "";
		if(player.getDyingFrames()==0) {
			g.drawImage(imgPlayerUp1, player.getY(), player.getX(), GAP, GAP, this);
		}else if(player.getDyingFrames()==1) {
			g.drawImage(imgPlayerHorizontal1, player.getY(), player.getX(), GAP, GAP, this);
		}else if(player.getDyingFrames()==2) {
			g.drawImage(imgPlayerDown1, player.getY(), player.getX(), GAP, GAP, this);
		}else if(player.getDyingFrames()==3) {
			g.drawImage(imgPlayerHorizontal1, player.getY()+GAP, player.getX(), -GAP, GAP, this);
		}
	}

	public void setModel(GameModel modelo) {
		this.modelo = modelo;
		player=modelo.getPlayer();
		rooms=modelo.getRooms();
		SalaActual=rooms.getSalaActual();
		map=rooms.getSalas();
		player.setX(480);
		player.setY(320);
	}
}