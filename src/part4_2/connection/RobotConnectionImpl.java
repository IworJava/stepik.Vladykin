package part4_2.connection;

public class RobotConnectionImpl implements RobotConnection {
    @Override
    public void moveRobotTo(int x, int y) {
        System.out.printf("Move %d.%d\n", x, y);
    }

    @Override
    public void close() {
        System.out.println("Closed");
    }
}
