/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBUtils;

/**
 *
 * @author PC
 */
public class UserDAO {

    Connection con = null;

    PreparedStatement pst = null;

    ResultSet rs = null;

    public User checkLoginByRole(String username, String password) throws SQLException {
        try {
            con = DBUtils.getConnection();

            String sql = "select userID, fullName, password, roleID, status \n"
                    + "from tblUsers\n"
                    + "where userid = ? and password = ?";

            pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);

            rs = pst.executeQuery();

            if (rs.next()) {
                String userid = rs.getString("userID");
                String fullname = rs.getString("fullName");
                String pw = rs.getString("password");
                String roleid = rs.getString("roleID");
                boolean status = rs.getBoolean("status");

                return new User(userid, fullname, password, roleid, status);
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
        return null;
    }

}
