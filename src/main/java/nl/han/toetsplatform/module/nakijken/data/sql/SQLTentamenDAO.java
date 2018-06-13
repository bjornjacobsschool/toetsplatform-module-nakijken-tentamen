package nl.han.toetsplatform.module.nakijken.data.sql;

import nl.han.toetsplatform.module.nakijken.data.ITentamenDAO;
import nl.han.toetsplatform.module.nakijken.data.IVraagDAO;
import nl.han.toetsplatform.module.nakijken.data.SQLLoader;
import nl.han.toetsplatform.module.nakijken.model.Tentamen;
import nl.han.toetsplatform.module.shared.storage.StorageDao;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLTentamenDAO implements ITentamenDAO {
    private final static Logger LOGGER = Logger.getLogger(SQLTentamenDAO.class.getName());

    private StorageDao _storageDao;

    private SQLLoader _sqlLoader;

    private IVraagDAO _vragenDao;


    @Inject
    public SQLTentamenDAO(StorageDao storageDao, SQLLoader sqlLoader, IVraagDAO vragenDao) {
        this._storageDao = storageDao;
        this._sqlLoader = sqlLoader;
        this._vragenDao = vragenDao;
    }

    private boolean isDatabaseConnected(Connection connection){
        if (connection == null) {
            LOGGER.log(Level.WARNING, "No database connected");
            return false;
        }

        return true;
    }
    @Override
    public List<Tentamen> loadTentamens(String tentamenCode, String tentamenVersie) {
        Connection conn = _storageDao.getConnection();

        if(!isDatabaseConnected(conn))  return new ArrayList<>();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(_sqlLoader.load("info_tentamen"));
            preparedStatement.setString(1, tentamenCode);
            preparedStatement.setString(2, tentamenVersie);
            ResultSet rs = preparedStatement.executeQuery();
            List<Tentamen> tentamens = new ArrayList<>();

            while (rs.next()){
                Tentamen tentamen = new Tentamen();
                tentamen.setTentamenCode(rs.getString("tentamencode"));
                tentamen.setTentamenVersie(rs.getString("tentamenversie"));
                tentamen.setTentamenMomentId(rs.getString("tentamenmomentid"));
                tentamen.setTentamenNaam(rs.getString("tentamennaam"));
                tentamen.setTentamenTijdsduur(rs.getTime("tentamentijdsduur"));
                tentamen.setTentamenKlaarzetDatum(rs.getDate("tentamenklaarzetdatum"));
                tentamen.setTentamenVersieDatum(rs.getTimestamp("tentamenversiedatum"));
                tentamen.setTentamenVersieOmschrijving(rs.getString("tentamenversieomschrijving"));

                tentamens.add(tentamen);
            }
            return tentamens;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Could not load tentamens from database: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
