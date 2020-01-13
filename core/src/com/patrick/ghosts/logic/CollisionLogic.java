package com.patrick.ghosts.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.patrick.ghosts.entities.Oil;
import com.patrick.ghosts.entities.Player;
import com.patrick.ghosts.util.Settings;

public class CollisionLogic {

    public CollisionLogic() {
    }

    public Array<Rectangle> buildShapes(Map map) {
        MapObjects objects = map.getLayers().get("Collisions").getObjects();
        Array<Rectangle> shapes = new Array<>();
        for (RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)) {
            shapes.add(rectangleObject.getRectangle());
        }
        return shapes;
    }

    public Rectangle buildExit(Map map) {
        MapObjects objects = map.getLayers().get("Exit").getObjects();
        if(objects != null) return objects.getByType(RectangleMapObject.class).get(0).getRectangle();
        return null;
    }

    public Vector2 getStartPoint(Map map) {
        MapObjects objects = map.getLayers().get("Start").getObjects();
        if(objects != null) {
            Rectangle r = objects.getByType(RectangleMapObject.class).get(0).getRectangle();
            return new Vector2(r.x - (Gdx.graphics.getWidth()/2), r.y - (Gdx.graphics.getHeight()/2));
        }
        return new Vector2(0, 0);
    }

    public Vector2 checkSimpleCollision(Array<Rectangle> objects, Rectangle player, Vector2 position) {
        if(!Settings.COLLISION) return position;
        Rectangle newPlayer = new Rectangle(player.getX() + position.x, player.getY() + position.y, player.getWidth(), player.getHeight());
        for(Rectangle object : objects) {
            if(newPlayer.overlaps(object)) {
                float x = 0;
                if(position.x > 0) x = object.getX() - (player.getX() + player.getWidth());
                else if(position.x < 0) x = (object.getX() + object.getWidth()) - player.getX();
                float y = 0;
                if(position.y > 0) y = object.getY() - (player.getY() + player.getHeight());
                else if(position.y < 0) y = (object.getY() + object.getHeight()) - player.getY();
                return new Vector2(x, y);
            }
        }
        return position;
    }

    public void oilPlayerCollision(Array<Oil> oils, PlayerLogic playerLogic) {
        for(Oil oil : oils) {
            if(oil.getSprite().getBoundingRectangle().overlaps(playerLogic.getCollisionRectangle())) {
                oil.setUsed(true);
                playerLogic.getPlayerLamp().addOil(oil.getAmount());
            }
        }
    }

    // this could be a little smarter but works for now
    public boolean checkExitCollision(Rectangle exit, Rectangle player) {
        return exit.overlaps(player);
    }
}