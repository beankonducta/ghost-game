package com.patrick.ghosts.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.patrick.ghosts.entities.Banner;
import com.patrick.ghosts.util.State;

public class BannerLogic {

    private Banner banner;

    public BannerLogic() {
        banner = null;
    }

    public void newBanner(Banner banner) {
        this.banner = banner;
        System.out.println(this.banner.getText());
    }

    public void draw(SpriteBatch spriteBatch) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        BitmapFont font = generator.generateFont(parameter);
        font.setColor(Color.WHITE);
        if(banner != null) {
            spriteBatch.draw(banner.getSprite(), getX(), getY());
            font.draw(spriteBatch, banner.getText(), getX() + 28, getY() +112, 200, 1, true);
        }
    }

    public float getX() {
        return banner.getSprite().getX();
    }

    public float getY() {
        return banner.getSprite().getY();
    }

    public void update(boolean skip) {
        if(skip && banner != null) banner.setDuration(0f);
       if(banner != null) {
           banner.update();
           State.BANNER = true;
       }
        if(banner != null && banner.getDuration() < 1) {
            banner = null;
            State.BANNER = false;
        }
    }
}
