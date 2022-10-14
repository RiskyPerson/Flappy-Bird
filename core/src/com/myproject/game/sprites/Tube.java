package com.myproject.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {
    public static final int TUBE_WIDTH = 52;
    private static final int FLUCTUATION = 130;
    private static final int TUBE_GAP = 150;
    private static final int LOWEST_OPENING = 120;
    private Random random;
    private Texture topTube, bottomTube;
    private Vector2 posTopTube, posBottomTube;
    private Rectangle topBound, bottomBound;
    public Tube(float x){
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        random = new Random();
        posTopTube = new Vector2(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        // draw bottom tube below screen because it big
        posBottomTube = new Vector2(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());
        topBound = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
        bottomBound = new Rectangle(posBottomTube.x, posBottomTube.y, bottomTube.getWidth(), bottomTube.getHeight());
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Vector2 getPosBottomTube() {
        return posBottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }
    public void reposition(float x){
        posTopTube.set(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBottomTube.set(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());
        topBound.setPosition(posTopTube.x, posTopTube.y);
        bottomBound.setPosition(posBottomTube.x, posBottomTube.y);
    }
    public boolean collides(Rectangle player){
        return topBound.overlaps(player) || bottomBound.overlaps(player);
    }
    public void dispose(){
        topTube.dispose();
        bottomTube.dispose();
    }
}
