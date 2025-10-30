/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

/**
 *
 * @author usuario
 */

   
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Jugador {
    private final Texture texture; //imagen homero
    private final Rectangle area = new Rectangle(); //posicion y tamaño
    private final float velx = 400f;
    
    private int vidas = 3;
    private int puntos = 0;
    //variables herido 
    private boolean herido = false;
    private float tHerido = 0f;
    private boolean heridoTemporal = false;
    private float tHeridoTemporal = 0f;
    
    public Jugador(Texture tex) { this.texture = tex; }

    public void crear() {  //jugador en centro
        area.set(800/2f - 32, 20, 64, 64);

    }

    public void actualizar(float dt) { //movimiento de homero
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))  area.x -= velx * dt;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) area.x += velx * dt;
        area.x = MathUtils.clamp(area.x, 0, 800 - area.width);
        
        if(herido){
            tHerido -=dt;
            if(tHerido <= 0f) herido = false;
        }
        if (heridoTemporal){
            tHeridoTemporal -= dt;
            if(tHeridoTemporal <= 0f) heridoTemporal = false;
        }
    }

    public void dibujar(SpriteBatch batch) { //se mueve si esta herido
        float shake = (herido || heridoTemporal) ? MathUtils.random(-3, 3) : 0f;
        batch.draw(texture, area.x + shake, area.y, area.width, area.height);
    }

    
    public void sumarPuntos(int pp) { puntos += pp; }
    public void dañar() { 
        vidas--; 
        herido = true; 
        tHerido = 0.4f; 
    }
    
    public void vibrarTemporal(){
        heridoTemporal = true;
        tHeridoTemporal = 0.2f;
    }
    //getters
    public int getVidas() { return vidas; }
    public int getPuntos() { return puntos; }
    public boolean estaHerido() { return herido; }
    
    public Rectangle getArea() { return area; }
    public void dispose() { texture.dispose(); } //libera memoria
}

    

