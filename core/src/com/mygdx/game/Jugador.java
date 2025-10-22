/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

/**
 *
 * @author usuario
 */

   
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Jugador {
    private final Texture texture;
    private final Rectangle area = new Rectangle();
    private float velx = 400f;
    public Jugador(Texture tex) { this.texture = tex; }

    public void crear() {
        area.set(800/2f - 32, 20, 64, 64);

    }

    public void actualizar(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))  area.x -= velx * dt;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) area.x += velx * dt;
        area.x = MathUtils.clamp(area.x, 0, 800 - area.width);
    }

    public void dibujar(SpriteBatch batch) {
        float drawWidth = 64;
        float drawHeight = 64;
        batch.draw(texture, area.x, area.y, area.width, area.height);


    }
    public Rectangle getArea() { return area; }
    public void dispose() { texture.dispose(); }
}

    

