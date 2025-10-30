/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AguaFabricar implements Fabricar {
    private final Texture tex;
    private final Sound sonido;
    private Agua agua;

    public AguaFabricar(Texture tex, Sound sonido) {
        this.tex = tex;
        this.sonido = sonido;
    }

    @Override
    public Agua crear(float x, float y, float factorVelocidad) {
        agua = new Agua(tex, x, y, 100f, sonido);
        agua.crear();
        return agua;
    }

    @Override
    public void actualizarMovimiento(Jugador homero, float factorVelocidad) {
        if(agua != null) agua.actualizarMovimiento(homero, factorVelocidad);
    }

    @Override
    public void draw(SpriteBatch batch) {
        if(agua != null) agua.draw(batch);
    }

    @Override
    public void dispose() {
        tex.dispose();
        sonido.dispose();
    }
}
