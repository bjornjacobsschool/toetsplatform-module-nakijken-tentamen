package nl.han.toetsplatform.module.nakijken.data.sql;


import nl.han.toetsplatform.module.nakijken.data.IVraagDAO;
import nl.han.toetsplatform.module.nakijken.data.SQLLoader;
import nl.han.toetsplatform.module.nakijken.model.Tentamen;
import nl.han.toetsplatform.module.nakijken.model.Vraag;
import nl.han.toetsplatform.module.shared.storage.StorageDao;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLVraagDAO implements IVraagDAO {

    private final static Logger LOGGER = Logger.getLogger(SQLVraagDAO.class.getName());

    private SQLLoader _sqlLoader;

    private StorageDao _storageDao;

    @Inject
    public SQLVraagDAO(SQLLoader _sqlLoader, StorageDao _storageDao) {
        this._sqlLoader = _sqlLoader;
        this._storageDao = _storageDao;

    }

    private boolean isDatabaseConnected(Connection connection){
        if (connection == null) {
            LOGGER.log(Level.WARNING, "No database connected");
            return false;
        }

        return true;
    }


    @Override
    public List<Vraag> loadVragen(String vraagId, String vraagVersie, String themaNaam) {
        Connection conn = _storageDao.getConnection();

        if(!isDatabaseConnected(conn))  return new ArrayList<>();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(_sqlLoader.load("info_tentamen"));
            preparedStatement.setString(1, vraagId);
            preparedStatement.setString(2, vraagVersie);
            preparedStatement.setString(3, themaNaam);
            ResultSet rs = preparedStatement.executeQuery();
            List<Vraag> vragen = new ArrayList<>();

            while (rs.next()){
                Vraag vraag = new Vraag();
                vraag.setVraagId(rs.getString("vraagid"));
                vraag.setVraagVersie(rs.getString("vraagversie"));
                vraag.setThema(rs.getString("themanaam"));
                vraag.setNakijkModelVersie(rs.getString("nakijkmodelversie"));
                vraag.setVraagStelling(rs.getString("vraagstelling"));
                vraag.setVraagPlugin(rs.getString("vraagplugin"));
                vraag.setVraagOefenvraag(rs.getBoolean("vraagoefenvraag"));
                vraag.setVraagGemaaktDoor(rs.getString("vraaggemaaktdoor"));
                vraag.setVraagVersieDatum(rs.getTimestamp("vraagversiedatum"));
                vraag.setVraagVersieOmschrijving(rs.getString("vraagversieomschrijving"));

                vragen.add(vraag);
            }
            return vragen;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Could not load tentamens from database: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}

