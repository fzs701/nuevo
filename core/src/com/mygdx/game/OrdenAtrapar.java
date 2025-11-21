/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;


/**
 *
 * @author usuario
 */
public class OrdenAtrapar {

    private final Array<ObjetoAtrapar> objetos;

    public OrdenAtrapar() {
        objetos = new Array<>();
    }

    public void agregar(ObjetoAtrapar o) {
        objetos.add(o);
    }

    public void actualizarTodos(Jugador jugador, float factorVelocidad) {
        for (int i = objetos.size - 1; i >= 0; i--) {
            ObjetoAtrapar o = objetos.get(i);

            o.actualizarMovimiento(jugador, factorVelocidad);

            // Atrapar
            if (o.getArea().overlaps(jugador.getArea())) {
                o.onCatch(jugador);
                objetos.removeIndex(i);
                continue;
            }

            // Fuera de pantalla
            if (o.fueraDePantalla()) {
                objetos.removeIndex(i);
            }
        }
    }

    public void draw(SpriteBatch batch) {
        for (ObjetoAtrapar o : objetos) {
            o.draw(batch);
        }
    }

    public void dispose() {
        for (ObjetoAtrapar o : objetos)
            o.dispose();
    }
}
