package nl.han.toetsplatform.module.nakijken.data.sql;

import nl.han.toetsplatform.module.nakijken.data.INakijkmodelDAO;
import nl.han.toetsplatform.module.nakijken.data.SQLLoader;
import nl.han.toetsplatform.module.nakijken.model.NakijkModel;
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

public class SQLNakijkModelDAO implements INakijkmodelDAO {
    private final static Logger LOGGER = Logger.getLogger(SQLVraagDAO.class.getName());

    private SQLLoader _sqlLoader;

    private StorageDao _storageDao;

    @Inject
    public SQLNakijkModelDAO(SQLLoader _sqlLoader, StorageDao _storageDao) {
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
    public List<NakijkModel> loadNakijkmodel(String nakijkModelVersie, String vraagId, String vraagVersie) {
        Connection conn = _storageDao.getConnection();

        if(!isDatabaseConnected(conn))  return new ArrayList<>();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(_sqlLoader.load("info_nakijkmodel"));
            preparedStatement.setString(1, nakijkModelVersie);
            preparedStatement.setString(2, vraagId);
            preparedStatement.setString(3, vraagVersie);
            ResultSet rs = preparedStatement.executeQuery();
            List<NakijkModel> nakijkModel = new ArrayList<>();

            while (rs.next()){
                NakijkModel model = new NakijkModel();
                model.setNakijkModelVersie(rs.getString("nakijkmodelversie"));
                model.setVraagId(rs.getString("vraagid"));
                model.setVraagVersie(rs.getString("vraagversie"));
                model.setNakijkModelVersie(rs.getString("correcteantwoorden"));
                model.setNakijkInstrucites(rs.getString("nakijkinstructies"));

                nakijkModel.add(model);
            }
            return nakijkModel;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Could not load tentamens from database: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
