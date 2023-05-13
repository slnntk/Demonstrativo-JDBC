package model;

import model.entities.Department;

import java.sql.ResultSet;
import java.util.Set;

public interface DepartmentDAO {


    void insert(Department obj);
    void update(Department obj);
    void deleteById(Integer id);
    Department findById(Integer id);
    Set<Department> findAll();
    Department instantiateDepartment(ResultSet rs);

}
