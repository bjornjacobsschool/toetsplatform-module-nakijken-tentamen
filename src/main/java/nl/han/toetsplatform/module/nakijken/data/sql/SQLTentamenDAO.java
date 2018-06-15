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



    @Inject
    public SQLTentamenDAO(StorageDao storageDao, SQLLoader sqlLoader) {
        this._storageDao = storageDao;
        this._sqlLoader = sqlLoader;
    }

    private boolean isDatabaseConnected(Connection connection){
        if (connection == null) {
            LOGGER.log(Level.WARNING, "No database connected");
            return false;
        }

        return true;
    }

    @Override
    public void saveTentamen(Tentamen tentamen) {
        Connection conn = _storageDao.getConnection();
        if(!isDatabaseConnected(conn))  return;

        try {
            PreparedStatement psResultaat = conn.prepareStatement(_sqlLoader.load("insert_TENTAMEN"));
            psResultaat.setString(1, tentamen.getTentamenCode());
            psResultaat.setString(2, tentamen.getTentamenVersie());
            psResultaat.setString(3, tentamen.getTentamenNaam());
            psResultaat.setString(4, tentamen.getTentamenTijdsduur());
            psResultaat.setDate(5, tentamen.getTentamenKlaarzetDatum());
            psResultaat.setTimestamp(6, tentamen.getTentamenVersieDatum());
            psResultaat.setString(7, tentamen.getTentamenVersieOmschrijving());

            psResultaat.execute();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Could not save data to database");
        }
        System.out.println("Done insert nakijkmodel");    }

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
                tentamen.setTentamenNaam(rs.getString("tentamennaam"));
                tentamen.setTentamenTijdsduur(rs.getString("tentamentijdsduur"));
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
