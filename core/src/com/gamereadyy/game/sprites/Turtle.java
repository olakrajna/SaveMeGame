package com.gamereadyy.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * @author Aleksandra Krajna
 */
/** Klasa tworząca żółwika*/

 public class Turtle {
    /**
     grawitacja
     */
    private static final int gravity = -20;
    /**szybkośc poruszania się*/
    private static final int MOVEMENT =100;
    /**pozycja żółwika*/
    private Vector3 position;
    /**prędkość żółwika*/
    private Vector3 velocity;
    /**utorzenie granic tekstury zółwika*/
    private Rectangle bounds;
    /**animacja zółwika - jego ruch*/
    private Animation turtleAnimation;
    private Texture texture;


    /**
     * Konstruktor w którym tworzymy obiekty
     * @param x - pozycja żółwika na osi OX
     * @param y - pozycja żółwika na osi OY
     */
    public Turtle(int x, int y) {
        /**
         * Utworzenie obiektów i nadanie wartości
         */
        position = new Vector3(x,y, 0);
        velocity = new Vector3(0,0,0);
        texture = new Texture("turtleanimation2.png");
        turtleAnimation = new Animation(new TextureRegion(texture), 2,0.5f);
        bounds = new Rectangle(x,y,texture.getWidth()/2, texture.getHeight());
    }

    /**
     * aktualizowanie stanu gru
     * @param dt - czas określający zależność między upływem czasu rzeczywistego a stanem gry
     */
    public void update(float dt) {
        turtleAnimation.update(dt);
        /**nadajemy prędkość i zmieniamy pozycje żółwika*/
        if(position.y > 0) {
            velocity.add(0, gravity, 0);
        }
        /**skalujemy prędkość w zależności od czasu*/
        velocity.scl(dt);

        position.add(MOVEMENT * dt, velocity.y, 0);
        /**ustawienie minimalnej pozycji na osi Y żółwika*/
        if(position.y < 0) {
            position.y = 0;
        }
        velocity.scl(1/dt);
        bounds.setPosition(position.x , position.y);
    }

    /**metody zwracające wartości*/
    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return turtleAnimation.getFrame();
    }

    public Rectangle getBounds() {
        return bounds;
    }

    /**metoda odpowiedzialna za podskakiwanie żółwika*/
    public void jump() {
        velocity.y = 300;
    }

    /**metoda odpowiedzialna za zwolnienie zasobów aplikacji*/
    public void dispose() {
        texture.dispose();
    }

}
