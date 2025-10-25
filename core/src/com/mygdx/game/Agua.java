/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.Sound;
/**
 *
 * @author usuario
 */
public class Agua extends ObjetoAtrapar {
    private Array<Rectangle> aguasPos;
    private Array<Float> aguasSpeed;
    private long lastSpawnTime;
    private final float spawnIntervalSeconds = 1.2f; // menos frecuente que cerveza
    private final int MAX_DROPS = 4;
    private Sound aguaSound;
    
    public Agua(Texture tex, float x, float y, float speed, Sound aguaSound){
        super(tex,x,y,speed);
        this.aguaSound = aguaSound;
    }
    public void crear() {
        aguasPos = new Array<>();
        aguasSpeed = new Array<>();
        crearAgua();
        
    }
    
    private void crearAgua(){
        if (aguasPos.size >= MAX_DROPS) return;
        Rectangle a = new Rectangle();
        a.x = MathUtils.random(0, 800 - SIZE);
        a.y = 480 + MathUtils.random(0, 200);
        a.width = SIZE; a.height = SIZE;

        aguasPos.add(a);
        aguasSpeed.add(MathUtils.random(140, 160f));
        lastSpawnTime = TimeUtils.nanoTime();
    }
    
    public void actualizarMovimiento(Jugador homero,  float factorVelocidad) {
        if ((TimeUtils.nanoTime() - lastSpawnTime)  / 1_000_000_000.0f > spawnIntervalSeconds) {
            if (MathUtils.random() < 0.8f) crearAgua();
            else lastSpawnTime = TimeUtils.nanoTime();
        }
        for (int i = 0; i < aguasPos.size; i++) {
            Rectangle a = aguasPos.get(i);
            float velocidadAgua = aguasSpeed.get(i);

            a.y -= velocidadAgua * Gdx.graphics.getDeltaTime() * factorVelocidad;

            if (a.y + a.height < 0) {
                a.x = MathUtils.random(0, 800 - SIZE);
                a.y = 480 + MathUtils.random(0, 200);
                continue;
            }

            if (a.overlaps(homero.getArea())) {
                onCatch(homero);
                aguasPos.removeIndex(i);
                aguasSpeed.removeIndex(i);
            }
        }
    }
    @Override
    public void draw(SpriteBatch batch) {
        for (Rectangle a : aguasPos) batch.draw(texture, a.x, a.y, a.width, a.height);
    }

    @Override
    public void onCatch(Jugador jugador) {
        jugador.dañar();
        if (aguaSound != null) aguaSound.play();
    }

    @Override
    public boolean isHarmful() { return true; }
    @Override
    public void dispose() {
        texture.dispose();
    }

    
}
