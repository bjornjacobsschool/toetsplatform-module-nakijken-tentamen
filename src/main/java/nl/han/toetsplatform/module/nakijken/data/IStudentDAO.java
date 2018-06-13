package nl.han.toetsplatform.module.nakijken.data;

import nl.han.toetsplatform.module.nakijken.model.Student;

import java.util.List;

public interface IStudentDAO {

    //void saveResultaten(Student tentamen);

    List<Student> loadStudenten();
}
