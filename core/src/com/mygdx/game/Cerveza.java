/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author usuario
 */

public class Cerveza {
    public static final float SIZE = 64f;

    private final Texture texture;
    private final Rectangle rect;
    private final float speed;

    public Cerveza(Texture tex, float x, float y, float speed) {
        this.texture = tex;
        this.speed = speed;
        this.rect = new Rectangle(x, y, SIZE, SIZE); // <-- rect de 64x64
    }

    public void update(float dt) { rect.y -= speed * dt; }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, rect.x, rect.y, SIZE, SIZE); // <-- dibuja 64x64
    }

    public boolean fueraDePantalla() { return rect.y + rect.height < 0; }

    public Rectangle getArea() { return rect; }

    // Respawn rápido arriba
    public void reset(float x, float y) {
        rect.setPosition(x, y);
    }

    public void dispose() { texture.dispose(); }
}


