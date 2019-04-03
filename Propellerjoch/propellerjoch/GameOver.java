package propellerjoch;

import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.TextObject;
import processing.core.PGraphics;

public class GameOver extends TextObject {

    private String text;

    public GameOver(String text) {
        this.text = text;
    }

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
