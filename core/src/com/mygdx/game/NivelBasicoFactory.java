/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

/**
 *
 * @author usuario
 */
public class NivelBasicoFactory implements NivelFactory {

    private final Recursos r = Recursos.getInstancia();

    @Override
    public Fabricar crearFabricadorCerveza() {
        return new CervezaFabricar(r.cervezaTex, r.sonidoCerveza);
    }

    @Override
    public Fabricar crearFabricadorAgua() {
        return new AguaFabricar(r.aguaTex, r.sonidoAgua);
    }

    @Override
    public Fabricar crearFabricadorDona() {
        return new DonaFabricar(r.donaTex, r.sonidoDona);
    }
}