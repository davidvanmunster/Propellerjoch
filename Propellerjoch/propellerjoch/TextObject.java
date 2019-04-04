package propellerjoch;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class TextObject extends GameObject {

    private String text;

    /**
     * Constructor
     * 
     * @param text	- De meegegeven tekst
     */
    public TextObject(String text) {
        this.text = text;
    }

    /**
     * Verandert de tekst.
     * @param text 
     */
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics g) {
        g.textAlign(g.CENTER, g.CENTER);
        g.textSize(50);
        g.text(text, getX(), getY());
    }
}
