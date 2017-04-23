package fr.adamaq01.gravirush.screens;

import fr.adamaq01.gravirush.Screens;
import fr.adamaq01.suplge.SuplgeEngine;
import fr.adamaq01.suplge.api.Game;
import fr.adamaq01.suplge.api.IImage;
import fr.adamaq01.suplge.api.IScreen;
import fr.adamaq01.suplge.api.graphics.Color;
import fr.adamaq01.suplge.api.input.controllers.IController;
import fr.adamaq01.suplge.input.ControllerManager;
import fr.adamaq01.suplge.opengl.GLWindow;
import fr.adamaq01.suplge.opengl.graphics.GLGraphics;
import fr.adamaq01.suplge.opengl.utils.GLImage;

import java.util.Random;

/**
 * Created by Adamaq01 on 22/04/2017.
 */
public class Llaw implements IScreen<GLWindow, GLGraphics> {

    private IImage lellaw;

    @Override
    public void onEnable(Game<GLWindow> game) {
        this.lellaw = new GLImage(SuplgeEngine.getResourceWithoutBaseURL("https://lellaw.adamaq01.fr/"));
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
        glGraphics.drawImage(this.lellaw, 0, 0, game.getWindow().getWidth(), game.getWindow().getHeight(), false);
    }
}
