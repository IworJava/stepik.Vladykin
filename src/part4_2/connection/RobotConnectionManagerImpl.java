package part4_2.connection;

public class RobotConnectionManagerImpl implements RobotConnectionManager {
    @Override
    public RobotConnection getConnection() {
        return new RobotConnectionImpl();
    }
}
