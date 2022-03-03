package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

 public int worldX,worldY;
 public int speed;
 
 public BufferedImage up1, down1, left1, right1,dead1;
 public String direction;
 
 public Rectangle solidArea;
 public boolean collisionOn = false;
 public boolean gameOver = false;
}
