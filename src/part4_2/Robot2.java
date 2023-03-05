package part4_2;

import part4_2.connection.RobotConnection;
import part4_2.connection.RobotConnectionException;
import part4_2.connection.RobotConnectionManager;
import part4_2.connection.RobotConnectionManagerImpl;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

public class Robot2 {
    public static final Logger LOGGER = Logger.getLogger(Robot2.class.getName());

    public static void main(String[] args) {
        loggerConfig();
        moveRobot(new RobotConnectionManagerImpl(), 1, 1);
    }

    public static void moveRobot(RobotConnectionManager robotConnectionManager, int toX, int toY) {
        LOGGER.info("moveRobot() starts");

        for (int i = 0; i < 3; i++) {
            try (RobotConnection connection = robotConnectionManager.getConnection()) {
                LOGGER.log(Level.FINE, "Attempt {0}", i + 1);
                System.out.println("Try " + (i + 1));

                switch (i) {
                    case 0 -> throw new RobotConnectionException("1");
                    case 1 -> throw new RobotConnectionException("2");
                    // case 2 -> throw new RobotConnectionException("3");
                }

                connection.moveRobotTo(toX, toY);
                i = 3;
            } catch (RobotConnectionException e) {
                LOGGER.log(Level.WARNING, "Connection has been broken");

                if (i == 2) {
                    throw e;
                }
            }
        }
        LOGGER.log(Level.INFO, "moveRobot() finishes successfully");
    }

    private static void loggerConfig() {
        Formatter formatter = new XMLFormatter();

        Handler handler;
        try {
            handler = new FileHandler("logger.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        handler.setFormatter(formatter);
        handler.setLevel(Level.ALL);

        LOGGER.setLevel(Level.ALL);
        LOGGER.setUseParentHandlers(false);
        LOGGER.addHandler(handler);
    }
}
