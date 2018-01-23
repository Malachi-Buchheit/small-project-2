package Engine.Updatable.Pieces.Tokens;

import Engine.Updatable.Rooms.Directions;
import Engine.Updatable.Updatable;
import GameObjects.Tokens.Player;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class TokenController {

    private static ArrayList<Token> allTokens = new ArrayList<>();
    private static Player player;

    public static void addToken(Token t) {
        int things = searchRange(t, 1).size();
        if(t.getType() == TokenType.PLAYER)
            player = (Player)t;
        if(things == 0 || (things == 1 && searchRange(t, 1).get(0).type == TokenType.BUILDER)) {
            allTokens.add(t);
        }
    }

    public static void forceAddToken(Token t) {
        if(t.getType() == TokenType.PLAYER)
            player = (Player)t;
        allTokens.add(t);
    }

    public static void removeToken(Token t) {
        allTokens.remove(t);
    }

    public static void updateTokens() {
        for(int i = 0; i < allTokens.size(); i++) {
            allTokens.get(i).update();
        }
    }

    public static void drawTokens(BufferStrategy bs) {
        for(int i = 0; i < allTokens.size(); i++) {
            Graphics2D g = (Graphics2D) bs.getDrawGraphics();

            allTokens.get(i).draw(g);
            g.dispose();
        }
    }

    public static void placePlayer(int x, int y) {
        player.setLocation(x, y);
    }

    public static ArrayList<Token> searchRange(Updatable self, int range) {
        ArrayList<Token> out = new ArrayList<>();

        for(Token t : TokenController.allTokens) {
            if(t != self && Math.abs(self.getPosX()-t.getPosX()) < range && Math.abs(self.getPosY()-t.getPosY()) < range) {
                out.add(t);
            }
        }
        return out;
    }

    public static int distanceBetween(Token token, Token token2) {
        return (int)Math.sqrt(Math.pow(Math.abs(token.getPosX() - token2.getPosX()), 2) + Math.pow(Math.abs(token.getPosY() - token2.getPosY()), 2));
    }

    public static Token findToken(ArrayList<Token> list, long id) {
        for(Token t : list) {
            if(t.getId() == id)
                return t;
        }

        return null;
    }

    public static ArrayList<Token> searchType(TokenType type){
        ArrayList<Token> out = new ArrayList<>();

        for(Token t : allTokens) {
            if(t.getType() == type)
                out.add(t);
        }
        return out;
    }

    public static void moveToken(Token t, Directions d) {
        t.move(d, 1);
    }

    public static ArrayList<Token> getAllTokens() {
        return allTokens;
    }

    public static Player getPlayer() {
        return player;
    }
}
