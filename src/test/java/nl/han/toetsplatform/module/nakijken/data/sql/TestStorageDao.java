package nl.han.toetsplatform.module.nakijken.data.sql;


        import nl.han.toetsplatform.module.nakijken.data.data.stub.StubStorageDao;

        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.SQLException;

public class TestStorageDao extends StubStorageDao {

    private Connection connection;

    @Override
    public Connection getConnection() {
        try {
            if (connection == null)
            connection = DriverManager.getConnection("jdbc:sqlite::memory:");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}