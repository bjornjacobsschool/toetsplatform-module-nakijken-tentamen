package nl.han.toetsplatform.module.nakijken.data.data.sql;


import nl.han.toetsplatform.module.nakijken.data.data.IStudentDAO;
import nl.han.toetsplatform.module.nakijken.data.data.SQLLoader;
import nl.han.toetsplatform.module.nakijken.model.Student;
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

public class SQLStudentDAO implements IStudentDAO {
    private final static Logger LOGGER = Logger.getLogger(SQLTentamenDAO.class.getName());

    private StorageDao _storageDao;

    private SQLLoader _sqlLoader;




    @Inject
    public SQLStudentDAO(StorageDao storageDao, SQLLoader sqlLoader) {
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
    public void saveStudent(Student student) {
        Connection conn = _storageDao.getConnection();
        if(!isDatabaseConnected(conn))  return;

        try {
            PreparedStatement psResultaat = conn.prepareStatement(_sqlLoader.load("insert_STUDENT"));
            psResultaat.setInt(1, student.getStudentNummer());
            psResultaat.setString(2, student.getKlas());
            psResultaat.execute();
            psResultaat.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Could not save data to database");
        }
        System.out.println("Done insert student");
    }

    @Override
    public List<Student> loadStudenten(int studentummer, String klas) {
        Connection conn = _storageDao.getConnection();

        if(!isDatabaseConnected(conn))  return new ArrayList<>();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(_sqlLoader.load("info_student"));
            preparedStatement.setInt(1, studentummer);
            preparedStatement.setString(2, klas);
            ResultSet rs = preparedStatement.executeQuery();
            List<Student> studenten = new ArrayList<>();

            while (rs.next()){
                Student student = new Student();
                student.setStudentNummer(rs.getInt("studentnummer"));
                student.setKlas(rs.getString("klas"));


                studenten.add(student);
            }
            rs.close();
            return studenten;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Could not load tentamens from database: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
