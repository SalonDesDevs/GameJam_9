package fr.adamaq01.gravirush.screens;

import fr.adamaq01.gravirush.Screens;
import fr.adamaq01.suplge.api.Game;
import fr.adamaq01.suplge.api.IScreen;
import fr.adamaq01.suplge.api.graphics.Color;
import fr.adamaq01.suplge.api.input.controllers.IController;
import fr.adamaq01.suplge.input.ControllerManager;
import fr.adamaq01.suplge.opengl.GLWindow;
import fr.adamaq01.suplge.opengl.graphics.GLGraphics;

import java.util.Random;

/**
 * Created by Adamaq01 on 22/04/2017.
 */
public class Credits implements IScreen<GLWindow, GLGraphics> {

    private Random random;

    @Override
    public void onEnable(Game<GLWindow> game) {
        this.random = new Random();
    }

    @Override
    public void onDisable(Game<GLWindow> game) {
    }

    @Override
    public void update(Game<GLWindow> game, double v) {
        IController controller = ControllerManager.MANAGER.get(0);
        if(controller.isButtonPressed(IController.Button.BUTTON_ACTION_DOWN)) {
            game.setCurrentScreen(Screens.MAIN_MENU);
        }
    }

    @Override
    public void render(Game<GLWindow> game, GLGraphics glGraphics) {
        glGraphics.setColor(Color.GOLD);
        glGraphics.drawString("Jeu cree par:", game.getWindow().getWidth() / 2 - ("Jeu cree par:".length() * "Jeu cree par:".length()), game.getWindow().getHeight() / 2, 1);
        glGraphics.setRotation(random.nextInt(22) - 11);
        glGraphics.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
        glGraphics.drawString("Adamaq01", game.getWindow().getWidth() / 2 - ("Adamaq01".length() * "Adamaq01".length()), game.getWindow().getHeight() / 2 + 48, 1);
        glGraphics.setRotation(0);
    }
}
