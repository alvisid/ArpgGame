package com.arpggame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Hero {
    private GameScreen gameScreen;
    private TextureRegion texture;
    private Vector2 position;
    private Vector2 tmp;
    private float speed;

    public Hero(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.texture = Assets.getInstance().getAtlas().findRegion("Knight");
        this.position = new Vector2(MathUtils.random(0, 1280), MathUtils.random(0, 720));
        this.tmp = new Vector2(0, 0);
        this.speed = 240.0f;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - 40, position.y - 40);
    }

    public void update(float dt) {
        float speedMod = 1.0f;
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            speedMod = 1.2f;
        }
        tmp.set(position);
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            tmp.x -= speed * dt * speedMod;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            tmp.x += speed * dt * speedMod;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            tmp.y -= speed * dt * speedMod;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            tmp.y += speed * dt * speedMod;
        }
        if (gameScreen.getMap().isCellPassable(tmp)) {
            position.set(tmp);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.G)) {
            gameScreen.getInfoController().setup(position.x, position.y, "Hello", Color.GOLD);
        }
    }
}
