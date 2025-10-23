/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author usuario
 */
public abstract class ObjetoAtrapar {
    protected Texture texture;
    protected Rectangle rect;     //posicion
    protected float speed;        //velocidad (speed)
    
    public static final float SIZE = 64f;

    public ObjetoAtrapar(Texture texture, float x, float y, float speed) {
        this.texture = texture;
        this.speed = speed;
        this.rect = new Rectangle(x, y, SIZE, SIZE);
    }
    public ObjetoAtrapar() {
}


    public void update(float dt) { rect.y -= speed * dt; } //mueve el objeto
    
    //dibuja la imagen en posicion actual
    public void draw(SpriteBatch batch) { 
        batch.draw(texture, rect.x, rect.y,rect.width, rect.height); 
    }
    
    public boolean fueraDePantalla() {  //si cayo fuera de pantalla, borra o reinicia
        return rect.y + rect.height < 0; 
    }
    //recolocar objeto en nueva posicion
    public void reset(float x, float y) { 
        rect.setPosition(x, y); 
    }

    public Rectangle getArea() { return rect; }

    public void dispose() { texture.dispose(); }
}
