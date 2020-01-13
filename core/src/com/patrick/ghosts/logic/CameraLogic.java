package com.patrick.ghosts.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.patrick.ghosts.util.Settings;

public class CameraLogic {

    private OrthographicCamera camera;

    public CameraLogic() {
        resetCamera();
    }

    public void move(Vector2 destination) {
        camera.translate(destination);
    }

    public void resetCamera() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.zoom += Settings.CAMERA_ZOOM;
        camera.update();
    }


    public OrthographicCamera getCamera() {
        return camera;
    }

    public void update() {
        camera.update();
    }
}
