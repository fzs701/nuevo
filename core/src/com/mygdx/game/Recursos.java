/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author usuario
 */
public class Recursos {
    
    private static Recursos instancia;

    // Texturas
    public final Texture homero2;
    public final Texture fondoCasa;
    public final Texture cervezaTex;
    public final Texture aguaTex;
    public final Texture donaTex;
    public final Texture splashSimpsons;
    public final Texture fondoGameOver;

    // Sonidos
    public final Sound sonidoAgua;
    public final Sound sonidoCerveza;
    public final Sound sonidoDona;
    public final Sound sonidoIntro;
    public final Sound sonidoFinal;

    // Constructor privado
    private Recursos() {
        homero2 = new Texture("homero2.png");
        fondoCasa = new Texture("casa.png");
        cervezaTex = new Texture("cerveza.png");
        aguaTex = new Texture("agua2.png");
        donaTex = new Texture("dona2.png");
        splashSimpsons = new Texture("the-simpsons.jpg");
        fondoGameOver = new Texture("final.jpg");

        sonidoAgua = Gdx.audio.newSound(Gdx.files.internal("oh.mp3"));
        sonidoCerveza = Gdx.audio.newSound(Gdx.files.internal("yuju.mp3"));
        sonidoDona = Gdx.audio.newSound(Gdx.files.internal("rosca.mp3"));
        sonidoIntro = Gdx.audio.newSound(Gdx.files.internal("intro.mp3"));
        sonidoFinal = Gdx.audio.newSound(Gdx.files.internal("final.mp3"));
    }

    // Acceso global
    public static Recursos getInstancia() {
        if (instancia == null) instancia = new Recursos();
        return instancia;
    }

    public void dispose() {
        homero2.dispose();
        fondoCasa.dispose();
        cervezaTex.dispose();
        aguaTex.dispose();
        donaTex.dispose();
        splashSimpsons.dispose();
        fondoGameOver.dispose();

        sonidoAgua.dispose();
        sonidoCerveza.dispose();
        sonidoDona.dispose();
        sonidoIntro.dispose();
        sonidoFinal.dispose();
    }
}