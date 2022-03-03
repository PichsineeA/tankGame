package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

 // screen settings
 final int originalTileSize = 16; // 16x16 tile
 final int scale = 3;

 public final int tileSize = originalTileSize * scale; // 48x48 tile
 public final int maxScreenCol = 20;
 public final int maxScreenRow = 15;
 public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
 public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

 // world setting
 public final int maxWorldCol = 100;
 public final int maxWorldRow = 80;
 public final int worldWidth = tileSize * maxWorldCol;
 public final int worldHeight = tileSize * maxWorldRow;


 // FPS frame per sec
 int FPS = 60;

 TileManager tileM = new TileManager(this);
 KeyHandler keyH = new KeyHandler(this);
 Thread gameThread;
 public CollisionChecker cChecker = new CollisionChecker(this);
 public UI ui = new UI(this);
 public Player player = new Player(this, keyH);

 // game state
 public int gameState;
 public final int titleState = 0;
 public final int playState = 1;
 public final int endState = 2;
 // public final int pauseState = 2;
 
 public GamePanel() {

  this.setPreferredSize(new Dimension(screenWidth, screenHeight));
  this.setBackground(Color.black);
  this.setDoubleBuffered(true);
  this.addKeyListener(keyH);
  this.setFocusable(true);
 }

 public void setupGame() {
  
  gameState = titleState;
  
 }
 
 public void startGameThread() {

  gameThread = new Thread(this);
  gameThread.start();

 }

 @Override
 public void run() {

  double drawInterval = 1000000000 / FPS; // 0.01666 seconds
  double nextDrawTime = System.nanoTime() + drawInterval;

  while (gameThread != null) {

   update();

   repaint();

   try {
    double remainingTime = nextDrawTime - System.nanoTime();
    remainingTime = remainingTime / 1000000; // 1 second = 1,000,000 nan0seconds

    if (remainingTime < 0) {
     remainingTime = 0;
    }

    Thread.sleep((long) remainingTime);

    nextDrawTime = nextDrawTime + drawInterval;

   } catch (InterruptedException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }
  }
 }

 public void update() {

  if(gameState == playState) {
   player.update();
  }
//  if(gameState == pauseState) {
//   
//  }
 }

 public void paintComponent(Graphics g) {

  super.paintComponent(g);

  Graphics2D g2 = (Graphics2D) g;


  // title screen 
  if(gameState == titleState) {
    ui.draw(g2);
  }
  //end
//  if(gameState == endState) {
//     ui.draw(g2);
//   }
  //other
  else {
   // tile
   tileM.draw(g2);
   
   // player
   player.draw(g2);
  }
  
  g2.dispose();
 }
 
// public void end() {
//	 Graphics2D g2;
//	 ui.draw(g2);
//	 ui.drawTitleScreen();
// }

}