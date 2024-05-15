/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author PC
 */
public class ProductDAO {

    Connection con = null;

    PreparedStatement pst = null;

    ResultSet rs = null;

    public List<Product> searchProductByName(String searchValue) throws SQLException {
        List<Product> list = new ArrayList<>();
        try {
            con = DBUtils.getConnection();

            String sql = "select id , name , description, price, size, status\n"
                    + "from tblProduct\n"
                    + "where description like ? and status = 1";

            pst = con.prepareStatement(sql);
            pst.setString(1, "%" + searchValue + "%");
            rs = pst.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                float price = rs.getFloat("price");
                String size = rs.getString("size");
                boolean status = rs.getBoolean("status");

                list.add(new Product(id, name, description, price, size, status));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public int deleteProductById(String id) {
        int rowsAffected = 0;

        try {
            con = DBUtils.getConnection();

            String sql = "UPDATE tblProduct SET status = 0 WHERE id = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, id);
            rowsAffected = pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle or throw the exception as needed.
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle or throw the exception as needed.
            }

            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle or throw the exception as needed.
            }
        }

        return rowsAffected;
    }

    public Product searchById(String id) throws SQLException{
      try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select id , name , description, price, size, status\n"
                    + "from tblProduct\n"
                    + "where id = ? and status = 1";
                pst = con.prepareStatement(sql);
                pst.setString(1, id);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String itemsid = rs.getString("id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    String size = rs.getString("size");
                    boolean status = rs.getBoolean("status");

                    return new Product(id, name, description, price, size, status);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }
    
   public boolean removeProductById(String id) {
        boolean result = false;

        try (Connection con = DBUtils.getConnection();
             PreparedStatement pst = con.prepareStatement("DELETE FROM tblProduct WHERE id = ?")) {

            pst.setString(1, id);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                result = true;
            }

        } catch (SQLException e) {
            
            e.printStackTrace();
        }

        return result;
    }
}
