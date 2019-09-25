package com.arpggame.game;

import com.arpggame.game.utils.Poolable;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class InfoText implements Poolable {
    private Color color;
    private String text;
    private boolean active;
    private Vector2 position;
    private Vector2 velocity;
    private float time;
    private float maxTime;

    public InfoText() {
        this.text = "";
        this.active = false;
        this.position = new Vector2(0.0f, 0.0f);
        this.velocity = new Vector2(10.0f, 50.0f);
        this.time = 0.0f;
        this.maxTime = 1.5f;
        this.color = Color.WHITE;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public String getText() {
        return text;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public void setup(float x, float y, String text, Color color) {
        this.position.set(x, y);
        this.active = true;
        this.text = text;
        this.time = 0.0f;
        this.color = color;
    }

    public void update(float dt) {
        position.mulAdd(velocity, dt);
        time += dt;
        if (time >= maxTime) {
            active = false;
        }
    }
}
