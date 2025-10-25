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
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.audio.Sound;

public class Dona extends ObjetoAtrapar {

    private Array<Rectangle> donasPos;
    private Array<Float> donasSpeed;
    private long lastSpawnTime;
    private final float spawnIntervalSeconds = 8f; // menos frecuente que cerveza o agua
    private final int MAX_DONAS = 1; // máximo 1 dona en pantalla
    private Sound donaSound;

    public Dona(Texture tex, float x, float y, float speed, Sound donaSound) {
        super(tex, x, y, speed);
        this.donaSound = donaSound;
    }

    public void crear() {
        donasPos = new Array<>();
        donasSpeed = new Array<>();
        crearDona();
    }

    private void crearDona() {
        if (donasPos.size >= MAX_DONAS) return; // no crear si ya hay una
        Rectangle d = new Rectangle();
        d.x = MathUtils.random(0, 800 - SIZE);
        d.y = 480 + MathUtils.random(0, 200);
        d.width = SIZE;
        d.height = SIZE;

        donasPos.add(d);
        donasSpeed.add(MathUtils.random(150f, 180f));
        lastSpawnTime = TimeUtils.nanoTime();
    }

    public void actualizarMovimiento(Jugador homero, float factorVelocidad) {
        if ((TimeUtils.nanoTime() - lastSpawnTime) / 1_000_000_000.0f > spawnIntervalSeconds) {
            crearDona();
        }

        for (int i = donasPos.size - 1; i >= 0; i--) { // iterar al revés
            Rectangle d = donasPos.get(i);
            float velocidadDona = donasSpeed.get(i);

            d.y -= velocidadDona * Gdx.graphics.getDeltaTime() * factorVelocidad;

            if (d.y + d.height < 0) {
                donasPos.removeIndex(i);
                donasSpeed.removeIndex(i);
                continue;
            }

            if (d.overlaps(homero.getArea())) {
                onCatch(homero);
                donasPos.removeIndex(i);
                donasSpeed.removeIndex(i);
            }
        }
    }


    @Override
    public void draw(SpriteBatch batch) {
        for (Rectangle d : donasPos) {
            batch.draw(texture, d.x, d.y, d.width, d.height);
        }
    }

    @Override
    public void onCatch(Jugador jugador) {
        jugador.sumarPuntos(5); // dona suma 5 puntos
        if (donaSound != null) donaSound.play();
    }

    @Override
    public boolean isHarmful() {
        return false;
    }

    @Override
    public void update(float dt) { }

    @Override
    public boolean fueraDePantalla() {
        for (Rectangle d : donasPos) {
            if (d.y + d.height < 0) return true;
        }
        return false;
    }

    @Override
    public Rectangle getArea() {
        return donasPos.size > 0 ? donasPos.get(0) : new Rectangle();
    }

    @Override
    public void reset(float x, float y) {
        if (donasPos.size > 0) donasPos.get(0).setPosition(x, y);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}

