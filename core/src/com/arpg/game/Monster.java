package com.arpg.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class Monster extends Unit {
    private TextureRegion hpTexture;
    private float aiTimer;
    private float aiTimerTo;
    Direction prefferedDirection;
    float speed;
    float angle;

    GameScreen gameScreen;


    int width;
    int height;

    public Monster() {
        super();
    }


    public boolean isActive() {
        return hp > 0;
    }

    public Monster(GameScreen gameScreen, TextureAtlas atlas) {
        super(gameScreen);
        this.hpTexture = Assets.getInstance().getAtlas().findRegion("monsterHp");
        this.texture = Assets.getInstance().getAtlas().findRegion("Skeleton");
        do {
            this.position.set(MathUtils.random(0, Map.MAP_SIZE_X_PX), MathUtils.random(0, Map.MAP_SIZE_Y_PX));
        } while (!gameScreen.getMap().isCellPassable(position));
        this.area.setPosition(position);
        this.speed = 120.0f;
        this.aiTimerTo = 0.0f;
        this.hpMax = 10;
        this.hp = hpMax;
        this.weapon = new Weapon("Short Dark Sword", 0.8f, 2, 5);
        this.prefferedDirection = Direction.UP;

    }

    public void activate(float x, float y) {
        hpMax = 3;
        hp = hpMax;
        prefferedDirection = Direction.values()[MathUtils.random(0, Direction.values().length - 1)];
        angle = prefferedDirection.getAngle();
        position.set(x, y);
        aiTimer = 0.0f;
//        active = true;
    }


    @Override
    public void render(SpriteBatch batch) {
        if (damageTimer > 0.0f) {
            batch.setColor(1.0f, 1.0f - damageTimer, 1.0f - damageTimer, 1.0f);
        }
        batch.draw(texture, position.x - 40, position.y - 40);
        if (hp < hpMax) {
            batch.setColor(1.0f, 1.0f, 1.0f, 0.9f);
            batch.draw(hpTexture, position.x - 40, position.y + 40, 80 * ((float) hp / hpMax), 12);
        }
        batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public void update(float dt) {
        aiTimer += dt;
        attackTime += dt;

        if (damageTimer > 0.0f) {
            damageTimer -= dt;
        }

        if (aiTimer > aiTimerTo) {
            aiTimer = 0.0f;
            aiTimerTo = MathUtils.random(2.0f, 4.0f);
            direction = Direction.values()[MathUtils.random(0, 3)];
        }

        tmp.set(position).add(direction.getX() * speed * dt, direction.getY() * speed * dt);
        if (gs.getMap().isCellPassable(tmp)) {
            position.set(tmp);
            area.setPosition(position);
        }

        tryToAttack();
    }


    /*public void update(float dt) {
        aiTimer += dt;
        if (aiTimer >= aiTimerTo) {
            aiTimer = 0.0f;
            aiTimerTo = MathUtils.random(3.5f, 6.0f);
            prefferedDirection = Direction.values()[MathUtils.random(0, Direction.values().length - 1)];
            angle = prefferedDirection.getAngle();
        }
        move(prefferedDirection, dt);

        Hero preferredTarget = null;
        if (gameScreen.getPlayers().size() == 1) {
            preferredTarget = gameScreen.getPlayers().get(0);
        } else {
            float minDist = Float.MAX_VALUE;
            for (int i = 0; i < gameScreen.getPlayers().size(); i++) {
                PlayerTank player = gameScreen.getPlayers().get(i);
                float dst = this.position.dst(player.getPosition());
                if (dst < minDist) {
                    minDist = dst;
                    preferredTarget = player;
                }
            }
        }

        float dst = this.position.dst(preferredTarget.getPosition());
        if (dst < pursuitRadius) {
            rotateTurretToPoint(preferredTarget.getPosition().x, preferredTarget.getPosition().y, dt);
            fire();
        }

        if (Math.abs(position.x - lastPosition.x) < 0.5f && Math.abs(position.y - lastPosition.y) < 0.5f) {
            lastPosition.z += dt;
            if (lastPosition.z > 0.3f) {
                aiTimer += 10.0f;
            }
        } else {
            lastPosition.x = position.x;
            lastPosition.y = position.y;
            lastPosition.z = 0.0f;
        }

        super.update(dt);
    }*/

/*    private void move(Direction prefferedDirection, float dt) {
        tmp.set(position);
        tmp.add(speed * direction.getX() * dt, speed * direction.getY() * dt);
        if (gameScreen.getMap().isAreaClear(tmp.x, tmp.y, width / 2)) {
            angle = direction.getAngle();
            position.set(tmp);
        }
    }*/

    public void tryToAttack() {
        if (attackTime > weapon.getAttackPeriod()) {
            attackTime = 0.0f;
            tmp.set(position).add(direction.getX() * 60, direction.getY() * 60);
            if (gs.getHero().getArea().contains(tmp)) {
                gs.getHero().takeDamage(weapon.getDamage(), Color.RED);
            }
        }
    }
}
