package Engine.Updatable.Ai;

import Engine.Updatable.Rooms.Directions;
import Engine.Updatable.Pieces.Tokens.AiToken;
import Engine.Updatable.Pieces.Tokens.Token;
import Engine.Updatable.Pieces.Tokens.TokenController;
import Engine.Updatable.Pieces.Tokens.TokenType;

/**
 * Created by m on 5/22/17.
 */
public class EnemyMelee extends Ai{

    protected int range;

    public EnemyMelee(AiToken t) {
        token = t;
        range = 12;
    }

    @Override
    public void update() {
        if(TokenController.distanceBetween(token, TokenController.getPlayer()) <= 1)
            attack(TokenController.getPlayer());
        search();
    }

    private void attack(Token t) {
        if(Math.abs(t.getPosX() - token.getPosX()) > Math.abs(t.getPosY() - token.getPosY())) {
            if (t.getPosX() > token.getPosX())
                token.attack(Directions.RIGHT);
            if (t.getPosX() < token.getPosX())
                token.attack(Directions.LEFT);
        }
        else {
            if (t.getPosY() > token.getPosY())
                token.attack(Directions.DOWN);
            if (t.getPosY() < token.getPosY())
                token.attack(Directions.UP);
        }
    }

    private void search() {
        for(Token t : TokenController.searchRange(token, range)) {
            if(t.getType() == TokenType.PLAYER)
                chase(t);
            break;
        }
    }

    private void chase(Token t) {
        if(Math.abs(t.getPosX() - token.getPosX()) > Math.abs(t.getPosY() - token.getPosY())) {
            if (t.getPosX() > token.getPosX())
                TokenController.moveToken(token, Directions.RIGHT);
            if (t.getPosX() < token.getPosX())
                TokenController.moveToken(token, Directions.LEFT);
        }
        else {
            if (t.getPosY() > token.getPosY())
                TokenController.moveToken(token, Directions.DOWN);
            if (t.getPosY() < token.getPosY())
                TokenController.moveToken(token, Directions.UP);
        }
    }
}
