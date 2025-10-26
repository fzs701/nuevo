/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author usuario
 */
public interface Fabricar {
    ObjetoAtrapar crear(float x, float y, float factorVelocidad);
    void actualizarMovimiento(Jugador homero, float factorVelocidad);
    void draw(SpriteBatch batch);
    void dispose();
}