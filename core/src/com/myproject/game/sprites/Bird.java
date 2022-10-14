package com.myproject.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    private static final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 velocity;
    private Texture bird;
    private Rectangle bound;
    private Animation birdAnimation;
    private Texture texture;
    private Sound flapSound;
    private static final int MOVEMENT = 100;

    public Bird(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0,0,0);
        bird = new Texture("bird.png");
        birdAnimation = new Animation(3, 0.5f);
        bound = new Rectangle(x, y, bird.getWidth(), bird.getHeight());
        flapSound = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public TextureRegion getBird() {
        return birdAnimation.getTextureRegion();
    }

    public Vector3 getPosition() {
        return position;
    }

    public void update(float dt){
        birdAnimation.update(dt);
        if(position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }
        // scale velocity by dt time
        velocity.scl(dt);
        position.add(MOVEMENT*dt, velocity.y, 0);
        if(position.y < 0){
            position.y = 0;
        }
        velocity.scl(1/dt);
        bound.setPosition(position.x, position.y);
    }
    public void jump(){
        velocity.y = 250;
        flapSound.play(0.25f);
    }
    public Rectangle getBound(){
        return bound;
    }
    public void dispose(){
        bird.dispose();
        flapSound.dispose();
    }


}
