package com.myproject.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.myproject.game.FlappyDemo;

public class MenuState extends State{
    private final Texture background;
    private final Texture playButton;
    public MenuState(GameStateManager gameStateManager){
        super(gameStateManager);
        background = new Texture("bg.png");
        playButton = new Texture("playbtn.png");
    }
    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gameStateManager.set(new PlayState(gameStateManager));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        // size and position of img you want draw
        sb.draw(background, 0, 0, FlappyDemo.WIDTH, FlappyDemo.HEIGHT);
        sb.draw(playButton, (FlappyDemo.WIDTH /2)- (playButton.getWidth() /2 ), FlappyDemo.HEIGHT /2);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
    }
}
