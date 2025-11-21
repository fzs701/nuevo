package com.mygdx.game;

//import com.badlogic.gdx.ApplicationAdapter;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;





public class Ejemplo extends PantallaBase {
    private OrthographicCamera camera;
    private BitmapFont font;
    private Viewport viewport;
    private OrdenAtrapar gestor;
    private Dificultad dificultad;
    private NivelFactory nivelFactory;


    
    
    private float factorVelocidad = 1f;



    private Jugador homero;
    private Texture fondo;


    public Ejemplo(Pantalla game) {
        super(game);
    }

    @Override
    public void show() {
        //iniciamos camara y viewport
        font = new BitmapFont();
        font.getData().setScale(2f);
        camera = new OrthographicCamera();
        viewport = new FitViewport(800, 480, camera);
        camera.position.set(400, 240, 0);
        camera.update();
        
        gestor = new OrdenAtrapar();
        Recursos r = Recursos.getInstancia();
        dificultad = new DificultadNormal();
        nivelFactory = new NivelBasicoFactory();


        // Jugador
        Texture homeroImagen = Recursos.getInstancia().homero2;
        homeroImagen.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        
        homero = new Jugador(homeroImagen);
        homero.crear();
        
        // Crear fabricadores y agregar a gestor
        gestor.agregar(nivelFactory.crearFabricadorAgua().crear(0, 480, 1f));
        gestor.agregar(nivelFactory.crearFabricadorCerveza().crear(0, 480, 1f));
        gestor.agregar(nivelFactory.crearFabricadorDona().crear(0, 480, 1f));

        // Fondo
        fondo = r.fondoCasa;
        fondo.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);


    }

    @Override
    protected void actualizar(float delta) {

        camera.update();
        viewport.apply();
        batch.setProjectionMatrix(camera.combined);

        // Actualizar jugador
        homero.actualizar(delta);

        // Actualizar estrategia de dificultad
        factorVelocidad = dificultad.factorVelocidad(homero.getPuntos());

        // Actualizar objetos que caen
        gestor.actualizarTodos(homero, factorVelocidad);

        // Game Over
        if (homero.getVidas() <= 0) {
            game.setScreen(new GameOverScreen(game, homero.getPuntos()));
        }
    }
    @Override
    protected void dibujar(float delta) {

        ScreenUtils.clear(0, 0, 0.2f, 1);

        batch.begin();
        batch.draw(fondo, 0, 0, 800, 580);

        homero.dibujar(batch);
        gestor.draw(batch);

        font.draw(batch, "Puntos: " + homero.getPuntos(), 10, 470);
        font.draw(batch, "Vidas: " + homero.getVidas(), 670, 475);

        batch.end();
    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    
    //liberar recursos
    @Override
    public void dispose() {
        super.dispose(); // Libera batch de PantallaBase
        font.dispose();
        homero.dispose();
        gestor.dispose(); // Libera todos los objetos atrapables
    }

}


