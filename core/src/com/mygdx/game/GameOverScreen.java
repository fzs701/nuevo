/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOverScreen  extends PantallaBase {

    private final int puntos;
    private BitmapFont font;
    private Texture fondo;
    private Sound sonido;

    public GameOverScreen(Pantalla game, int puntos) {
        super(game);
        this.puntos = puntos;
    }

    @Override
    public void show() {
        Recursos r = Recursos.getInstancia();
        fondo = r.fondoGameOver;
        sonido = r.sonidoFinal;

        sonido.play();

        font = new BitmapFont();
        font.setColor(Color.BLACK);
    }
    @Override
    protected void actualizar(float delta) {

        // Volver al menú con ENTER
        if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
            sonido.stop();
            game.setScreen(new Imagen(game));
        }
    }

    @Override
    protected void dibujar(float delta) {

        ScreenUtils.clear(0.1f, 0, 0, 1);

        batch.begin();

        batch.draw(fondo, 0, 0, 
                   Gdx.graphics.getWidth(), 
                   Gdx.graphics.getHeight());

        font.draw(batch, "GAME OVER", 50, 350);
        font.draw(batch, "Puntos: " + puntos, 50, 300);
        font.draw(batch, "Presiona ENTER para volver al menú", 50, 250);

        batch.end();
    }


    
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override
    public void dispose() {
        super.dispose();
        font.dispose();
    }
}
