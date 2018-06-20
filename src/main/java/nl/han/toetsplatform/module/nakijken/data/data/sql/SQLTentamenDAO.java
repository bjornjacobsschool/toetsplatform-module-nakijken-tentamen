package nl.han.toetsplatform.module.nakijken.data.data.sql;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nl.han.toetsapplicatie.apimodels.dto.UitgevoerdTentamenDto;
import nl.han.toetsplatform.module.nakijken.data.data.ITentamenDAO;
import nl.han.toetsplatform.module.nakijken.data.data.SQLLoader;
import nl.han.toetsplatform.module.nakijken.data.data.dto_model_mapper.DatabaseMapper;
import nl.han.toetsplatform.module.nakijken.exceptions.GatewayCommunicationException;
import nl.han.toetsplatform.module.nakijken.model.Tentamen;
import nl.han.toetsplatform.module.nakijken.serviceagent.GatewayServiceAgent;
import nl.han.toetsplatform.module.shared.storage.StorageDao;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLTentamenDAO implements ITentamenDAO {
    private final static Logger LOGGER = Logger.getLogger(SQLTentamenDAO.class.getName());

    private StorageDao _storageDao;

    private SQLLoader _sqlLoader;

    private GatewayServiceAgent localGateway = new GatewayServiceAgent();

    private DatabaseMapper _myMapper = new DatabaseMapper();




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
        System.out.println("Done insert nakijkmodel");
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
                tentamen.setTentamenNaam(rs.getString("tentamennaam"));
                tentamen.setTentamenTijdsduur(rs.getString("tentamentijdsduur"));
                tentamen.setTentamenKlaarzetDatum(rs.getDate("tentamenklaarzetdatum"));
                tentamen.setTentamenVersieDatum(rs.getTimestamp("tentamenversiedatum"));
                tentamen.setTentamenVersieOmschrijving(rs.getString("tentamenversieomschrijving"));

                tentamens.add(tentamen);
            }
            rs.close();
            return tentamens;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Could not load tentamens from database: " + e.getMessage());
        }
        return new ArrayList<>();
    }

//    public  ObservableList<Tentamen> getUitgevoerdeTentamens(StorageDao _storageDao, SQLLoader _sqlLoader) throws GatewayCommunicationException {
//        List<Tentamen> uitgevoerdeTentamens = loadTentamens("%", "%") ;
//        SQLAntwoordOpVraagDAO _ingevuldTentamen = new SQLAntwoordOpVraagDAO(_storageDao, _sqlLoader);
//        try {
//            List<UitgevoerdTentamenDto> serverVragen = Arrays.asList(localGateway.get("GET/tentamens/uitgevoerd", UitgevoerdTentamenDto.class));
//
//            for (UitgevoerdTentamenDto sV : serverVragen) {
//                boolean exists = false;
//                for (Tentamen localTentamen : uitgevoerdeTentamens) {
//                    if (sV.getId().toString().equals(localTentamen.getTentamenCode())) {
//                        exists = true;
//                        break;
//                    }
//                }
//                if (!exists) {
//                    LOGGER.log(Level.INFO, "Nieuwe tentamen van server: " + sV.getId().toString());
//                    _myMapper.fillDatabase(sV);
//                }
//            }
//        } catch (GatewayCommunicationException e) {
//            LOGGER.log(Level.WARNING, "Could not connect to gatewary: " + e.getMessage());
//        }
//        List<Tentamen> tempTentamens = loadTentamens("%", "%");
//        ObservableList<Tentamen> tentamenListData = FXCollections.observableArrayList();
//
//
//        for(Tentamen currenttentamen : tempTentamens){
//            if(_ingevuldTentamen.loadAntwoorden("%", "%", "%", currenttentamen.getTentamenCode(), "%").size() != 0) {
//                tentamenListData.add(currenttentamen);
//            }
//        }
//
//        return tentamenListData ;
//    }
}
