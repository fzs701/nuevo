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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;

public class Imagen implements Screen {

    private final Pantalla game;
    private SpriteBatch batch;
    private Texture splashTexture;
    private Sound splashSound;
    private float timer = 0f;
    private BitmapFont font;
    private final float DURATION = 4f; // dura 3 segundos

    public Imagen(Pantalla game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        splashTexture = new Texture(Gdx.files.internal("the-simpsons.jpg")); 
        splashSound = Gdx.audio.newSound(Gdx.files.internal("intro.mp3"));
        splashSound.play();
        font = new BitmapFont();
        font.setColor(Color.BLACK);
    }

    @Override
    public void render(float delta) {
        
        ScreenUtils.clear(0, 0, 0, 1);

        batch.begin();
        
        batch.draw(splashTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font.draw(batch, "Presione ENTER para continuar", 200, 50);
        batch.end();

        if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
            game.setScreen(new Ejemplo(game));
        }
    }

    @Override
    public void resize(int width, int height) {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
    @Override
    public void dispose() {
        batch.dispose();
        splashTexture.dispose();
        splashSound.dispose();
    }
}

