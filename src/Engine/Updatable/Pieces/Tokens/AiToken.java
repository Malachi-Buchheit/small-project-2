package Engine.Updatable.Pieces.Tokens;

import Engine.Updatable.Ai.Ai;

/**
 * Created by m on 5/22/17.
 */
public class AiToken extends Token {
    protected Ai ai;

    @Override
    public void update() {
        super.update();
        ai.update();
    }
}
