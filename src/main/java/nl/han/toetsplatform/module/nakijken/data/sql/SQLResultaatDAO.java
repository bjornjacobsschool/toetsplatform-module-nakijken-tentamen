package nl.han.toetsplatform.module.nakijken.data.sql;

import nl.han.toetsplatform.module.nakijken.data.IResultaatDAO;
import nl.han.toetsplatform.module.nakijken.data.IVraagDAO;
import nl.han.toetsplatform.module.nakijken.data.SQLLoader;
import nl.han.toetsplatform.module.nakijken.model.Resultaat;
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

public class SQLResultaatDAO implements IResultaatDAO {
    private final static Logger LOGGER = Logger.getLogger(SQLTentamenDAO.class.getName());

    private StorageDao _storageDao;

    private SQLLoader _sqlLoader;


    @Inject
    public SQLResultaatDAO(StorageDao storageDao, SQLLoader sqlLoader, IVraagDAO vragenDao) {
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
    public void saveResultaten(Resultaat resultaat) {
        Connection conn = _storageDao.getConnection();
        if(!isDatabaseConnected(conn))  return;

        try {
            PreparedStatement psResultaat = conn.prepareStatement(_sqlLoader.load("insert_RESULTAAT"));
            psResultaat.setInt(1, resultaat.getStudentNummer());
            psResultaat.setString(2, resultaat.getTentamenCode());
            psResultaat.setString(3, resultaat.getTentamenVersie());
            psResultaat.setFloat(4, resultaat.getCijfer());
            psResultaat.setString(5, resultaat.getHash());
            psResultaat.execute();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Could not save data to database");
        }
        System.out.println("Done insert resultaat");
    }

    @Override
    public void updateResultaat(Resultaat resultaat) {
        Connection conn = _storageDao.getConnection();
        if(!isDatabaseConnected(conn))  return;

        try {
            PreparedStatement psResultaat = conn.prepareStatement(_sqlLoader.load("update_RESULTAAT"));
            psResultaat.setInt(1, resultaat.getStudentNummer());
            psResultaat.setString(2, resultaat.getTentamenCode());
            psResultaat.setString(3, resultaat.getTentamenVersie());
            psResultaat.setFloat(4, resultaat.getCijfer());
            psResultaat.setString(5, resultaat.getHash());
            psResultaat.setInt(6, resultaat.getStudentNummer());
            psResultaat.setString(7, resultaat.getTentamenCode());
            psResultaat.setInt(8, resultaat.getStudentNummer());
            psResultaat.execute();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Could not save data to database");
        }
        System.out.println("Done updating resultaat");
    }

    @Override
    public List<Resultaat> loadResultaten(String studentnummer, String tentamencode, String tentamenversie) {
        Connection conn = _storageDao.getConnection();

        if(!isDatabaseConnected(conn))  return new ArrayList<>();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(_sqlLoader.load("info_resultaat"));
            preparedStatement.setString(1, studentnummer);
            preparedStatement.setString(2, tentamencode);
            preparedStatement.setString(3, tentamenversie);
            ResultSet rs = preparedStatement.executeQuery();
            List<Resultaat> resultaten = new ArrayList<>();

            while (rs.next()){
                Resultaat resultaat = new Resultaat();
                resultaat.setStudentNummer(rs.getInt("studentnummer"));
                resultaat.setTentamenCode(rs.getString("tentamencode"));
                resultaat.setTentamenVersie(rs.getString("tentamenversie"));
                resultaat.setCijfer(rs.getFloat("cijfer"));
                resultaat.setHash(rs.getString("hash"));

                resultaten.add(resultaat);
            }
            return resultaten;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Could not load tentamens from database: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}

