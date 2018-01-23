package Engine.Updatable.Pieces;

import Engine.Graphics.Animation;
import Engine.Graphics.AnimationMovement;
import Engine.Updatable.Pieces.Tokens.Token;
import Engine.Updatable.Pieces.Tokens.TokenController;
import Engine.Updatable.Rooms.Directions;
import Engine.Updatable.Rooms.RoomController;
import Engine.Updatable.Updatable;

import java.awt.*;
import java.util.ArrayList;

public class Piece extends Updatable {

    public int size = room.TILE_WIDTH*room.SCALE*(room.TILE_WIDTH/8);

    protected int fontWeight = Font.BOLD;
    protected Color color = Color.black;
    protected String font = "Ubuntu Mono";
    protected char icon = 'x';

    protected Animation animation;
    public AnimationMovement direction = AnimationMovement.DOWN;

    protected double speed = 1;
    private ArrayList<Token> near = new ArrayList<>();
    protected boolean immobile = false, passable = false, killable = true, ethereal = false, moving, movingFlag;


    private long lastTime, thisTime;

    private boolean canMove(Directions d, int spaces) {
        boolean out = true;

        if(ethereal)
            return true;
        for(Token t : near) {
            if(!t.passable) {
                /*if ((this.getPixelY() - spaces) <= 0 ||
                        (this.getPixelY() + spaces) >= room.height ||
                        (this.getPixelX() - spaces) <= 0 ||
                        (this.getPixelX() + spaces) >= room.width)
                    out = false;*/
                if(this.getPixelX()+size > t.getPixelX() && this.getPixelX()+size < t.getPixelX()+size ||
                        t.getPixelX()+size > this.getPixelX() && t.getPixelX()+size < this.getPixelX()+size)
                    out = false;
                if(this.getPixelY()+size > t.getPixelY() && this.getPixelY()+size < t.getPixelY()+size ||
                        t.getPixelY()+size > this.getPixelY() && t.getPixelY()+size < this.getPixelY()+size)
                    out = false;
            }
        }

        for(Token t : near) {
            if(!t.passable)
                switch(d) {
                    case UP:
                        if((this.getPosY()-spaces) <= 0 ||
                                ((this.getPosY()-spaces) == t.getPosY() && this.getPosX() == t.getPosX()))
                            out = false;
                        break;
                    case DOWN:
                        if((this.getPosY()+spaces) >= room.height ||
                                ((this.getPosY()+spaces) == t.getPosY() && this.getPosX() == t.getPosX()))
                            out = false;
                        break;
                    case LEFT:
                        if((this.getPosX()-spaces) <= 0 ||
                                ((this.getPosX()-spaces) == t.getPosX() && this.getPosY() == t.getPosY()))
                            out = false;
                        break;
                    case RIGHT:
                        if((this.getPosX()+spaces) >= room.width ||
                                ((this.getPosX()+spaces) == t.getPosX() && this.getPosY() == t.getPosY()))
                            out = false;
                        break;
                }
        }

        return out;
    }

    protected void move(Directions d, int spaces) {
        thisTime = System.nanoTime();

        if(!immobile && thisTime - lastTime >= 500000000/speed) {
            lastTime = thisTime;
            if (canMove(d, spaces)) {
                movingFlag = true;
                moving = true;
                switch (d) {
                    case UP:
                        setPosY(getPosY() - spaces);
                        direction = AnimationMovement.UP;
                        break;
                    case DOWN:
                        setPosY(getPosY() + spaces);
                        direction = AnimationMovement.DOWN;
                        break;
                    case LEFT:
                        setPosX(getPosX() - spaces);
                        direction = AnimationMovement.LEFT;
                        break;
                    case RIGHT:
                        setPosX(getPosX() + spaces);
                        direction = AnimationMovement.RIGHT;
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private long movingTimeThen = System.nanoTime();
    private void animationMoving() {
        long movingTimeNow = System.nanoTime();

        if(movingFlag) {
            movingTimeThen = movingTimeNow;
        }

        if (!movingFlag && movingTimeNow > movingTimeThen + 100000000) {
            movingTimeThen = movingTimeNow;
            moving = false;
        }
    }

    public void setLocation(int x, int y) {
        setPosX(x);
        setPosY(y);
    }

    public boolean getMoving() {
        return moving;
    }

    public ArrayList<Token> getNear() {
        return near;
    }

    @Override
    public void update() {
        room = RoomController.room;

        if(!immobile)
            near = TokenController.searchRange(this, 3);
        if(animation != null)
            animation.update();

        animationMoving();
        movingFlag = false;
    }

    @Override
    public void draw(Graphics2D g) {

    }
}
