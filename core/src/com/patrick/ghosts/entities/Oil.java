package com.patrick.ghosts.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Oil {

    float amount;
    private Sprite sprite;
    private float x;
    private float y;
    private boolean used;

    public Oil(float amount, float x, float y) {
        this.sprite  = new Sprite(new Texture(Gdx.files.internal("oil.png")));
        this.sprite.setX(x);
        this.sprite.setY(y);
        this.amount = amount;
        this.x = x;
        this.y = y;
        this.used = false;
    }

    public void move(Vector2 position) {
        this.x = position.x;
        this.y = position.y;
        this.sprite.setX(position.x);
        this.sprite.setY(position.y);
    }

    public boolean draw() {
        return !this.used;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

}