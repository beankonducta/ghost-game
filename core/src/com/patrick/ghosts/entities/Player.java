package com.patrick.ghosts.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Player extends Actor {

    private Texture texture;
    private Rectangle halfCollision;
    private TextureRegion[][] textureRegions;
    private Animation<TextureRegion> upAnimation;
    private Animation<TextureRegion> downAnimation;
    private Animation<TextureRegion> leftAnimation;
    private Animation<TextureRegion> rightAnimation;
    private float x;
    private float y;
    private Lamp lamp;

    public Player(Vector2 position) {
        texture = new Texture(Gdx.files.internal("player_sheet.png"));
        textureRegions = Sprite.split(texture, 32, 32);
        upAnimation = new Animation(0.06f, textureRegions[2]);
        downAnimation = new Animation(0.06f, textureRegions[0]);
        leftAnimation = new Animation(0.06f, textureRegions[1]);
        rightAnimation = new Animation(0.06f, textureRegions[3]);
        halfCollision = new Rectangle(position.x+7, position.y, 18, 8);
        this.move(position);
    }

    // not used
    public void move(Vector2 position) {
        this.setX(position.x);
        this.setY(position.y);
        this.setBounds(position.x+4, position.y, 24, 32);
        this.halfCollision.setX(position.x+7);
        this.halfCollision.setY(position.y);
    }

    public Animation<TextureRegion> getCurrentAnimation(int direction) {
        if(direction == 0) return upAnimation;
        if(direction == 1) return downAnimation;
        if(direction == 2) return leftAnimation;
        if(direction == 3) return rightAnimation;
        return upAnimation;
    }

    public Lamp getLamp() {
        return lamp;
    }

    public void setLamp(Lamp lamp) {
        this.lamp = lamp;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public Rectangle getHalfCollision() {
        return halfCollision;
    }

    public void setHalfCollision(Rectangle halfCollision) {
        this.halfCollision = halfCollision;
    }

    public Animation<TextureRegion> getUpAnimation() {
        return upAnimation;
    }

    public void setUpAnimation(Animation<TextureRegion> upAnimation) {
        this.upAnimation = upAnimation;
    }

    public Animation<TextureRegion> getDownAnimation() {
        return downAnimation;
    }

    public void setDownAnimation(Animation<TextureRegion> downAnimation) {
        this.downAnimation = downAnimation;
    }

    public Animation<TextureRegion> getLeftAnimation() {
        return leftAnimation;
    }

    public void setLeftAnimation(Animation<TextureRegion> leftAnimation) {
        this.leftAnimation = leftAnimation;
    }

    public Animation<TextureRegion> getRightAnimation() {
        return rightAnimation;
    }

    public void setRightAnimation(Animation<TextureRegion> rightAnimation) {
        this.rightAnimation = rightAnimation;
    }
}
