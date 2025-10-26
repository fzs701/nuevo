/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;

public class GameOverScreen implements Screen {

    private final Pantalla game;
    private final int puntos;
    private SpriteBatch batch;
    private BitmapFont font;
    private Texture fondo;
    private Sound sonido;

    public GameOverScreen(Pantalla game, int puntos) {
        this.game = game;
        this.puntos = puntos;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        fondo = new Texture(Gdx.files.internal("final.jpg")); 
        sonido = Gdx.audio.newSound(Gdx.files.internal("final.mp3"));
        sonido.play();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(fondo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font.setColor(0,0,0,1);
        font.draw(batch, "GAME OVER", 50, 350);
        font.draw(batch, "Puntos: " + puntos, 50, 300);
        font.draw(batch, "Presiona ENTER para volver al menú", 50, 250);
        batch.end();

        if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
            sonido.stop();
            sonido.dispose();
            game.setScreen(new Imagen(game));
            //game.setScreen(new Menu(game));
        }
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        fondo.dispose();
    }
}
