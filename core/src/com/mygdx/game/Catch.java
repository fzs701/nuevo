/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

/**
 *
 * @author usuario
 */
public interface Catch {
    void onCatch(Jugador jugador); //efecto al atraparlo
    boolean isHarmful();           //si es dañino
    
}
