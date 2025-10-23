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
import com.badlogic.gdx.math.MathUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

//import puppy.code.Lluvia;
//import puppy.code.Tarro;

public class Ejemplo extends ApplicationAdapter {
    
    private OrthographicCamera camera;
    private SpriteBatch batch;	   
    private BitmapFont font;   
    //private Tarro tarro;
    //private Lluvia lluvia;
    private Viewport viewport;
    
    private final boolean modoHomero = true;   
    private Jugador homero;             
    private Cerveza cerveza;  
    

    @Override
    public void create () {
        font = new BitmapFont(); // use libGDX's default Arial font
	
        camera = new OrthographicCamera();
        viewport = new FitViewport(800,480,camera);
        camera.position.set(400,240,0);
        camera.update();
        
        batch = new SpriteBatch();
        
        /*
	// load the images for the droplet and the bucket, 64x64 pixels each 	     
        Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
        tarro = new Tarro(new Texture(Gdx.files.internal("bucket.png")),hurtSound);
          
	// load the drop sound effect and the rain background "music" 
        //Texture gota = new Texture(Gdx.files.internal("drop.png"));
        //Texture gotaMala = new Texture(Gdx.files.internal("dropBad.png"));
        Texture gota = new Texture(Gdx.files.internal("homero.jpg"));
        Texture gotaMala = new Texture(Gdx.files.internal("homero.jpg"));

        
        Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
         
	Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        lluvia = new Lluvia(gota, gotaMala, dropSound, rainMusic);
	      
        // camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
        // creacion del tarro
        tarro.crear();
        // creacion de la lluvia
        lluvia.crear(); */
        
        Texture homeroImagen = new Texture(Gdx.files.internal("homero.jpg"));
        homero = new Jugador(homeroImagen);
        homero.crear();

        Texture cervezaImagen = new Texture(Gdx.files.internal("cerveza.png"));
        float x = 800/2f - cervezaImagen.getWidth()/2f;
        float y = 480;          
        float speed = 200f;          
        cerveza = new Cerveza(cervezaImagen, x, y, speed);
        cerveza.crear();

        
        
    }
	


    @Override
    public void render () {
        
        ScreenUtils.clear(0, 0, 0.2f, 1); //limpia la pantalla con color azul obscuro.
        float dt = Gdx.graphics.getDeltaTime(); // AÑADE (calcular delta time):

        //actualizar matrices de la cámara
        camera.update();
        viewport.apply();
        //actualizar 
        batch.setProjectionMatrix(camera.combined);
        
        
        homero.actualizar(dt);
        cerveza.actualizarMovimiento(homero);   // ← aquí se mueven, colisionan y respawnean TODAS

        

        batch.begin();
        homero.dibujar(batch);
        cerveza.draw(batch);
        //dibujar textos
        font.draw(batch, "Puntos: " + homero.getPuntos(), 10, 470);
        font.draw(batch, "Vidas : " + homero.getVidas(), 720, 475);
        batch.end();
        /*
        if (!tarro.estaHerido()) {
            tarro.actualizarMovimiento();// movimiento del tarro desde teclado    
            lluvia.actualizarMovimiento(tarro);	  // caida de la lluvia 
        }
        viewport.apply();

        batch.setProjectionMatrix(camera.combined);
        tarro.dibujar(batch);
        lluvia.actualizarDibujoLluvia(batch);

        batch.end();	
        batch.setProjectionMatrix(camera.combined);
        batch.begin(); */

       

    }
	
    @Override
    public void dispose () {
        batch.dispose();
        font.dispose();
        
        if (homero != null) homero.dispose();
        if (cerveza != null) cerveza.dispose();

    }
    
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }
}

