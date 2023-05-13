package application;

import db.DB;
import model.dao.impl.DepartmentDAOJDBC;
import model.entities.Department;

import java.sql.Connection;

public class Program {

    public static void main(String[] args) {

        Connection conn = DB.getConnection();
        DepartmentDAOJDBC depart = new DepartmentDAOJDBC(conn);
        System.out.println(depart.findAll());
    }
}
