package fr.adamaq01.gravirush;

import fr.adamaq01.gravirush.screens.*;
import fr.adamaq01.suplge.SuplgeEngine;
import fr.adamaq01.suplge.api.Game;
import fr.adamaq01.suplge.input.ControllerManager;
import fr.adamaq01.suplge.input.KeyboardManager;
import fr.adamaq01.suplge.input.glfw.GLFWController;
import fr.adamaq01.suplge.input.glfw.GLFWKeyboard;
import fr.adamaq01.suplge.opengl.GLWindow;
import io.socket.client.IO;
import io.socket.client.Socket;

import java.net.URISyntaxException;

/**
 * Created by Adamaq01 on 22/04/2017.
 */
public class GraviRush extends Game<GLWindow> {

    private static Socket socket;

    public static void main(String[] args) throws URISyntaxException {
        SuplgeEngine.setResourcesURL("https://www.adamaq01.fr/files/test/");
        new GraviRush().start();
    }

    public static Socket getSocket() {
        return socket;
    }

    public GraviRush() {
        super(new GLWindow("GraviRush", null/*new GLImage(SuplgeEngine.getResource("test.png"))*/, 640, 480, false, 60, 128, SuplgeEngine.getResource("Prototype.ttf"), 48), new MainMenu(), new InGame(), new Options(), new Credits(), new Llaw());
        ControllerManager.MANAGER.add(new GLFWController());
        KeyboardManager.MANAGER.add(new GLFWKeyboard(getWindow().getWindowHandle()));

        try {
            GraviRush.socket = IO.socket("https://lellaw.adamaq01.fr/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        GraviRush.socket.connect();
    }

    @Override
    public void stop() {
        super.stop();
        GraviRush.socket.disconnect();
    }
}
