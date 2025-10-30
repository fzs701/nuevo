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
import com.badlogic.gdx.audio.Sound;

/**
 *
 * @author usuario
 */

public class Cerveza extends ObjetoAtrapar {

    private Array<Rectangle> cervezasPos;   // posiciones de cada cerveza
    private Array<Float> cervezasSpeed;     // velocidad individual
    private long lastSpawnTime;             // tiempo para crear nuevas
    private final float spawnIntervalSeconds = 0.8f; 
    private final int MAX_DROPS = 6; 
    private final Sound cervezaSound;

    
    public Cerveza(Texture tex, float x, float y, float speed, Sound cervezaSound) {
        super(tex, x, y, speed);
        this.cervezaSound = cervezaSound;
    }

    // Inicializa las listas y genera la primera cerveza 
    public void crear() {
        cervezasPos = new Array<>();
        cervezasSpeed = new Array<>();
        crearCerveza();
    }

    // Crea una nueva cerveza en una posición aleatoria 
    private void crearCerveza() {
        if (cervezasPos.size >= MAX_DROPS) return;
        Rectangle cerveza = new Rectangle();
        cerveza.x = MathUtils.random(0, 800 - SIZE);
        cerveza.y = 480 + MathUtils.random(0, 200);
        cerveza.width = SIZE;
        cerveza.height = SIZE;

        cervezasPos.add(cerveza);
        cervezasSpeed.add(MathUtils.random(140f, 160f)); // velocidad al caer
        lastSpawnTime = TimeUtils.nanoTime();
    }

    //Actualiza la caída de las cervezas y la detección con Homero 
    @Override
    public void actualizarMovimiento(Jugador homero,  float factorVelocidad) {
        // cada medio segundo se crea una cerveza nueva
        if ((TimeUtils.nanoTime() - lastSpawnTime)  / 1_000_000_000.0f > spawnIntervalSeconds){
            
            if (MathUtils.random() < 0.9f) crearCerveza(); // 90% de probabilidad de spawn
            else lastSpawnTime = TimeUtils.nanoTime();
        }

        for (int i = 0; i < cervezasPos.size; i++) {
            Rectangle c = cervezasPos.get(i);
            float velocidadCerveza = cervezasSpeed.get(i);

            // caída
            c.y -= velocidadCerveza * Gdx.graphics.getDeltaTime() * factorVelocidad;

            // si toca el suelo, reaparece arriba
            if (c.y + c.height < 0) {
                c.x = MathUtils.random(0, 800 - SIZE);
                c.y = 480 + MathUtils.random(0, 200);
                continue;
            }

            // si choca con homero, suma puntos y reaparece
            if (c.overlaps(homero.getArea())){
                onCatch(homero);
                cervezasPos.removeIndex(i);
                cervezasSpeed.removeIndex(i);
            }
        }
    }

    //cervezas activas 
    @Override
    public void draw(SpriteBatch batch) {
        for (Rectangle c : cervezasPos) {
            batch.draw(texture, c.x, c.y, c.width, c.height);
        }
    }
    @Override
    public void onCatch(Jugador jugador){
        jugador.sumarPuntos(1);
        if (cervezaSound != null) cervezaSound.play();
    }
    
    @Override
    public boolean isHarmful(){ return false ;}

    
    

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
        texture.dispose();
    }
    
}




