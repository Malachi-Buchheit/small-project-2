package Engine.Updatable.Ai;

import Engine.Updatable.Pieces.Tokens.AiToken;

/**
 * Created by m on 5/22/17.
 */
public abstract class Ai {
    protected AiToken token;

    public abstract void update();
}
