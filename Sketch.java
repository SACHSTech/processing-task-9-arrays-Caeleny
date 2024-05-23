import processing.core.PApplet;

public class Sketch extends PApplet {

  // Variables for the locations of the snowflakes
  float[] circleY = new float[25];
  float[] circleX = new float[25];

  // Speed of snowlake
  float speed = 1; 

  // Variable for the visibility of the snowflakes
  boolean[] hide = new boolean[25];

  // Variable for the player position and lives
  float playerX, playerY;
  int lives = 3;

  // Variable for ending the game
  boolean end = false;

  public void settings() {
    // Background size
    size(400, 400);
  }

  public void setup() {
    // Play width and
    playerX = width / 2;
    playerY = height - 50;

    for (int i = 0; i < circleY.length; i++) {
      circleY[i] = random(height);
      circleX[i] = random(width);

      hide[i] = false;
    }
  }

  public void draw() {
    
    if (end) {
      background(255); 
  
    } else {
      background(50); 

    
    for (int i = 0; i < circleY.length; i++) {
      if (!hide[i]) {
        ellipse(circleX[i], circleY[i], 35, 35); 

          circleY[i] += speed;

        if (circleY[i] > height) {
            circleY[i] = 0;
            circleX[i] = random(width);
        }

         // Check for collision with player
        if (dist(playerX, playerY, circleX[i], circleY[i]) < 35 / 2 + 30 / 2) {
          lives--;
          circleY[i] = 0; // Reset snowflake position
          circleX[i] = random(width); // Reset snowflake position

          if (lives == 0) {
            end = true;
          }
        }
      }
    }
  
    // Draw circle
    fill(0, 0, 255);
    ellipse(playerX, playerY, 30, 30);

    // Displays 3 lives
    fill(255, 0, 0);
    for (int i = 0; i < lives; i++) {
      rect(width - (i + 1) * 20 - 10, 10, 15, 15);
      
     }
    }
  }
  

  public void keyPressed() {
      // Move the player according to the keys pressed
      if (key == 'a') {
        playerX -= 10;
      } else if (key == 'd') {
        playerX += 10;
      } else if (key == 'w') {
        playerY -= 10;
      } else if (key == 's') {
        playerY += 10;

      // Change the speed of the snowballs
      } else if (keyCode == DOWN) {
        speed++;
      } else if (keyCode == UP) {
        speed = max(0, speed - 1); 
      }
    }
  
    public void mousePressed() {
      // Delete snowballs when clicked on by the mouse
      for (int i = 0; i < circleY.length; i++) {
        if (!hide[i] && dist(mouseX, mouseY, circleX[i], circleY[i]) < 35 / 2) {
          hide[i] = true; 
        }
      }

}
}




