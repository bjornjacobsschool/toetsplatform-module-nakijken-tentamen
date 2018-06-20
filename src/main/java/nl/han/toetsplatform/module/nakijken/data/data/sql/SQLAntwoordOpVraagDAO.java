package nl.han.toetsplatform.module.nakijken.data.data.sql;


import nl.han.toetsplatform.module.nakijken.data.data.IAntwoordOpVraagDAO;
import nl.han.toetsplatform.module.nakijken.data.data.SQLLoader;
import nl.han.toetsplatform.module.nakijken.model.AntwoordOpVraag;
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

public class SQLAntwoordOpVraagDAO implements IAntwoordOpVraagDAO {
    private final static Logger LOGGER = Logger.getLogger(SQLTentamenDAO.class.getName());

    private StorageDao _storageDao;

    private SQLLoader _sqlLoader;


    @Inject
    public SQLAntwoordOpVraagDAO(StorageDao storageDao, SQLLoader sqlLoader) {
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
    public void saveAntwoorden(AntwoordOpVraag antwoord) {
        Connection conn = _storageDao.getConnection();
        if(!isDatabaseConnected(conn))  return;

        try {
            PreparedStatement psResultaat = conn.prepareStatement(_sqlLoader.load("insert_ANTWOORD_op_VRAAG"));
            psResultaat.setString(1, antwoord.getVraagId());
            psResultaat.setString(2, antwoord.getVraagVersie());
            psResultaat.setInt(3, antwoord.getStudentNummer());
            psResultaat.setString(4, antwoord.getTentamenCode());
            psResultaat.setString(5, antwoord.getTentamenVersie());
            psResultaat.setString(6, antwoord.getAntwoord());
            psResultaat.setFloat(7, antwoord.getBehaaldePunten());
            psResultaat.setString(8, antwoord.getNakijkComment());
            psResultaat.execute();
            psResultaat.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Could not save data to database");
        }
        System.out.println("Done insert antwoord");
    }

    @Override
    public void updateAntwoord(AntwoordOpVraag antwoord) {
        Connection conn = _storageDao.getConnection();
        if(!isDatabaseConnected(conn))  return;

        try {
            PreparedStatement psResultaat = conn.prepareStatement(_sqlLoader.load("update_ANTWOORD_op_VRAAG"));
            psResultaat.setString(1, antwoord.getVraagId());
            psResultaat.setString(2, antwoord.getVraagVersie());
            psResultaat.setInt(3, antwoord.getStudentNummer());
            psResultaat.setString(4, antwoord.getTentamenCode());
            psResultaat.setString(5, antwoord.getTentamenVersie());
            psResultaat.setString(6, antwoord.getAntwoord());
            psResultaat.setFloat(7, antwoord.getBehaaldePunten());
            psResultaat.setString(8, antwoord.getNakijkComment());
            psResultaat.setString(9, antwoord.getVraagId());
            psResultaat.setString(10, antwoord.getVraagVersie());
            psResultaat.setInt(11, antwoord.getStudentNummer());
            psResultaat.setString(12, antwoord.getTentamenCode());
            psResultaat.setString(13, antwoord.getTentamenVersie());

            psResultaat.execute();
            psResultaat.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Could not save data to database");
        }
        System.out.println("Done update antwoord");
    }

    @Override
    public List<AntwoordOpVraag> loadAntwoorden(String vraagId, String vraagVersie, String tentamenCode, String tentamenVersie) {
        Connection conn = _storageDao.getConnection();

        if(!isDatabaseConnected(conn))  return new ArrayList<>();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(_sqlLoader.load("info_antwoord_op_vraag"));
            preparedStatement.setString(1, "%" + vraagId + "%");
            preparedStatement.setString(2, "%" +vraagVersie + "%");
            preparedStatement.setString(3, "%" + tentamenCode + "%");
            preparedStatement.setString(4, "%" + tentamenVersie  + "%");
            ResultSet rs = preparedStatement.executeQuery();
            List<AntwoordOpVraag> antwoorden = new ArrayList<>();

            while (rs.next()){
                AntwoordOpVraag antwoord = new AntwoordOpVraag();
                antwoord.setVraagId(rs.getString("vraagid"));
                antwoord.setVraagVersie(rs.getString("vraagversie"));
                antwoord.setStudentNummer(rs.getInt("studentnummer"));
                antwoord.setTentamenCode(rs.getString("tentamencode"));
                antwoord.setTentamenVersie(rs.getString("tentamenversie"));
                antwoord.setAntwoord(rs.getString("antwoord"));
                antwoord.setBehaaldePunten(rs.getInt("behaaldepunten"));
                antwoord.setNakijkComment(rs.getString("nakijkcomment"));

                antwoorden.add(antwoord);
            }
            rs.close();
            return antwoorden;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Could not load tentamens from database: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public List<AntwoordOpVraag> loadAllAntwoorden() {
        Connection conn = _storageDao.getConnection();

        if(!isDatabaseConnected(conn))  return new ArrayList<>();

        try {
            ResultSet rs =  conn.createStatement().executeQuery(_sqlLoader.load("select_antwoord_op_vraag"));
       // PreparedStatement preparedStatement = conn.prepareStatement(_sqlLoader.load("select_antwoord_op_vraag"));
//            ResultSet rs = preparedStatement.executeQuery();


            List<AntwoordOpVraag> antwoorden = new ArrayList<>();
            while (rs.next()){
                AntwoordOpVraag antwoord = new AntwoordOpVraag();
                antwoord.setVraagId(rs.getString("vraagid"));
                antwoord.setVraagVersie(rs.getString("vraagversie"));
                antwoord.setStudentNummer(rs.getInt("studentnummer"));
                antwoord.setTentamenCode(rs.getString("tentamencode"));
                antwoord.setTentamenVersie(rs.getString("tentamenversie"));
                antwoord.setAntwoord(rs.getString("antwoord"));
                antwoord.setBehaaldePunten(rs.getInt("behaaldepunten"));
                antwoord.setNakijkComment(rs.getString("nakijkcomment"));

                antwoorden.add(antwoord);
            }
            rs.close();
            return antwoorden;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Could not load tentamens from database: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}

