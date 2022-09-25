package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;


public class Drop extends Game {

    SpriteBatch batch;
    BitmapFont font;

    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("ComicMono.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=45;
        font = generator.generateFont(parameter);
        font.setColor(Color.PURPLE);
        this.setScreen(new MainMenuScreen(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}