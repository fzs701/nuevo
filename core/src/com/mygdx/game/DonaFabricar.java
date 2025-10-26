/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 *
 * @author usuario
 */

public class DonaFabricar implements Fabricar {
    private Texture tex;
    private Sound sonido;
    private Dona dona;

    public DonaFabricar(Texture tex, Sound sonido) {
        this.tex = tex;
        this.sonido = sonido;
    }

    @Override
    public Dona crear(float x, float y, float factorVelocidad) {
        dona = new Dona(tex, x, y, 100f, sonido);
        dona.crear();
        return dona;
    }

    @Override
    public void actualizarMovimiento(Jugador homero, float factorVelocidad) {
        if(dona != null) dona.actualizarMovimiento(homero, factorVelocidad);
    }

    @Override
    public void draw(SpriteBatch batch) {
        if(dona != null) dona.draw(batch);
    }

    @Override
    public void dispose() {
        tex.dispose();
        sonido.dispose();
    }
}

