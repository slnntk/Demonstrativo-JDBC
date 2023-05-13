package model.dao.impl;

import db.DB;
import model.DepartmentDAO;
import model.entities.Department;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class DepartmentDAOJDBC implements DepartmentDAO{

    private Connection conn;

    public DepartmentDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "INSERT INTO department "
                    +"(Name)"
                    +"VALUES "
                    +"(?)",
                    Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getName());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }

    @Override
    public void update(Department obj) {

    }

    @Override
    public void deleteById(Integer id) {

        PreparedStatement st = null;

        try{

            st = conn.prepareStatement(
                    "DELETE FROM department "
                    +"WHERE "
                    +"Id = ?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }

    @Override
    public Department findById(Integer id) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try{

            st = conn.prepareStatement(
                    "SELECT * FROM department "
                    +"WHERE department.Id = ?");
            st.setInt(1, id);
            st.executeQuery();
            rs = st.getResultSet();
            while (rs.next()){
                return instantiateDepartment(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            DB.closeConnection();
        }
        return null;
    }

    @Override
    public Set<Department> findAll() {

        Statement st = null;
        ResultSet rs = null;
        Set<Department> departments = new HashSet<>();

        try{
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM department");
            while (rs.next()){
                departments.add(instantiateDepartment(rs));
            }
            return departments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Department instantiateDepartment(ResultSet rs) {

        Department department = new Department();
        try {
            department.setId(rs.getInt("Id"));
            department.setName(rs.getString("Name"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return department;
    }
}
