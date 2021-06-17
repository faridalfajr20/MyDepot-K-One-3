/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import Koneksi.Koneksi;
import Model.Airgalon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author user
 */
public class AirgalonDao {
    public static void insert(Connection con, Airgalon airgalon) throws SQLException{
        String sql = "insert into airgalon values(?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, airgalon.getId_air());
        ps.setString(2, airgalon.getKeterangan_air());
        ps.executeUpdate();
    }
    
    public static void main(String[] args) {
        try {
            Koneksi k = new Koneksi();
            Airgalon airgalon = new Airgalon();
            airgalon.setId_air("");
            airgalon.setKeterangan_air("");
            AirgalonDao.insert(k.getKoneksi(), airgalon);
            JOptionPane.showMessageDialog(null, "Insert Ok");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error "+ex.getMessage()); 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error "+ex.getMessage()); 
        }
    }
    
    public static Airgalon getAirgalon(Connection con, String Id_air) throws SQLException{
        String sql = "Select * from airgalon where id_air=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, Id_air);
        Airgalon airgalon = null;
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            airgalon = new Airgalon();
            airgalon.setId_air(rs.getString(1));
            airgalon.setKeterangan_air(rs.getString(2));
        }
        return airgalon;
    }
    
    public static void update(Connection con, Airgalon airgalon) throws SQLException{
        String sql = "update airgalon set keterangan_air=? "
                + "where id_air=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, airgalon.getId_air());
        ps.setString(2, airgalon.getKeterangan_air());
        ps.executeUpdate();
    }
    
    public static void delete(Connection con, Airgalon airgalon) throws SQLException{
        String sql = "delete from airgalon where id_air=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, airgalon.getId_air());
        ps.executeUpdate();
    }
}
