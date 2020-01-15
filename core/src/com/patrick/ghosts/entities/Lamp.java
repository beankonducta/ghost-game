package com.patrick.ghosts.entities;

import com.badlogic.gdx.math.Vector2;

public class Lamp {

    private float oil;
    private boolean useOil;
    private boolean moveWithWorld;
    private boolean flicker;
    private boolean active;
    private float usageSpeed;
    private float maxRadius;
    private float radius;
    private float maxOil;
    private float x;
    private float y;

    public Lamp(float oil, float maxRadius, float usageSpeed, float x, float y) {
        this.active = true;
        this.oil = oil;
        this.maxRadius = maxRadius;
        this.radius = maxRadius;
        this.usageSpeed = usageSpeed;
        this.maxOil = oil;
        this.useOil = oil != -1;
        this.flicker = oil != -1;
        this.x = x;
        this.y = y;
    }

    public void update() {
        if(this.useOil) {
            this.radius = (this.oil / this.maxOil) * this.maxRadius;
            if(this.oil > 100) this.oil -= this.usageSpeed;
        }
    }

    public void move(Vector2 position) {
        this.x = position.x;
        this.y = position.y;
    }

    public boolean isFlicker() {
        return flicker;
    }

    public void setFlicker(boolean flicker) {
        this.flicker = flicker;
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

    public float getMaxOil() {
        return maxOil;
    }

    public void setMaxOil(float maxOil) {
        this.maxOil = maxOil;
    }

    public boolean isMoveWithWorld() {
        return moveWithWorld;
    }

    public void setMoveWithWorld(boolean moveWithWorld) {
        this.moveWithWorld = moveWithWorld;
    }

    public float getUsageSpeed() {
        return usageSpeed;
    }

    public void setUsageSpeed(float usageSpeed) {
        this.usageSpeed = usageSpeed;
    }

    public float getOil() {
        return oil;
    }

    public void setOil(float oil) {
        this.oil = oil;
    }

    public void addOil(float oil) {
        this.oil += oil;
        if(this.oil > this.maxOil) this.oil = this.maxOil;
    }

    public boolean isUseOil() {
        return useOil;
    }

    public void setUseOil(boolean useOil) {
        this.useOil = useOil;
    }

    public boolean isActive() { return active; };

    public void setActive(boolean active) { this.active = active; };

    public float getMaxRadius() {
        return maxRadius;
    }

    public void setMaxRadius(float maxRadius) {
        this.maxRadius = maxRadius;
    }

    public float getRadius() {
        return radius;
    }
}
