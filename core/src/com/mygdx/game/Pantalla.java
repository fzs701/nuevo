/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

/**
 *
 * @author usuario
 */

import com.badlogic.gdx.Game;

public class Pantalla extends Game {
    @Override
    public void create() {
        setScreen(new Imagen(this)); //mostrar pantalla introductoria
    }

    
}
