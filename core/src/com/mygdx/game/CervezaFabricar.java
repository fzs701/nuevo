/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

/**
 *
 * @author usuario
 */

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CervezaFabricar implements Fabricar { 
    private final Texture tex;
    private final Sound sonido;
    private Cerveza cerveza;

    public CervezaFabricar(Texture tex, Sound sonido) { //constructor con sonido y textura ya cargados
        this.tex = tex;
        this.sonido = sonido;
    }

    @Override //crear cerveza
    public Cerveza crear(float x, float y, float factorVelocidad) {
        cerveza = new Cerveza(tex, x, y, 100f, sonido);
        cerveza.crear();
        return cerveza;
    }

    @Override
    public void actualizarMovimiento(Jugador homero, float factorVelocidad) {
        if(cerveza != null) cerveza.actualizarMovimiento(homero, factorVelocidad);
    }

    @Override
    public void draw(SpriteBatch batch) {
        if(cerveza != null) cerveza.draw(batch);
    }

    @Override
    public void dispose() {
        tex.dispose();
        sonido.dispose();
    }
}
