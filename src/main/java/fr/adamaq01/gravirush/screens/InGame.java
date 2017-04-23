package fr.adamaq01.gravirush.screens;

import fr.adamaq01.gravirush.Stage;
import fr.adamaq01.suplge.SuplgeEngine;
import fr.adamaq01.suplge.api.Game;
import fr.adamaq01.suplge.api.IImage;
import fr.adamaq01.suplge.api.IScreen;
import fr.adamaq01.suplge.api.graphics.Color;
import fr.adamaq01.suplge.api.graphics.IGraphics;
import fr.adamaq01.suplge.api.input.controllers.IController;
import fr.adamaq01.suplge.api.input.keyboards.IKeyboard;
import fr.adamaq01.suplge.input.ControllerManager;
import fr.adamaq01.suplge.input.KeyboardManager;
import fr.adamaq01.suplge.opengl.GLWindow;
import fr.adamaq01.suplge.opengl.graphics.GLGraphics;
import fr.adamaq01.suplge.opengl.graphics.shapes.Rectangle;
import fr.adamaq01.suplge.opengl.utils.GLImage;

/**
 * Created by Adamaq01 on 22/04/2017.
 */
public class InGame implements IScreen<GLWindow, GLGraphics> {

    private int x, y;
    private long lastInput;

    // Animation
    private IImage tutter;
    private int index;
    private long lastAnimationChange;

    // Stage
    private Stage stage;
    private boolean inStage;

    @Override
    public void onEnable(Game<GLWindow> game) {
        this.stage = new Stage();
        this.tutter = new GLImage(SuplgeEngine.getResource("tutter.png"));
        this.index = 0;
        this.lastAnimationChange = System.currentTimeMillis();
        this.x = 0;
        this.y = 0;
        this.stage.init(game);
        this.inStage = false;
    }

    @Override
    public void onDisable(Game<GLWindow> game) {

    }

    @Override
    public void update(Game<GLWindow> game, double delta) {
        IController controller = ControllerManager.MANAGER.get(0);
        IKeyboard keyboard = KeyboardManager.MANAGER.get(0);

        if(inStage) {


            if (controller.getJoyStickValue(IController.JoyStick.JOY_STICK_1, IController.ControllerAxe.AXE_JOY_STICK_VERTICAL) < -0.3 || controller.isButtonPressed(IController.Button.BUTTON_CROSSPAD_DOWN) || keyboard.isKeyPressed(IKeyboard.Key.KEY_S))
                y -= 1 * delta;
            if (controller.getJoyStickValue(IController.JoyStick.JOY_STICK_1, IController.ControllerAxe.AXE_JOY_STICK_VERTICAL) > 0.3 || controller.isButtonPressed(IController.Button.BUTTON_CROSSPAD_UP) || keyboard.isKeyPressed(IKeyboard.Key.KEY_Z))
                y += 1 * delta;
            if (controller.getJoyStickValue(IController.JoyStick.JOY_STICK_1, IController.ControllerAxe.AXE_JOY_STICK_HORIZONTAL) < -0.3 || controller.isButtonPressed(IController.Button.BUTTON_CROSSPAD_LEFT) || keyboard.isKeyPressed(IKeyboard.Key.KEY_Q))
                x -= 1 * delta;
            if (controller.getJoyStickValue(IController.JoyStick.JOY_STICK_1, IController.ControllerAxe.AXE_JOY_STICK_HORIZONTAL) > 0.3 || controller.isButtonPressed(IController.Button.BUTTON_CROSSPAD_RIGHT) || keyboard.isKeyPressed(IKeyboard.Key.KEY_D))
                x += 1 * delta;


            stage.update(game, x, y, delta);
            if (System.currentTimeMillis() - this.lastAnimationChange > 83) {
                this.lastAnimationChange = System.currentTimeMillis();
                if (this.index == 10)
                    this.index = 0;
                else
                    this.index++;
            }
        } else {
            if(System.currentTimeMillis() - lastInput > 1200) {
                lastInput = System.currentTimeMillis();
                if (controller.isButtonPressed(IController.Button.BUTTON_ACTION_DOWN)) {
                    this.stage = new Stage();
                    this.stage.init(game);
                }else if (controller.isButtonPressed(IController.Button.BUTTON_ACTION_RIGHT)) {
                    inStage = true;
                }
            }
        }
    }

    @Override
    public void render(Game<GLWindow> game, GLGraphics glGraphics) {
        if(inStage) {
            renderStage(game, glGraphics);
        } else {
            renderStageChooser(game, glGraphics);
        }
    }

    public void renderStage(Game game, IGraphics graphics) {
        stage.drawStage(graphics);
        graphics.drawImage(tutter.sub(0, 256 * index, 256, 256), x, y, 24, 24, false);
    }

    public void renderStageChooser(Game game, IGraphics graphics) {
        stage.drawStage(graphics);
        graphics.setColor(Color.SILVER);
        graphics.fillShape(new Rectangle(game.getWindow().getWidth(), game.getWindow().getHeight() / 40), 0, 0);
        graphics.fillShape(new Rectangle(game.getWindow().getWidth(), game.getWindow().getHeight() / 40), 0, game.getWindow().getHeight() - game.getWindow().getHeight() / 40);
        graphics.fillShape(new Rectangle(game.getWindow().getWidth() / 40, game.getWindow().getHeight()), 0, 0);
        graphics.fillShape(new Rectangle(game.getWindow().getWidth() / 40, game.getWindow().getHeight()), game.getWindow().getWidth() - game.getWindow().getWidth() / 40, 0);
    }

}
