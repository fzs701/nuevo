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
public abstract class ObjetoAtrapar {
    protected Texture texture;
    protected Rectangle rect;
    protected float speed;

    public ObjetoAtrapar(Texture texture, float x, float y, float speed) {
        this.texture = texture;
        this.speed = speed;
        this.rect = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
    }
    public ObjetoAtrapar() {
}


    public void update(float dt) { rect.y -= speed * dt; }
    public void draw(SpriteBatch batch) { batch.draw(texture, rect.x, rect.y); }
    public boolean fueraDePantalla() { return rect.y + rect.height < 0; }
    public Rectangle getArea() { return rect; }

    public void dispose() { texture.dispose(); }
}
