package com.arpggame.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Monster {
    private GameScreen gameScreen;
    private TextureRegion texture;
    private Vector2 position;
    private Vector2 direction;
    private Vector2 tmp;
    private float speed;
    private float aiTimer;
    private float aiTimerTo;

    public Monster(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.texture = Assets.getInstance().getAtlas().findRegion("Skeleton");
        this.position = new Vector2(MathUtils.random(0, 1280), MathUtils.random(0, 720));
        this.tmp = new Vector2(0, 0);
        this.direction = new Vector2(0, 0);
        this.speed = 120.0f;
        this.aiTimerTo = 0.0f;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - 40, position.y - 40);
    }

    public void update(float dt) {
        aiTimer += dt;
        if (aiTimer > aiTimerTo) {
            aiTimer = 0.0f;
            aiTimerTo = MathUtils.random(2.0f, 4.0f);
            direction.set(MathUtils.random(-1.0f, 1.0f), MathUtils.random(-1.0f, 1.0f)).nor();
        }
        tmp.set(position);
        tmp.mulAdd(direction, speed * dt);
        if (gameScreen.getMap().isCellPassable(tmp)) {
            position.set(tmp);
        }
    }
}
