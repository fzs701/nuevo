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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;

public class Imagen extends PantallaBase {

    private Texture splashTexture;
    private BitmapFont font;
    private Sound splashSound;

    public Imagen(Pantalla game) {
        super(game); // PantallaBase guarda game y batch
    }

    @Override
    public void show() {

        //Obtener recursos desde Singleton
        Recursos r = Recursos.getInstancia();

        splashTexture = r.splashSimpsons;
        splashSound = r.sonidoIntro;

        splashSound.play();

        font = new BitmapFont();
        font.setColor(Color.BLACK);
    }

    @Override
    protected void actualizar(float delta) {
        if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
            game.setScreen(new Ejemplo(game));
        }
    }

    @Override
    protected void dibujar(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        batch.begin();
        batch.draw(splashTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font.draw(batch, "Presione ENTER para continuar", 200, 50);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();   // libera batch (PantallaBase)
        font.dispose();    // recursos del propio objeto
        // NO liberar textura ni sonido, lo hace Recursos (Singleton)
    }
}

