/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author usuario
 */
public abstract class PantallaBase implements Screen {
    // Referencia al controlador principal del juego
    protected final Pantalla game; 
    protected final SpriteBatch batch; //SpriteBatch único por pantalla, usado para dibujar imágenes

    public PantallaBase(Pantalla game) {
        this.game = game;
        this.batch = new SpriteBatch();
    }

    
    @Override
    public final void render(float delta) {
        actualizar(delta);
        dibujar(delta);
    }

    protected abstract void actualizar(float delta);

    protected abstract void dibujar(float delta);

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
    }
}

