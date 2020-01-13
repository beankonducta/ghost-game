package com.patrick.ghosts.logic;

import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.patrick.ghosts.entities.Lamp;
import com.patrick.ghosts.util.State;

public class LightLogic {

    private World world;
    private RayHandler rayHandler;
    private Array<Light> playerLights;

    private Color standard;
    private Color flickerLow;
    private Color flickerHigh;
    private Color flickerMid;

    private int flickerTick;
    private int flickerReset;

    public LightLogic() {
        flickerReset = 12;
        flickerTick = 0;

        standard = new Color(1,1,1,1);
        flickerLow = new Color(1,1,1,.78f);
        flickerMid = new Color(1,1,1,.87f);
        flickerHigh = new Color(1,1,1,.94f);

        playerLights = new Array<Light>();
    }

    private Color getRandomFlicker() {
        Color[] colors = { standard, standard, standard, standard, standard, flickerLow, flickerHigh, flickerHigh, flickerHigh, flickerMid, flickerMid, flickerMid };
        return colors[MathUtils.random(colors.length-1)];
    }

    public void renderLights(OrthographicCamera camera, Lamp lamp) {
        world = new World(new Vector2(0,0),false);
        rayHandler = new RayHandler(world);
        rayHandler.setCombinedMatrix(camera.combined);
        RayHandler.useDiffuseLight(true);
        for(int i = 0; i < 5; i++)
        playerLights.add(new PointLight(rayHandler,1000, Color.WHITE,lamp.getRadius(), lamp.getX(), lamp.getY()));
    }

    public void updateAndRender(Lamp lamp) {
        flickerTick ++;
        if(flickerTick == flickerReset) {
            flickerTick = 0;
            if(lamp.isFlicker()) {
                for(Light light : playerLights)
                    if(!State.PAUSED()) light.setColor(getRandomFlicker());
            }
        }
        rayHandler.updateAndRender();
        for(Light light : playerLights)
        light.setDistance(lamp.getRadius());
    }
}
