package entity;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

 GamePanel gp;
 KeyHandler keyH;
 public final int screenX;
 public final int screenY;
 
 public Player(GamePanel gp, KeyHandler keyH) {
  
  this.gp = gp;
  this.keyH = keyH;
  
  screenX = gp.screenWidth/2 - (gp.tileSize/2);
  screenY = gp.screenHeight/2 - (gp.tileSize/2);
  
  solidArea = new Rectangle();
  solidArea.x = 16;
  solidArea.y = 16;
  solidArea.width = 32;
  solidArea.height = 21;
  
  setDefaultValues();
  getPlayerImage();
 }
 
 public void setDefaultValues() {
  // in entity
  worldX = gp.tileSize * 23; 
  worldY = gp.tileSize * 21;
  speed = 4;
  direction = "down";
 }
 public void getPlayerImage() {
  
  try {
   
   up1 = ImageIO.read(new File("res/player/robot_N.png"));
   down1 = ImageIO.read(new File("res/player/robot_S.png"));
   left1 = ImageIO.read(new File("res/player/robot_L.png"));
   right1 = ImageIO.read(new File("res/player/robot_R.png"));
   dead1 = ImageIO.read(new File("res/player/robot_D.png"));
   
  } catch(IOException e) {
   e.printStackTrace();
  }
 }
 
 public void update() {
  
   if(keyH.upPressed == true || keyH.downPressed == true ||
     keyH.leftPressed == true || keyH.rightPressed == true) {
    
    if(keyH.upPressed == true) {
     direction = "up";
    }
    else if(keyH.downPressed == true) {
     direction = "down"; 
    }
    else if(keyH.leftPressed == true) {
     direction = "left"; 
    }
    else if(keyH.rightPressed == true) {
     direction = "right"; 
    }
    
    // check tile collision
    collisionOn = false;

    // check bomb collision
    gameOver = false;
    
    gp.cChecker.checkTile(this);
    
    if(gameOver == true) {
      gp.setupGame();
//    	gp.end();
//      System.exit(0);
    }
    
    //if collision is false, player can move
    if(collisionOn == false) {
     
     switch(direction) {
     case "up":
      worldY = worldY - speed;
      break;
      
     case"down":
      worldY = worldY + speed;
      break;
      
     case"left":
      worldX = worldX - speed;
      break;
      
     case"right":
      worldX = worldX + speed;
      break;
     }
    }
   }
 }
 public void draw(Graphics2D g2) {
  
//  g2.setColor(Color.white);
//  g2.fillRect(x, y, gp.tileSize, gp.tileSize);
  
  BufferedImage image = null;
  
  switch(direction) {
  case "up":
   image = up1;
   break;
  case "down":
   image = down1;
   break;
  case "left":
   image = left1;
   break;
  case "right":
   image = right1;
   break;
  }
  g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
  
 }
}