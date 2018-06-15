package nl.han.toetsplatform.module.nakijken.data.sql;

import nl.han.toetsplatform.module.nakijken.data.IAntwoord_op_VraagDAO;
import nl.han.toetsplatform.module.nakijken.data.IVraagDAO;
import nl.han.toetsplatform.module.nakijken.data.SQLLoader;
import nl.han.toetsplatform.module.nakijken.model.Antwoord_op_Vraag;
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

public class SQLAntwoordOpVraagDAO implements IAntwoord_op_VraagDAO{
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
    public void saveAntwoorden(Antwoord_op_Vraag antwoord) {
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
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Could not save data to database");
        }
        System.out.println("Done insert antwoord");
    }

    @Override
    public void updateAntwoord(Antwoord_op_Vraag antwoord) {
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
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Could not save data to database");
        }
        System.out.println("Done update antwoord");
    }

    @Override
    public List<Antwoord_op_Vraag> loadAntwoorden(String vraagId, String vraagVersie, String studentNummer, String tentamenCode, String tentamenVersie) {
        Connection conn = _storageDao.getConnection();

        if(!isDatabaseConnected(conn))  return new ArrayList<>();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(_sqlLoader.load("info_antwoord_op_vraag"));
            preparedStatement.setString(1, vraagId);
            preparedStatement.setString(2, vraagVersie);
            preparedStatement.setString(3, studentNummer);
            preparedStatement.setString(4, tentamenCode);
            preparedStatement.setString(5, tentamenVersie);
            ResultSet rs = preparedStatement.executeQuery();
            List<Antwoord_op_Vraag> antwoorden = new ArrayList<>();

            while (rs.next()){
                Antwoord_op_Vraag antwoord = new Antwoord_op_Vraag();
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
            return antwoorden;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Could not load tentamens from database: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}

