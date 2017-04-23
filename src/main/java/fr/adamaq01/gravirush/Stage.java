package fr.adamaq01.gravirush;

import fr.adamaq01.suplge.api.Game;
import fr.adamaq01.suplge.api.graphics.Color;
import fr.adamaq01.suplge.api.graphics.IGraphics;
import fr.adamaq01.suplge.opengl.graphics.shapes.Rectangle;

import java.util.Random;

/**
 * Created by Adamaq01 on 22/04/2017.
 */
public class Stage {

    private Random random;
    private boolean level[][];

    private int doorX, doorY;

    public void init(Game game) {
        random = new Random();
        level = new boolean[1000][1000];

        for(int x = 0; x < 1000; x++) {
            for(int y = 0; y < 1000; y++) {
                boolean draw = random.nextInt(100) > 30;
                level[x][y] = !draw;
            }
        }

        generateDoor(game);

    }

    public void generateDoor(Game game) {
        int doorX = random.nextInt(game.getWindow().getWidth() / 24);
        int doorY = random.nextInt(game.getWindow().getWidth() / 24);

        if(level[doorX][doorY] && !level[doorX][doorY + (32 / 24)]) {
            this.doorX = doorX;
            this.doorY = doorY + (32 / 24);
        } else {
            generateDoor(game);
        }
    }

    public void update(Game game, int playerX, int playerY, double delta) {
        if(new Rectangle(16, 32).collides(playerX, playerY, doorX * 24, doorY * 24)) {
            GraviRush.getSocket().emit("changepixel");
            game.setCurrentScreen(Screens.MAIN_MENU);
        }
    }

    public void drawStage(IGraphics graphics) {
        for(int x = 0; x < graphics.getWindow().getWidth() / 24; x++) {
            for (int y = 0; y < graphics.getWindow().getHeight() / 24; y++) {
                if(level[x][y]) {
                    graphics.setColor(new Color(77, 51, 25));
                    graphics.fillShape(new Rectangle(32, 32), x * 24, y * 24);
                    graphics.setColor(new Color(0, 153, 51));
                    graphics.fillShape(new Rectangle(32, 4), x * 24, y * 24 + 28);
                }
            }
        }
        graphics.drawShape(new Rectangle(16, 32), doorX * 24, doorY * 24);
    }

    public boolean collides(Game game, int x, int y) {
        for(int rectx = 0; rectx < game.getWindow().getWidth() / 24; rectx++) {
            for (int recty = 0; recty < game.getWindow().getHeight() / 24; recty++) {
                if(level[rectx][recty]) {
                    new Rectangle(32, 32).collides(x, y, rectx * 24, recty * 24);
                }
            }
        }
        return false;
    }

}
