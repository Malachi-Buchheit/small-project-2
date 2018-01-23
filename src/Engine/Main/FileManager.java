package Engine.Main;

import Engine.Updatable.Pieces.Tokens.Token;
import Engine.Updatable.Pieces.Tokens.TokenController;
import Engine.Updatable.Pieces.Tokens.TokenType;
import Engine.Updatable.UpdateController;
import GameObjects.Tokens.Player;
import GameObjects.Tokens.Wall;
import GameObjects.Tokens.Zombie;

import java.io.*;

public class FileManager {

    public static final File saveOne = new File("saves/save1.dat");

    public static void save(File file) {
        try(DataOutputStream out = new DataOutputStream(new FileOutputStream(file))) {
            for(Token t : TokenController.getAllTokens()) {
                if(t.getType() == TokenType.PLAYER) {
                    out.write(0);
                    out.writeInt(t.getPosX());
                    out.writeInt(t.getPosY());
                }
                else {
                    out.write(t.objID);
                    out.writeInt(t.getPosX());
                    out.writeInt(t.getPosY());
                }
            }
            out.close();
        } catch(IOException e) {
            UpdateController.addToken(new Player(2, 2));
        }
    }

    public static void load(File file) {
        try(DataInputStream in = new DataInputStream(new FileInputStream(file))) {
            while(in.available() > 0) {
                switch(in.read()) {
                    case 0:
                        UpdateController.addToken(new Player(in.readInt(), in.readInt()));
                        break;
                    case 1:
                        UpdateController.addToken(new Wall(in.readInt(), in.readInt()));
                        break;
                    case 2:
                        UpdateController.addToken(new Zombie(in.readInt(), in.readInt()));
                        break;
                }
            }
            in.close();
        } catch(IOException e) {

        }
    }
}
