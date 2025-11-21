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
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Input.Keys;

public class Menu extends PantallaBase {

    private BitmapFont font;

    
    public Menu(Pantalla game) {
        super(game);
    }
    @Override
    public void show() {
        font = new BitmapFont();
    }
     @Override
    protected void actualizar(float delta) {
        // Cambiar de pantalla al apretar ENTER
        if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
            game.setScreen(new Ejemplo(game));
        }
    }
    @Override
    protected void dibujar(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        font.draw(batch, "PRESIONE ENTER PARA COMENZAR", 200, 240);
        batch.end();
    }
    
    

    @Override public void resize(int width, int height) {}
    @Override public void hide() {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void dispose() {
        super.dispose();
        font.dispose();
    }
}
