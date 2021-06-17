/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Koneksi;

/**
 *
 * @author User
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Koneksi {
    private String url = "jdbc:mysql://localhost/mydepot_k_one";
    private String uname="root";
    private String passw="";
    
    public Connection getKoneksi() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(url, uname, passw);
    }
    
    public static void main(String[] args) {
        try {
            Koneksi k = new Koneksi();
            k.getKoneksi();
            JOptionPane.showMessageDialog(null, "Koneksi Ok"); 
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error "+ex.getMessage()); 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error "+ex.getMessage()); 
        }
    }

}
