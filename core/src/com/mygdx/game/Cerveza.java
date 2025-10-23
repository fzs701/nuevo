/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

/**
 *
 * @author usuario
 */

public class Cerveza extends ObjetoAtrapar {

    private Array<Rectangle> cervezasPos;   // posiciones de cada cerveza
    private Array<Float> cervezasSpeed;     // velocidad individual
    private long lastSpawnTime;             // tiempo para crear nuevas
    private Texture texturaCerveza;         // textura compartida

    public Cerveza(Texture tex, float x, float y, float speed) {
        super(tex, x, y, speed);
        this.texturaCerveza = tex;
    }

    /** Inicializa las listas y genera la primera cerveza */
    public void crear() {
        cervezasPos = new Array<>();
        cervezasSpeed = new Array<>();
        crearCerveza();
    }

    /** Crea una nueva cerveza en una posición aleatoria arriba de la pantalla */
    private void crearCerveza() {
        Rectangle cerveza = new Rectangle();
        cerveza.x = MathUtils.random(0, 800 - 64);
        cerveza.y = 480 + MathUtils.random(0, 200);
        cerveza.width = 64;
        cerveza.height = 64;

        cervezasPos.add(cerveza);
        cervezasSpeed.add(MathUtils.random(280f, 400f)); // velocidad al caer
        lastSpawnTime = TimeUtils.nanoTime();
    }

    /** Actualiza la caída de las cervezas y la detección con Homero */
    public void actualizarMovimiento(Jugador homero) {
        // cada medio segundo se crea una cerveza nueva
        if (TimeUtils.nanoTime() - lastSpawnTime > 500_000_000L) crearCerveza();

        for (int i = 0; i < cervezasPos.size; i++) {
            Rectangle c = cervezasPos.get(i);
            float speed = cervezasSpeed.get(i);

            // caída
            c.y -= speed * Gdx.graphics.getDeltaTime();

            // si toca el suelo → reaparece arriba
            if (c.y + c.height < 0) {
                c.x = MathUtils.random(0, 800 - 64);
                c.y = 480 + MathUtils.random(0, 200);
                continue;
            }

            // si choca con Homero → sumar puntos y reaparecer
            if (c.overlaps(homero.getArea())) {
                homero.sumarPuntos(10);
                c.x = MathUtils.random(0, 800 - 64);
                c.y = 480 + MathUtils.random(0, 200);
            }
        }
    }

    /** Dibuja todas las cervezas activas */
    @Override
    public void draw(SpriteBatch batch) {
        for (Rectangle c : cervezasPos) {
            batch.draw(texturaCerveza, c.x, c.y, c.width, c.height);
        }
    }

    @Override
    public void update(float dt) {
        // No se usa directamente: las cervezas se actualizan en actualizarMovimiento()
    }

    @Override
    public boolean fueraDePantalla() {
        return rect.y + rect.height < 0;
    }

    @Override
    public Rectangle getArea() {
        return rect;
    }

    @Override
    public void reset(float x, float y) {
        rect.setPosition(x, y);
    }

    @Override
    public void dispose() {
        texturaCerveza.dispose();
    }
}




