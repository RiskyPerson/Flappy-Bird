package com.myproject.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.myproject.game.FlappyDemo;
import com.myproject.game.sprites.Bird;
import com.myproject.game.sprites.Tube;

import java.util.ArrayList;

public class PlayState extends State{
    private static final int TUBE_SPACING = 125;// distance between two continuous tube
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -80;
    private Array<Tube> tubes;
    private Bird bird;
    private Texture background;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        tubes = new Array<>();
        bird = new Bird(50, 300);
        ground = new Texture("ground.png");
        background = new Texture("bg.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth/2 - 60 , GROUND_Y_OFFSET);
        groundPos2 = new Vector2(cam.position.x - cam.viewportWidth/2 + ground.getWidth() - 60, GROUND_Y_OFFSET);
        cam.setToOrtho(false, FlappyDemo.WIDTH/ 2, FlappyDemo.HEIGHT/2);
        for(int i = 1; i <= TUBE_COUNT; i++){
            tubes.add(new Tube(i*(TUBE_SPACING+ Tube.TUBE_WIDTH)));
        }
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);
        cam.position.x = bird.getPosition().x;
        for (Tube tube : tubes) {
            // in the case viewport pass the tube
            if (cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                // move position of passed tube to end
                tube.reposition(tube.getPosTopTube().x + (Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT);
            }
            if (tube.collides(bird.getBound())){
                gameStateManager.set(new PlayState(gameStateManager));
                break;
            }
        }
        if(bird.getPosition().y <= GROUND_Y_OFFSET + ground.getHeight()){
            gameStateManager.set(new PlayState(gameStateManager));
        }
        cam.update();
    }
    @Override
    public void render(SpriteBatch sb){
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        // camera position is in middle of screen
        sb.draw(background, cam.position.x - (cam.viewportWidth/ 2), 0);
        sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        for(Tube tube : tubes){
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.end();
    }
    @Override
    public void dispose() {
        background.dispose();
        ground.dispose();
        bird.dispose();
        for(Tube tube : tubes){
            tube.dispose();
        }
    }
    public void updateGround(){
        if(cam.position.x - (cam.viewportWidth/2) > groundPos1.x + ground.getWidth()){
            groundPos1.add(ground.getWidth()*2, 0);
        }
        if(cam.position.x - (cam.viewportWidth/2) > groundPos2.x + ground.getWidth()){
            groundPos2.add(ground.getWidth()*2, 0);
        }
    }

}
