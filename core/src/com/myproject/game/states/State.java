package com.myproject.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gameStateManager;
    public State(){

    }
    protected State(GameStateManager gameStateManager){
        this.gameStateManager = gameStateManager;
        mouse = new Vector3();
        cam = new OrthographicCamera();
    }
    public abstract void handleInput();
    public abstract void update(float dt);// dt is delta time- the time different between 2 continuous frame render
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}
