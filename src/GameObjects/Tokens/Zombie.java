package GameObjects.Tokens;

import Engine.Updatable.Ai.EnemyMelee;
import Engine.Updatable.Combat.Weapon;
import Engine.Updatable.Pieces.Tokens.AiToken;

import java.awt.*;

/**
 * Created by m on 5/22/17.
 */
public class Zombie extends AiToken {

    public Zombie(int x, int y) {
        weapon = new Weapon(this, 1, 1, 10, 10);
        icon = 'z';
        color = new Color(40, 200, 40);
        objID = 2;
        setPosX(x);
        setPosY(y);
        ai = new EnemyMelee(this);

        //say(this, new String[]{"Greetings"}, color, 60);
    }

    @Override
    public void update() {
        super.update();
        ai.update();
    }
}
