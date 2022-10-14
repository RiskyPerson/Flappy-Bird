package com.myproject.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {
    private Array<TextureRegion> frames;
    private float maxFrameTime; // how much time one frame stay in view before switch to another
    private float currentFrameTime; // how much time frame is already stay in
    private int frameCount; // count total of frame already
    private int frame; // current frame we are working on
    public Animation(int frameCount, float cycleTime){
        // create texture region
        Texture texture = new Texture("birdanimation.png");
        TextureRegion region = new TextureRegion(texture);
        frames = new Array<>();
        int frameWidth = region.getRegionWidth() /frameCount;
        // cut birdanimation.png into 3 frame
        for(int i = 0; i < frameCount; i++){
            frames.add(new TextureRegion(region,i* frameWidth, 0, frameWidth, region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        this.maxFrameTime = cycleTime / frameCount;
        // current frame
        frame = 0;
    }
    public void update(float dt){
        currentFrameTime += dt;
        if(currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime = 0;
        }
        if(frame >= frameCount){
            frame = 0;
        }
    }
    // get current frame
    public TextureRegion getTextureRegion(){
        return frames.get(frame);
    }


}
