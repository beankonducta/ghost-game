package com.patrick.ghosts.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.patrick.ghosts.util.Settings;

public class Banner {

    private String text;
    private float duration;
    private Sprite sprite;

    public Banner(String text, float duration) {
        this.text = text;
        this.duration = duration;
        this.sprite = new Sprite(new Texture(Gdx.files.internal("banner.png")));
        this.sprite.setX((Gdx.graphics.getWidth() - this.sprite.getWidth())/2);
        this.sprite.setY((Gdx.graphics.getHeight() - this.sprite.getHeight())/2);
    }

    public void update() {
        if(this.duration > 0) this.duration -= Settings.BANNER_FADE;
    }

    public boolean isVisible() {
        return this.duration > 0;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}