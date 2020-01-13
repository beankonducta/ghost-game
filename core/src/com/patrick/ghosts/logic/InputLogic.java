package com.patrick.ghosts.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

public class InputLogic implements InputProcessor {

    public int currentDirection;

    public static boolean MOVING = false;

    public InputLogic() {
    }

    public Vector2 move(float speed) {
        Vector2 temp = new Vector2(0,0);
        //if(colliding) return temp;
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            temp.add(0, speed);
            currentDirection = 0;
            MOVING = true;
            return temp;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            temp.add(0, - speed);
            currentDirection = 1;
            MOVING = true;
            return temp;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            temp.add(- speed, 0);
            currentDirection = 2;
            MOVING = true;
            return temp;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            temp.add(speed, 0);
            currentDirection = 3;
            MOVING = true;
            return temp;
        }
        MOVING = false;
        return temp;
    }

    public boolean skipBanner() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
