package nl.han.toetsplatform.module.nakijken.data.sql;

import nl.han.toetsplatform.module.nakijken.data.IVraagDAO;
import nl.han.toetsplatform.module.nakijken.data.IVraag_van_TentamenDAO;
import nl.han.toetsplatform.module.nakijken.data.SQLLoader;
import nl.han.toetsplatform.module.nakijken.model.Antwoord_op_Vraag;
import nl.han.toetsplatform.module.nakijken.model.Vraag_van_Tentamen;
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

public class SQLVraagVanTentamenDAO implements IVraag_van_TentamenDAO {
    private final static Logger LOGGER = Logger.getLogger(SQLTentamenDAO.class.getName());

    private StorageDao _storageDao;

    private SQLLoader _sqlLoader;


    @Inject
    public SQLVraagVanTentamenDAO(StorageDao storageDao, SQLLoader sqlLoader, IVraagDAO vragenDao) {
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
    public List<Vraag_van_Tentamen> loadTentamenVragen(String tentamenCode, String tentamenVersie, String vraagId, String vraagVersie) {
        Connection conn = _storageDao.getConnection();

        if(!isDatabaseConnected(conn))  return new ArrayList<>();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(_sqlLoader.load("info_vraag_van_tentamen"));
            preparedStatement.setString(1, tentamenCode);
            preparedStatement.setString(2, tentamenVersie);
            preparedStatement.setString(3, vraagId);
            preparedStatement.setString(4, vraagVersie);
            ResultSet rs = preparedStatement.executeQuery();
            List<Vraag_van_Tentamen> vragen = new ArrayList<>();

            while (rs.next()){
                Vraag_van_Tentamen vraag = new Vraag_van_Tentamen();
                vraag.setTentamenVersie(rs.getString("tentamenversie"));
                vraag.setVraagId(rs.getString("vraagid"));
                vraag.setVraagVersie(rs.getString("vraagversie"));
                vraag.setAantalPunten(rs.getFloat("aantalpunten"));

                vragen.add(vraag);
            }
            return vragen;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Could not load tentamens from database: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
