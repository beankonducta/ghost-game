package com.patrick.ghosts.logic;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.patrick.ghosts.entities.Oil;

public class ItemSpawnLogic {

    private Array<Oil> oils;

    public ItemSpawnLogic() {

    }

    public void move(Vector2 amount) {
        for(Oil oil : oils) {
            oil.move(new Vector2(oil.getX() - amount.x, oil.getY() - amount.y));
        }
    }

    public void spawnOil(int amount, float maxVal, float minVal, TileMapLogic mapLogic) {
        int count = 0;
        Array<Oil> rtrn = new Array();
        while(count < amount) {
            boolean skip = false;
            float value = MathUtils.random(minVal, maxVal);
            float randomX = MathUtils.random(0, mapLogic.getMapDimensions().x * 32);
            float randomY = MathUtils.random(0, mapLogic.getMapDimensions().y * 32);
            Oil oil = new Oil(value, randomX, randomY);
            for(Rectangle object : mapLogic.getCollisionRectangles()) {
                if(object.overlaps(oil.getSprite().getBoundingRectangle())) {
                    skip = true;
                }
            }
            if(!skip) {
                rtrn.add(oil);
                count ++;
            }
        }
        this.oils = rtrn;
    }

    public Array<Oil> getOils() {
        return oils;
    }

    public void setOils(Array<Oil> oils) {
        this.oils = oils;
    }
}
