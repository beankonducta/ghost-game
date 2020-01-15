package com.patrick.ghosts;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.patrick.ghosts.entities.Banner;
import com.patrick.ghosts.entities.Oil;
import com.patrick.ghosts.logic.*;
import com.patrick.ghosts.util.Banners;
import com.patrick.ghosts.util.Maps;
import com.patrick.ghosts.util.Settings;
import com.patrick.ghosts.util.State;

public class Main extends ApplicationAdapter {
    TiledMap map;
    TileMapLogic mapLogic;
    CameraLogic cameraLogic;
    CollisionLogic collisionLogic;
    LightLogic lightLogic;
    InputLogic inputLogic;
    PlayerLogic playerLogic;
    ItemSpawnLogic itemSpawnLogic;
    BannerLogic bannerLogic;

    SpriteBatch spriteBatch;
    ShapeRenderer shapeRenderer;

    boolean needLoad = true;

    float stateTime;

    @Override
    public void create() {
        stateTime = 0f;
        cameraLogic = new CameraLogic();
        collisionLogic = new CollisionLogic();
        cameraLogic.update();
        inputLogic = new InputLogic();
        playerLogic = new PlayerLogic();
        lightLogic = new LightLogic();
        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(cameraLogic.getCamera().combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        itemSpawnLogic = new ItemSpawnLogic();
        bannerLogic = new BannerLogic();
        changeMap();
        //cameraLogic.moveToAdjustPlayerPosition(new Vector2(cameraLogic.getCamera().position.x + 3, cameraLogic.getCamera().position.y -3));
        Gdx.input.setInputProcessor(inputLogic);
    }

    private void changeMap() {
        if (needLoad) {
            cameraLogic.resetCamera();
            map = new TmxMapLoader().load(Maps.GET_MAP());
            mapLogic = new TileMapLogic(map, collisionLogic.buildShapes(map), collisionLogic.buildExit(map));
            itemSpawnLogic.spawnOil(8 * Maps.CURRENT_MAP, 80, 500, mapLogic);
            lightLogic.renderLights(cameraLogic.getCamera(), playerLogic.getPlayerLamp(), itemSpawnLogic);
            cameraLogic.move(collisionLogic.getStartPoint(map));
            mapLogic.setView(cameraLogic.getCamera(), collisionLogic.getStartPoint(map));
            cameraLogic.update();
            bannerLogic.newBanner(Banners.BANNERS[Maps.CURRENT_MAP]);
            needLoad = false;
        }
    }

    private void move(Vector2 position) {
        Vector2 collisionOffset = collisionLogic.checkSimpleCollision(mapLogic.getCollisionRectangles(), playerLogic.getCollisionRectangle(), position);
        cameraLogic.getCamera().translate(collisionOffset.x, collisionOffset.y);
        itemSpawnLogic.move(collisionOffset);
        mapLogic.setView(cameraLogic.getCamera(), collisionOffset);
    }

    private void checkItemCollisions() {
        collisionLogic.oilPlayerCollision(itemSpawnLogic.getOils(), playerLogic);
    }

    @Override
    public void render() {
        if (InputLogic.MOVING) stateTime += Gdx.graphics.getDeltaTime() * Settings.FRAME_SPEED;
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mapLogic.render();
        spriteBatch.begin();
        spriteBatch.draw(playerLogic.getPlayer().getCurrentAnimation(inputLogic.currentDirection).getKeyFrame(stateTime, true), playerLogic.getPlayer().getX(), playerLogic.getPlayer().getY());
        for (Oil oil : itemSpawnLogic.getOils())
            if (oil.draw()) spriteBatch.draw(oil.getSprite(), oil.getX(), oil.getY());
        bannerLogic.draw(spriteBatch);
        spriteBatch.end();
        if (!State.PAUSED()) {
            move(inputLogic.move(.015f * Settings.PLAYER_SPEED));
            cameraLogic.update();
            playerLogic.update();
        }
        bannerLogic.update(inputLogic.skipBanner());
        checkItemCollisions();
        lightLogic.updateAndRender(playerLogic.getPlayerLamp(), itemSpawnLogic);
        if (collisionLogic.checkExitCollision(mapLogic.getExitRectangle(), playerLogic.getCollisionRectangle())) {
            needLoad = true;
            changeMap();
        }
        if (Settings.RENDER_BORDERS) {
            for (Rectangle r : collisionLogic.buildShapes(map)) {
                shapeRenderer.begin();
                shapeRenderer.setColor(Color.WHITE);
                shapeRenderer.rect(r.x, r.y, r.width, r.height);
                shapeRenderer.end();
            }
            Rectangle r = playerLogic.getPlayer().getHalfCollision();
            shapeRenderer.begin();
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.rect(r.x, r.y, r.width, r.height);
            shapeRenderer.end();
        }
    }

    @Override
    public void dispose() {
    }
}
