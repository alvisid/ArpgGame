package com.arpg.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;

public class Map {
    public enum BlockType {
        EMPTY, WALL;
    }

    public static final int CELL_SIZE = 80;
    public static final int MAP_SIZE_X = 20;
    public static final int MAP_SIZE_X_PX = MAP_SIZE_X * CELL_SIZE;
    public static final int MAP_SIZE_Y = 10;
    public static final int MAP_SIZE_Y_PX = MAP_SIZE_Y * CELL_SIZE;

    private BlockType[][] data;
    private TextureRegion textureWall;
    private TextureRegion textureGrass;

    private Cell cells[][];

/*    private class Cell {
        TextureRegion textureWall;
        int hp;

        public Cell(TextureRegion textureWall) {
            this.textureWall = textureWall;
            this.hp = textureWall.maxHp;
        }

        public void damage() {
            if (textureWall.destructible) {
                hp--;
                if (hp <= 0) {
                    textureWall = TextureRegion.NONE;
                }
            }
        }

        public void changeType(WallType type) {
            this.type = type;
            this.hp = type.maxHp;
        }
    }*/


    public Map() {
        this.data = new BlockType[MAP_SIZE_X][MAP_SIZE_Y];

        this.textureGrass = Assets.getInstance().getAtlas().findRegion("Grass");
        this.textureWall = Assets.getInstance().getAtlas().findRegion("Wall");
        for (int i = 0; i < MAP_SIZE_X; i++) {
            for (int j = 0; j < MAP_SIZE_Y; j++) {
                data[i][j] = BlockType.EMPTY;
                if (MathUtils.random() < 0.05) {
                    data[i][j] = BlockType.WALL;
                }
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < MAP_SIZE_X; i++) {
            for (int j = 0; j < MAP_SIZE_Y; j++) {
                batch.draw(textureGrass, i * 80, j * 80);
                if (data[i][j] == BlockType.WALL) {
                    batch.draw(textureWall, i * 80 , j * 80 );
                }
            }
        }
    }

    public boolean isCellPassable(Vector2 position) {
        if (position.x < 0.0f || position.y < 0.0f) {
            return false;
        }
        int cellX = (int) (position.x / 80);
        int cellY = (int) (position.y / 80);
        if (cellX < 0 || cellX >= MAP_SIZE_X || cellY < 0 || cellY >= MAP_SIZE_Y) {
            return false;
        }
        return data[cellX][cellY] == BlockType.EMPTY;
    }

/*    public boolean isAreaClear(float x, float y, float halfSize) {
        int leftX = (int) ((x - halfSize) / CELL_SIZE);
        int rightX = (int) ((x + halfSize) / CELL_SIZE);

        int bottomY = (int) ((y - halfSize) / CELL_SIZE);
        int topY = (int) ((y + halfSize) / CELL_SIZE);

        if (leftX < 0) {
            leftX = 0;
        }
        if (rightX >= MAP_SIZE_X) {
            rightX = MAP_SIZE_X - 1;
        }
        if (bottomY < 0) {
            bottomY = 0;
        }
        if (topY >= MAP_SIZE_Y) {
            topY = MAP_SIZE_Y - 1;
        }

        for (int i = leftX; i <= rightX; i++) {
            for (int j = bottomY; j <= topY; j++) {
                if (!cells[i][j].type.unitPassable) {
                    return false;
                }
            }
        }
        return true;
    }*/
}
