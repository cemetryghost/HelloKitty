package com.mygdx.game;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen implements Screen {
    final Drop game;

    Texture kuromi;
    Texture myMelody;
    Texture helloKitty;
    Texture blackHeart;
    Texture pinkHeart;
    Texture redHeart;
    Texture catImage;

    TextureRegion background;

    Sound dropSound;
    Music gameMusic;
    OrthographicCamera camera;
    Rectangle cats;
    Array<Heart> heartdrops;
    long lastDropTime;
    int dropsGathered;

    int velocity = 200;

    public GameScreen(final Drop game) {
        this.game = game;
        background = new TextureRegion(new Texture("background.jpg"), 0, 0, 1920, 1080);

        redHeart = new Texture(Gdx.files.internal("red_heart.png"));
        blackHeart = new Texture(Gdx.files.internal("black_Heart.png"));
        pinkHeart = new Texture(Gdx.files.internal("pink_heart.png"));

        helloKitty = new Texture(Gdx.files.internal("hello_kitty.png"));
        kuromi = new Texture(Gdx.files.internal("kuromi.png"));
        myMelody = new Texture(Gdx.files.internal("mymelody.png"));

        dropSound = Gdx.audio.newSound(Gdx.files.internal("drops.wav"));
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("soundtrack.mp3"));
        gameMusic.setLooping(true);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);

        cats = new Rectangle();
        cats.x = 1920 / 2 - 64 * 2 / 2;
        cats.y = 20;

        cats.width = 64 * 2;
        cats.height = 64 * 2;

        heartdrops = new Array<Heart>();
        spawnHeartdrop();
    }

    private void spawnHeartdrop() {
        Texture hearts;
        Rectangle heartdrop = new Rectangle();
        heartdrop.x = MathUtils.random(0, 1920 - 64);
        heartdrop.y = 1080;
        heartdrop.width = 64;
        heartdrop.height = 64;

        if(dropsGathered < 15){
            hearts = redHeart;
            catImage = helloKitty;
        }
        else if (dropsGathered >= 15 && dropsGathered < 30){
            hearts = blackHeart;
            catImage = kuromi;
            velocity = 400;
        }
        else{
            hearts = pinkHeart;
            catImage = myMelody;
            velocity = 600;
        }

        heartdrops.add(new Heart(heartdrop, hearts));
        lastDropTime = TimeUtils.nanoTime();
    }

    @Override
    public void render(float delta) {

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(background, 0, 0);
        game.font.draw(game.batch, "Hearts Collected: " + dropsGathered, 30,1050);
        game.batch.draw(catImage, cats.x, cats.y);
        for (Heart heart : heartdrops) {
            game.batch.draw(heart.texture, heart.rectangleHeart.x, heart.rectangleHeart.y);
        }
        game.batch.end();

        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            cats.x = touchPos.x - 64;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.BUTTON_Z))
            cats.x -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.BUTTON_X))
            cats.x += 200 * Gdx.graphics.getDeltaTime();

        if (cats.x < 0)
            cats.x = 0;
        if (cats.x > 1920 - 64 * 2)
            cats.x = 1920 - 64 * 2;

        if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
            spawnHeartdrop();


        Iterator<Heart> iter = heartdrops.iterator();
        while (iter.hasNext()) {
            Heart heartdrop = iter.next();
            heartdrop.rectangleHeart.y -= velocity * Gdx.graphics.getDeltaTime();
            if (heartdrop.rectangleHeart.y + 64 < 0)
                iter.remove();
            if (heartdrop.rectangleHeart.overlaps(cats)) {
                dropsGathered++;
                dropSound.play();
                iter.remove();
            }
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        gameMusic.play();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        kuromi.dispose();
        myMelody.dispose();
        helloKitty.dispose();
        blackHeart.dispose();
        pinkHeart.dispose();
        redHeart.dispose();
        catImage.dispose();
        dropSound.dispose();
        gameMusic.dispose();
    }

}
