package nl.han.toetsplatform.module.nakijken.data.data;

import nl.han.toetsplatform.module.nakijken.model.Student;

import java.util.List;

public interface IStudentDAO {

    void saveStudent(Student student);

    List<Student> loadStudenten(int studentnummer, String klas);
}
