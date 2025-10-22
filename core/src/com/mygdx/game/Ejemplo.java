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

import puppy.code.Lluvia;
import puppy.code.Tarro;

public class Ejemplo extends ApplicationAdapter {
    private OrthographicCamera camera;
    private SpriteBatch batch;	   
	   private BitmapFont font;
	   
	   private Tarro tarro;
	   private Lluvia lluvia;
           // AÑADE:
    private final boolean modoHomero = true;   
    private Jugador homero;             
    private Cerveza cerveza;  
    private Viewport viewport;

	@Override
	public void create () {
		 font = new BitmapFont(); // use libGDX's default Arial font
		 
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
	      lluvia.crear();
            // AÑADE (modo Homero sin sonido, solo sprites):
        if (modoHomero) {
            Texture homerTex = new Texture(Gdx.files.internal("homero.jpg"));
            homero = new Jugador(homerTex);
            homero.crear();

            Texture beerTex = new Texture(Gdx.files.internal("cerveza.png"));
            float x = 800/2f - beerTex.getWidth()/2f;
            float y = 480 + 10;          
            float speed = 200f;          
            cerveza = new Cerveza(beerTex, x, y, speed);
        }
        camera = new OrthographicCamera();
        viewport = new FitViewport(800, 480, camera);
        camera.position.set(400, 240, 0);
        camera.update();

    }
	


	@Override
	public void render () {
		//limpia la pantalla con color azul obscuro.
		ScreenUtils.clear(0, 0, 0.2f, 1);
                // AÑADE (calcular delta time):
                float dt = Gdx.graphics.getDeltaTime();

                if (modoHomero) {
                    homero.actualizar(dt);
                    if (!cerveza.fueraDePantalla()) {
                        cerveza.update(dt);
                    }
                }

		//actualizar matrices de la cámara
		camera.update();
		//actualizar 
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		//dibujar textos
		font.draw(batch, "Gotas totales: " + tarro.getPuntos(), 5, 475);
		font.draw(batch, "Vidas : " + tarro.getVidas(), 720, 475);
		
		if (!tarro.estaHerido()) {
			// movimiento del tarro desde teclado
	        tarro.actualizarMovimiento();        
			// caida de la lluvia 
	        lluvia.actualizarMovimiento(tarro);	   
		}
                viewport.apply();
                
                batch.setProjectionMatrix(camera.combined);
		tarro.dibujar(batch);
		lluvia.actualizarDibujoLluvia(batch);
		
		batch.end();	
		batch.setProjectionMatrix(camera.combined);
                batch.begin();

                if (modoHomero) {
                    homero.dibujar(batch);
                    cerveza.draw(batch);
                } else {
                    font.draw(batch, "Gotas totales: " + tarro.getPuntos(), 5, 475);
                    font.draw(batch, "Vidas : " + tarro.getVidas(), 720, 475);

                    if (!tarro.estaHerido()) {
                        tarro.actualizarMovimiento();
                        lluvia.actualizarMovimiento(tarro);
                    }

                    tarro.dibujar(batch);
                    lluvia.actualizarDibujoLluvia(batch);
                }

                batch.end();

	}
	
	@Override
	public void dispose () {
	      tarro.destruir();
          lluvia.destruir();
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

