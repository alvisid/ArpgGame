package com.arpggame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends AbstractScreen {
    private Map map;
    private Hero hero;
    private Monster monster;
    private InfoController infoController;
    private BitmapFont font24;

    public GameScreen(SpriteBatch batch) {
        super(batch);
    }

    public InfoController getInfoController() {
        return infoController;
    }

    public Map getMap() {
        return map;
    }

    @Override
    public void show() {
        this.map = new Map();
        this.hero = new Hero(this);
        this.monster = new Monster(this);
        this.font24 = Assets.getInstance().getAssetManager().get("fonts/font24.ttf");
        this.infoController = new InfoController();
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        ScreenManager.getInstance().getCamera().position.set(hero.getPosition(), 0.0f);
        ScreenManager.getInstance().getCamera().update();
        batch.setProjectionMatrix(ScreenManager.getInstance().getCamera().combined);

        batch.begin();
        map.render(batch);
        hero.render(batch);
        monster.render(batch);
        infoController.render(batch, font24);
        batch.end();
    }

    public void update(float dt) {
        hero.update(dt);
        monster.update(dt);
        infoController.update(dt);
    }
}
