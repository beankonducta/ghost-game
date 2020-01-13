package com.patrick.ghosts.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.patrick.ghosts.entities.Lamp;
import com.patrick.ghosts.entities.Player;

public class PlayerLogic {

    private Player player;

    public PlayerLogic() {
        player = new Player(new Vector2((Gdx.graphics.getWidth() / 2), (Gdx.graphics.getHeight() / 2)));
        player.setLamp(new Lamp(1000, 250, .22f, player.getX() + (player.getWidth() / 2), player.getY() + (player.getHeight() / 2)));
    }

    public Player getPlayer() {
        return player;
    }

    public Lamp getPlayerLamp() {
        return player.getLamp();
    }

    public Rectangle getCollisionRectangle() {
        return player.getHalfCollision();
    }

    public void update() {
        this.player.getLamp().update();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
