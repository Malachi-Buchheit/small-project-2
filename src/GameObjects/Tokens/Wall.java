package GameObjects.Tokens;

import Engine.Updatable.Pieces.Tokens.Token;
import Engine.Updatable.Pieces.Tokens.TokenType;

public class Wall extends Token{

    public Wall(int x, int y) {
        objID = 1;
        type = TokenType.WALL;
        setPosX(x);
        setPosY(y);
        immobile = true;
        killable = false;
    }
}
