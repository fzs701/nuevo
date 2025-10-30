/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

/**
 *
 * @author usuario
 */


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Input.Keys;

public class Menu implements Screen {

    private final Pantalla game;
     private SpriteBatch batch;
    private BitmapFont font;

    
    public Menu(Pantalla game) {
        this.game = game;
    }
    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        
        if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
            game.setScreen(new Ejemplo(game));
        }
    }
    

    @Override public void resize(int width, int height) {}
    @Override public void hide() {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
