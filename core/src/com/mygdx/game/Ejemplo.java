package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.math.MathUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

//import puppy.code.Lluvia;
//import puppy.code.Tarro;



public class Ejemplo implements Screen {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private Viewport viewport;
    private Sound cervezaSound, aguaSound;
    
    
    
    private Sound donaSound;
    private float factorVelocidad = 1f;



    private Jugador homero;
    private Cerveza cerveza;
    private Dona dona;
    private Agua agua;
    private Texture fondo;

    private final Pantalla game;

    public Ejemplo(Pantalla game) {
        this.game = game;
    }

    @Override
    public void show() {
        font = new BitmapFont();
        camera = new OrthographicCamera();
        viewport = new FitViewport(800, 480, camera);
        camera.position.set(400, 240, 0);
        camera.update();
        batch = new SpriteBatch();

        // Jugador
        Texture homeroImagen = new Texture(Gdx.files.internal("homero.jpg"));
        homero = new Jugador(homeroImagen);
        homero.crear();

        // Agua
        Texture aguaImagen = new Texture(Gdx.files.internal("agua.jpg"));
        //agua = new Agua(aguaImagen, 0, 0, 120f); // velocidad más lenta
       

        // Cerveza
        Texture cervezaImagen = new Texture(Gdx.files.internal("cerveza.png"));
        //cerveza = new Cerveza(cervezaImagen, 0, 0, 100f); // velocidad más lenta
        
        cervezaSound = Gdx.audio.newSound(Gdx.files.internal("yuju.mp3"));
        cerveza = new Cerveza(cervezaImagen, 0, 0, 100f, cervezaSound);
        cerveza.crear();
        aguaSound = Gdx.audio.newSound(Gdx.files.internal("oh.mp3"));
        agua = new Agua(aguaImagen, 0, 0, 120f, aguaSound);
        agua.crear();
        
        Texture donaImagen = new Texture(Gdx.files.internal("dona.png"));
        donaSound = Gdx.audio.newSound(Gdx.files.internal("yuju.mp3"));
        dona = new Dona(donaImagen, 0, 0, 100f, donaSound);
        dona.crear();
        fondo = new Texture(Gdx.files.internal("casa.png"));
        
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        viewport.apply();
        batch.setProjectionMatrix(camera.combined);

        // Actualizar posiciones
        homero.actualizar(delta);
        
        if(homero.getPuntos() >= 80) factorVelocidad = 3f;
        else if(homero.getPuntos() >= 60) factorVelocidad = 2.5f;
        else if(homero.getPuntos() >= 40) factorVelocidad = 2f;
        else if(homero.getPuntos() >= 20) factorVelocidad = 1.5f;
        else factorVelocidad = 1f;
        cerveza.actualizarMovimiento(homero,factorVelocidad);
        agua.actualizarMovimiento(homero,factorVelocidad);
        dona.actualizarMovimiento(homero, factorVelocidad);

        // Dibujar
        batch.begin();
        batch.draw(fondo, 0, 0, 800, 480);
        homero.dibujar(batch);
        cerveza.draw(batch);
        agua.draw(batch);
        dona.draw(batch);
        font.draw(batch, "Puntos: " + homero.getPuntos(), 10, 470);
        font.draw(batch, "Vidas: " + homero.getVidas(), 720, 475);
        font.draw(batch, "Puntos: " + homero.getPuntos(), 10, 470);
        font.draw(batch, "Vidas: " + homero.getVidas(), 720, 475);

        batch.end();

        // Game Over
        if (homero.getVidas() <= 0) {
            game.setScreen(new GameOverScreen(game, homero.getPuntos()));
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        homero.dispose();
        cerveza.dispose();
        agua.dispose();
        cervezaSound.dispose();
        aguaSound.dispose();
        dona.dispose();
        donaSound.dispose();
        fondo.dispose();

    }
}


