package fr.adamaq01.gravirush.screens;

import fr.adamaq01.gravirush.Screens;
import fr.adamaq01.suplge.SuplgeEngine;
import fr.adamaq01.suplge.api.Game;
import fr.adamaq01.suplge.api.IImage;
import fr.adamaq01.suplge.api.IScreen;
import fr.adamaq01.suplge.api.graphics.Color;
import fr.adamaq01.suplge.api.input.controllers.IController;
import fr.adamaq01.suplge.input.ControllerManager;
import fr.adamaq01.suplge.input.glfw.GLFWController;
import fr.adamaq01.suplge.opengl.GLWindow;
import fr.adamaq01.suplge.opengl.graphics.GLGraphics;
import fr.adamaq01.suplge.opengl.graphics.shapes.Circle;
import fr.adamaq01.suplge.opengl.graphics.shapes.Triangle;
import fr.adamaq01.suplge.opengl.utils.GLImage;

import java.nio.file.Paths;

/**
 * Created by Adamaq01 on 22/04/2017.
 */
public class MainMenu implements IScreen<GLWindow, GLGraphics> {

    private int selection;
    private IImage background;

    @Override
    public void onEnable(Game<GLWindow> game) {
        this.selection = 0;
        this.background = new GLImage(SuplgeEngine.getResource("background.jpg"));
    }

    @Override
    public void onDisable(Game<GLWindow> game) {

    }

    private long lastChange;

    @Override
    public void update(Game<GLWindow> game, double delta) {
        IController controller = ControllerManager.MANAGER.get(0);
        if(!game.isPaused()) {
            handleSelection(game, controller);
        }
    }

    @Override
    public void render(Game<GLWindow> game, GLGraphics glGraphics) {
        if(!game.isPaused()) {
            if(!glGraphics.isOrtho()) {
                glGraphics.setOrtho(true);
            }
            glGraphics.setRotation(0);
            glGraphics.drawImage(background, 0, 0, game.getWindow().getWidth(), game.getWindow().getHeight(), false);
            glGraphics.setColor(Color.WHITE);
            glGraphics.drawString("Commencer", 100, 100, 1);
            glGraphics.drawString("Options", 100, 200, 1);
            glGraphics.drawString("Credits", 100, 300, 1);
            glGraphics.fillShape(new Circle(20), (100 / 2) - (30 / 2), this.selection * (100 / 2) + (100 / 2) - (20 / 2));
        }
    }

    private void handleSelection(Game game, IController controller) {
        if(controller.isButtonPressed(IController.Button.BUTTON_ACTION_RIGHT)) {
            switch(this.selection) {
                case 0:
                    game.setCurrentScreen(Screens.GAME);
                    break;
                case 1:
                    game.setCurrentScreen(Screens.OPTIONS);
                    break;
                case 2:
                    game.setCurrentScreen(Screens.CREDITS);
                    break;
            }
        }

        if(System.currentTimeMillis() - lastChange > 125) {
            lastChange = System.currentTimeMillis();
            if (controller.getJoyStickValue(IController.JoyStick.JOY_STICK_1, IController.ControllerAxe.AXE_JOY_STICK_VERTICAL) < -0.1 || controller.isButtonPressed(IController.Button.BUTTON_CROSSPAD_DOWN))
                if (this.selection == 2)
                    this.selection = 0;
                else
                    this.selection++;
            else if (controller.getJoyStickValue(IController.JoyStick.JOY_STICK_1, IController.ControllerAxe.AXE_JOY_STICK_VERTICAL) > 0.1 || controller.isButtonPressed(IController.Button.BUTTON_CROSSPAD_UP))
                if (this.selection == 0)
                    this.selection = 2;
                else
                    this.selection--;
        }
    }
}
