package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Heart {
    Rectangle rectangleHeart;
    Texture texture;
    public Heart(Rectangle rectangleHeart, Texture texture){
        this.rectangleHeart = rectangleHeart;
        this.texture = texture;
    }
}
