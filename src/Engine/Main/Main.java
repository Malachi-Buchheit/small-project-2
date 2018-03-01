package Engine.Main;

import Engine.Updatable.Pieces.Builder.BuildController;
import Engine.Updatable.Rooms.RoomController;
import Engine.Updatable.Pieces.Tokens.TokenController;
import Engine.Updatable.UpdateController;

import java.awt.image.BufferStrategy;
import java.io.File;
import java.util.Scanner;

public class Main {

    public static int fps = 30;

    static MyWindow window;
    private static BufferStrategy bs;
    private static boolean running = true, paused = false, building = false;
    public static boolean debug = true;

    public static void main(String[] args) {
        createWindow();

        if(!debug) {
            FileManager.load(FileManager.saveOne);
        }
        else {
            File folder = new File("saves");
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    System.out.println("" + listOfFiles[i].getName());
                }
            }

            Scanner scan = new Scanner(System.in);
            FileManager.load(new File("saves/" + scan.nextLine()));
        }

        loop(fps);
    }

    private static void update() {
        if(building)
            BuildController.update();
        else
            UpdateController.updateAll();
    }

    private static void render() {
        if(building)
            BuildController.draw(bs);
        else
            UpdateController.drawAll(bs);
    }

    private static void loop(double delta) {
        long thisTime;
        long lastTime = System.nanoTime();

        while(running) {
            thisTime = System.nanoTime();

            if((thisTime - lastTime) >= ((1000000000/delta))) {
                lastTime = thisTime;
                render();

                if(!paused) {
                    update();
                }
            }
        }
    }

    private static void createWindow() {
        window = new MyWindow(800, 600);
        bs = window.getBufferStrategy();
    }

    public static void pause() {
        paused = true;
    }

    public static void unpause() {
        paused = false;
    }

    public static void start() {
        running = true;
    }

    public static void stop() {
        running = false;
    }

    static long buildingTime = System.nanoTime()/100000000;
    public static void setBuilding(boolean building) {
        long timeNow = System.nanoTime()/100000000;

        if(timeNow > buildingTime+10) {
            buildingTime = timeNow;

            if (Main.building) {
                TokenController.placePlayer(BuildController.getBuilder().getPosX(), BuildController.getBuilder().getPosY());
                BuildController.removeBuilder();
            } else {
                BuildController.newBuilder();
                TokenController.placePlayer(-RoomController.room.width, -RoomController.room.height);
            }
            Main.building = building;
        }
    }

    public static boolean getBuilding() {
        return building;
    }

    public static MyWindow getWindow() {
        return window;
    }
}
