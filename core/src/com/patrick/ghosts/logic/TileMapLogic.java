package com.patrick.ghosts.logic;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class TileMapLogic {

    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;

    private Array<Rectangle> collisionRectangles;
    private Rectangle exitRectangle;

    public TileMapLogic(TiledMap tiledMap, Array<Rectangle> collisionRectangles, Rectangle exitRectangle) {
        switchMap(tiledMap, collisionRectangles, exitRectangle);
    }

    public void switchMap(TiledMap tiledMap, Array<Rectangle> collisionRectangles, Rectangle exitRectangle) {
        this.tiledMap = tiledMap;
        this.collisionRectangles = collisionRectangles;
        this.exitRectangle = exitRectangle;
        tiledMapRenderer = new OrthoCachedTiledMapRenderer(tiledMap);
        ((OrthoCachedTiledMapRenderer) tiledMapRenderer).setBlending(true);
    }

    public void setView(OrthographicCamera camera, Vector2 movement) {
        tiledMapRenderer.setView(camera);
        for(Rectangle r : collisionRectangles) {
            r.setPosition(new Vector2(r.x, r.y).sub(movement));
        }
        exitRectangle.setPosition(new Vector2(exitRectangle.x, exitRectangle.y).sub(movement));
    }

    public void update() {
        // do something
    }

    public Vector2 getMapDimensions()  {
        return new Vector2(tiledMap.getProperties().get("width", Integer.class), tiledMap.getProperties().get("width", Integer.class));
    }

    public void render() {
        tiledMapRenderer.render();
    }

    public Array<Rectangle> getCollisionRectangles() {
        return collisionRectangles;
    }

    public void setCollisionRectangles(Array<Rectangle> collisionRectangles) {
        this.collisionRectangles = collisionRectangles;
    }

    public Rectangle getExitRectangle() {
        return exitRectangle;
    }

    public void setExitRectangle(Rectangle exitRectangle) {
        this.exitRectangle = exitRectangle;
    }
}
