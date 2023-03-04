package part4_2;

import part4_2.connection.RobotConnection;
import part4_2.connection.RobotConnectionException;
import part4_2.connection.RobotConnectionManager;
import part4_2.connection.RobotConnectionManagerImpl;

public class Robot2 {
    public static void main(String[] args) {
        moveRobot(new RobotConnectionManagerImpl(), 1, 1);
    }

    public static void moveRobot(RobotConnectionManager robotConnectionManager, int toX, int toY) {
        for (int i = 0; i < 3; i++) {
            try (RobotConnection connection = robotConnectionManager.getConnection()) {
                System.out.println("try " + (i + 1));
                switch (i) {
                    case 0 -> throw new RobotConnectionException("1");
                    case 1 -> throw new RobotConnectionException("2");
                    // case 2 -> throw new RobotConnectionException("3");
                }

                connection.moveRobotTo(toX, toY);
                i = 3;
            } catch (RobotConnectionException e) {
                if (i == 2) {
                    throw e;
                }
            }
        }
    }
}
