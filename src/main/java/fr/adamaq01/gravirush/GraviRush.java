package fr.adamaq01.gravirush;

import fr.adamaq01.gravirush.screens.Credits;
import fr.adamaq01.gravirush.screens.MainMenu;
import fr.adamaq01.suplge.SuplgeEngine;
import fr.adamaq01.suplge.api.Game;
import fr.adamaq01.suplge.input.ControllerManager;
import fr.adamaq01.suplge.input.glfw.GLFWController;
import fr.adamaq01.suplge.opengl.GLWindow;

import java.net.URISyntaxException;

/**
 * Created by Adamaq01 on 22/04/2017.
 */
public class GraviRush extends Game<GLWindow> {

    public static void main(String[] args) throws URISyntaxException {
        SuplgeEngine.setResourcesURL("https://www.adamaq01.fr/files/test/");
        new GraviRush().start();
    }

    public GraviRush() {
        super(new GLWindow("GraviRush", null/*new GLImage(SuplgeEngine.getResource("test.png"))*/, 640, 480, false, 60, 128, SuplgeEngine.getResource("Prototype.ttf"), 48), new MainMenu(), new Credits());
        ControllerManager.MANAGER.add(new GLFWController());
    }
}
