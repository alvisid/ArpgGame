package com.arpg.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class MonsterController {

/*            protected InfoText newObject() {
        return new InfoText();
    }

    public void setup(float x, float y, String text, Color color) {
        InfoText infoText = getActiveElement();
        infoText.setup(x, y, text, color);
    }

    public void render(SpriteBatch batch, BitmapFont font) {
        for (int i = 0; i < activeList.size(); i++) {
            InfoText infoText = activeList.get(i);
            font.setColor(infoText.getColor());
            font.draw(batch, infoText.getText(), infoText.getPosition().x, infoText.getPosition().y);
        }
        font.setColor(Color.WHITE);
    }

    public void update(float dt) {
        for (int i = 0; i < activeList.size(); i++) {
            InfoText infoText = activeList.get(i);
            infoText.update(dt);
        }
        checkPool();
    }*/


    private Monster[] monsters;

    public Monster[] getBots() {
        return monsters;
    }

    public static final int MAX_MONSTER_COUNT = 200;

    public MonsterController(GameScreen gameScreen, TextureAtlas atlas) {
        this.monsters = new Monster[MAX_MONSTER_COUNT];
        for (int i = 0; i < monsters.length; i++) {
            this.monsters[i] = new Monster(gameScreen, atlas);
        }
    }

//    public void activate(float x, float y) {
//        for (int i = 0; i < monsters.length; i++) {
//            if (!monsters[i].isActive()) {
//                monsters[i].activate(x, y);
//                break;
//            }
//        }
//    }

    public MonsterController() {
        this.monsters = new Monster[MAX_MONSTER_COUNT];
        for (int i = 0; i < monsters.length; i++) {
            this.monsters[i] = new Monster();
        }
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < monsters.length; i++) {
            if (monsters[i].isActive()) {
                monsters[i].render(batch);
            }
        }
    }

    public void update(float dt) {
        for (int i = 0; i < monsters.length; i++) {
            if (monsters[i].isActive()) {
                monsters[i].update(dt);
            }
        }
    }


    public boolean activate(float x, float y) {
        for (int i = 0; i < monsters.length; i++) {
            if (!monsters[i].isActive()) {
                monsters[i].activate(x, y);
                break;
            }
        }
        return false;
    }
}
