package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {

 GamePanel gp;
 Graphics2D g2;
 Font arial_40, arial_80B;
 public int commandNum = 0;

 public UI(GamePanel gp) {

  this.gp = gp;

  arial_40 = new Font("Arial", Font.PLAIN, 40);
  arial_80B = new Font("Arial", Font.BOLD, 80);
 }

 public void draw(Graphics2D g2) {
  this.g2 = g2;

  g2.setFont(arial_40);
  g2.setColor(Color.BLACK);

  // end
  if (gp.gameState == gp.endState) {
   drawEndScreen();
  }
    
  // title
  if (gp.gameState == gp.titleState) {
   drawTitleScreen();
  }
  // play state
  if (gp.gameState == gp.playState) {

  }

 }

 public void drawTitleScreen() {

  g2.setColor(new Color(169, 169, 169));
  g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

  // title
  g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 96F));
  String text = "Tank 2D Game";
  int x = getXforCenterText(text);
  int y = gp.tileSize * 3;

  // shadow
  g2.setColor(Color.black);
  g2.drawString(text, x + 5, y + 5);

  // main
  g2.setColor(Color.ORANGE);
  g2.drawString(text, x, y);

  // image
  x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
  y = y + gp.tileSize * 2;
  g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

  // menu
  g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));

  text = "1 Player";
  x = getXforCenterText(text);
  y = y + gp.tileSize * 4;
  g2.drawString(text, x, y);
  if (commandNum == 0) {
   g2.drawString(">", x - gp.tileSize, y);
  }
  
  text = "2 Players";
  x = getXforCenterText(text);
  y = y + gp.tileSize;
  g2.drawString(text, x, y);
  if (commandNum == 1) {
   g2.drawString(">", x - gp.tileSize, y);
  }

  text = "Quit Game";
  x = getXforCenterText(text);
  y = y + gp.tileSize;
  g2.drawString(text, x, y);
  if (commandNum == 2) {
   g2.drawString(">", x - gp.tileSize, y);
  }
 }

 // End Screen
 public void drawEndScreen() {

  g2.setColor(new Color(169, 169, 169));
  g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

  // title
  g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 96F));
  String text = "Tank 2D Game";
  int x = getXforCenterText(text);
  int y = gp.tileSize * 3;

  // shadow
  g2.setColor(Color.black);
  g2.drawString(text, x + 5, y + 5);

  // main
  g2.setColor(Color.ORANGE);
  g2.drawString(text, x, y);

  // image
  x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
  y = y + gp.tileSize * 2;
  g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

  // menu
  g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));

  text = "Try Again";
  x = getXforCenterText(text);
  y = y + gp.tileSize * 4;
  g2.drawString(text, x, y);
  if (commandNum == 0) {
   g2.drawString(">", x - gp.tileSize, y);
  }

  text = "Quit Game";
  x = getXforCenterText(text);
  y = y + gp.tileSize;
  g2.drawString(text, x, y);
  if (commandNum == 2) {
   g2.drawString(">", x - gp.tileSize, y);
  }
 }

 public int getXforCenterText(String text) {

  int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
  int x = gp.screenWidth / 2 - length / 2;
  return x;
 }
}