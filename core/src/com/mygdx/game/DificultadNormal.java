/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

/**
 *
 * @author usuario
 */
public class DificultadNormal implements Dificultad {
    //colocar segun puntos la velocidad al que debe ir 
    @Override
    public float factorVelocidad(int puntos) {
        if (puntos >= 80) return 3f;
        if (puntos >= 60) return 2.5f;
        if (puntos >= 40) return 2f;
        if (puntos >= 20) return 1.5f;
        return 1f;
    }
    
}
