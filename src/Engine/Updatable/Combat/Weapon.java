package Engine.Updatable.Combat;

import Engine.Updatable.Pieces.Tokens.Token;

/**
 * Created by m on 5/25/17.
 */
public class Weapon {
    public int damage, duration, durability, cooldown;
    Token t;

    public Weapon(Token t, int damage, int duration, int durability, int cooldown) {
        this.t = t;
        this.damage = damage;
        this.durability = durability;
        this.duration = duration;
        this.cooldown = cooldown;
    }
}
