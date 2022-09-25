package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MainMenuScreen implements Screen {

    final Drop game;
    TextureRegion background;
    OrthographicCamera camera;

    public MainMenuScreen(final Drop gam) {
        game = gam;
        background = new TextureRegion(new Texture("menu.jpg"), 0, 0, 1920, 1080);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(background, 0, 0);

        game.font.draw(game.batch, "Hello Kitty Game", 500, 500);
        game.font.draw(game.batch, "Tap to start", 600, 500);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
    }
}
