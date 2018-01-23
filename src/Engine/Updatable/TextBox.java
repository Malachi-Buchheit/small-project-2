package Engine.Updatable;

import Engine.Updatable.Pieces.Tokens.Token;

import java.awt.*;

/**
 * Created by m on 5/22/17.
 */
public class TextBox extends Updatable {

    private String[] string;
    private char[][] letters;

    private int boxWidth = 0, boxHeight = 0;
    private int fontSize = 12, fontSpeed = 1, fontWeight = Font.PLAIN, charPosition = 0, stringPosition = 0, timespan = 0;
    private Color fontColor, backgroundColor = Color.white;
    private Font font;
    private long startTime, checkTime;
    private Token token;

    public TextBox(Token t, String[] string, Color color, int timespan) {
        super.updatableType = UpdatableTypes.TEXT_BOX;
        startTime = System.nanoTime()/100000000;

        this.token = t;
        this.timespan = timespan;
        setPosX(token.getPosX());
        setPosY(token.getPosY());
        this.string = string;
        this.fontColor = color;
        createBox();
    }

    public TextBox(int x, int y, Token t, String[] string, Color color, int timespan) {
        super.updatableType = UpdatableTypes.TEXT_BOX;
        startTime = System.nanoTime()/100000000;

        this.token = t;
        this.timespan = timespan;
        setPosX(x);
        setPosY(y);
        this.string = string;
        this.fontColor = color;
        createBox();
    }

    private void createBox() {
        letters = new char[string.length][];

        boxHeight = 50;
        boxWidth  = 170;
        font = new Font("Mono", fontWeight, fontSize);
        splitString();
    }

    private void splitString() {
        for(int j = 0; j < string.length; j++) {
            letters[j] = new char[string[j].length()];

            for(int i = 0; i < string[j].length(); i++) {
                letters[j][i] = string[j].charAt(i);
            }
        }
    }

    private void writeMessage(Graphics2D g) {
        for(int j = 0; j < letters.length; j++) {
            for(int i = 0; i < letters[j].length; i++) {
                if(i <= charPosition)
                    g.setColor(fontColor);
                    g.drawString(letters[j][i]+"", getPixelX()+5+i*(fontSize - 2), getPixelY()+15+12*j);
            }
        }
    }

    long timeLast = System.nanoTime()/100000000;
    @Override
    public void update() {
        long timeThis = System.nanoTime()/100000000;
        checkTime = System.nanoTime()/100000000;

        setPixelX(token.getPixelX()-(boxWidth/2));
        setPixelY(token.getPixelY()-boxHeight*3/2);

        if(checkTime > startTime+timespan) {
            destroy();
        }

        if(timeThis >= timeLast+(10/fontSpeed)) {
            timeLast = timeThis;
            if(stringPosition >= letters.length)
                stringPosition = 0;
                //destroy();
            else if(charPosition >= letters[stringPosition].length) {
                charPosition = 0;
                stringPosition++;
            }

        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(backgroundColor);
        g.fillRect(getPixelX(), getPixelY(), boxWidth, boxHeight);
        writeMessage(g);
    }

    @Override
    public void destroy() {
        UpdateController.remove(this);
    }
}