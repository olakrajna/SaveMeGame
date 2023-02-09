package com.gamereadyy.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.gamereadyy.game.SaveMe;

import java.util.Random;
import java.util.Vector;

/** @author Aleksandra Krajna*/
/**Klasa tworząca obiekt rur i butelki*/

public class Tube {
    /**szerokość tuby*/
    public static final int TUBE_WIDTH = 120;
    /**wartość użyta do wylowania wysokości rury */
    private static final int FLUCTUATION = 125;
    /**odległość pomiedzy rurami - górną i dolną */
    public static final int TUBE_GAP = 200;
    /**najniższa dostępna wartość y dla górnej tuby*/
    private static final int LOWEST_OPENING = 150;

    /**tekstura rur i butelki*/
    private Texture topTube;
    private Texture bottomTube;
    public Texture bottle, plasticBag;

    /**zmienna odpowiedzialna za pozycje rur i butelki*/
    private Vector2 posTopTube, posBotTube, posBottle, posBag;

    /**zmienna odpowiedzialna za tworzenie prostokątów/granic obiektów*/
    private Rectangle boundsTop, boundsBot, boundsBottle, boundsBag;

    private Random rand;

    public Tube(float x) {

        /**Utworzenie obiektów */
        topTube = new Texture("pipe2siatka.png");
        bottomTube = new Texture("pipe1siatka.png");
        bottle = new Texture("butelka2.png");
        plasticBag = new Texture("plasticbag.png");

        rand = new Random();

        /**Nadanie pozycji obiektom*/
        posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING );
        posBottle = new Vector2(x+34, posTopTube.y - TUBE_GAP/2);
        posBag = new Vector2(x+34, posTopTube.y - TUBE_GAP/2);
        posBotTube = new Vector2(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());

        /**Utorzenie obiektów i nadanie im wartości*/
        boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
        boundsBottle = new Rectangle(posBottle.x, posBottle.y, bottle.getWidth(), bottle.getHeight());
        boundsBag = new Rectangle(posBottle.x, posBottle.y, bottle.getWidth(), bottle.getHeight());
        boundsBot = new Rectangle(posBotTube.x, posBotTube.y, bottomTube.getWidth(), bottomTube.getHeight());

    }

    /**Metody zwracające odpowiednie obiekty*/
    public Texture getTopTube() {
        return topTube;
    }
    public Texture getBottomTube() {
        return bottomTube;
    }

    public Texture getPlasticBag() {
        return plasticBag;
    }

    public Vector2 getPosBag() {
        return posBag;
    }

    public Texture getBottle() {
        return bottle;
    }
    public Vector2 getPosBottle() {
        return posBottle;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }
    public Vector2 getPosBotTube() {
        return posBotTube;
    }


    /**Metoda odpowiedzialna za zmiane pozycji rury*/
    public void reposition(float x) {
        posTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube.set(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());
        posBottle.set(x+34, posTopTube.y - TUBE_GAP/2);
        posBag.set(x+34, posTopTube.y - TUBE_GAP/2);

        boundsBottle.setPosition(posBottle.x, posBottle.y);
        boundsBag.setPosition(posBag.x, posBag.y);
        boundsTop.setPosition(posTopTube.x, posTopTube.y);
        boundsBot.setPosition(posBotTube.x, posBotTube.y);
    }

    /**Metoda odpowiedzialna za kolizje gracza z rurami - nakładanie się tekstur */
    public boolean collides(Rectangle player) {
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    /**Metoda odpowiedzialna za kolizje gracza z butelką - nakładanie się tekstur*/
    public boolean collect(Rectangle player) {
        return player.overlaps(boundsBottle) ;
    }
    /**Metoda odpowiedzialna za kolizje gracza z plastikową torbą - nakładanie się tekstur*/
    public boolean collect2(Rectangle player) {
        return player.overlaps(boundsBag) ;
    }

    /**Metoda odpowiedzialna za zwolnienie zasobów aplikacji*/
    public void dispose() {
        topTube.dispose();
        bottle.dispose();
        plasticBag.dispose();
        bottomTube.dispose();
    }
}
