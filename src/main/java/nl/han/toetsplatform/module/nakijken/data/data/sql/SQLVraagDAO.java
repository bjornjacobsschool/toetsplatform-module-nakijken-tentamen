package nl.han.toetsplatform.module.nakijken.data.data.sql;



import nl.han.toetsplatform.module.nakijken.data.data.IVraagDAO;
import nl.han.toetsplatform.module.nakijken.data.data.SQLLoader;
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
    public void saveVraag(Vraag vraag) {
        Connection conn = _storageDao.getConnection();
        if(!isDatabaseConnected(conn))  return;

        try {
            PreparedStatement psResultaat = conn.prepareStatement(_sqlLoader.load("insert_VRAAG"));
            psResultaat.setString(1, vraag.getVraagId());
            psResultaat.setString(2, vraag.getVraagVersie());
            psResultaat.setString(3, vraag.getVraagNaam());
            psResultaat.setString(4, vraag.getVraagType());
            psResultaat.setString(5, vraag.getVraagThema());
            psResultaat.setString(6, vraag.getVraagCorrecteAntwoorden());
            psResultaat.setString(7, vraag.getVraagNakijkInstructies());
            psResultaat.setString(8, vraag.getNakijkModelVersie());
            psResultaat.setString(9, vraag.getVraagStelling());
            psResultaat.setTimestamp(10, vraag.getVraagVersieDatum());
            psResultaat.setString(11, vraag.getVraagVersieOmschrijving());

            psResultaat.execute();
            psResultaat.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Could not save data to database");
        }
        System.out.println("Done insert vraag");
    }



    @Override
    public List<Vraag> loadVragen(String vraagId, String vraagVersie, String themaNaam) {
        Connection conn = _storageDao.getConnection();

        if(!isDatabaseConnected(conn))  return new ArrayList<>();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(_sqlLoader.load("info_vraag"));
            preparedStatement.setString(1, vraagId);
            preparedStatement.setString(2, vraagVersie);
            preparedStatement.setString(3, themaNaam);
            ResultSet rs = preparedStatement.executeQuery();
            List<Vraag> vragen = new ArrayList<>();

            while (rs.next()){
                Vraag vraag = new Vraag();
                vraag.setVraagId(rs.getString("vraagid"));
                vraag.setVraagVersie(rs.getString("vraagversie"));
                vraag.setVraagNaam(rs.getString("vraagnaam"));
                vraag.setVraagType(rs.getString("vraagtype"));
                vraag.setVraagThema(rs.getString("vraagthema"));
                vraag.setVraagCorrecteAntwoorden(rs.getString("vraagcorrecteantwoorden"));
                vraag.setVraagNakijkInstructies(rs.getString("vraagnakijkinstructies"));
                vraag.setNakijkModelVersie(rs.getString("nakijkmodelversie"));
                vraag.setVraagStelling(rs.getString("vraagstelling"));
                vraag.setVraagVersieDatum(rs.getTimestamp("vraagversiedatum"));
                vraag.setVraagVersieOmschrijving(rs.getString("vraagversieomschrijving"));

                vragen.add(vraag);
            }
            rs.close();
            return vragen;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Could not load tentamens from database: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}

