/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import Koneksi.Koneksi;
import Model.Pelanggan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class PelangganDao {
    public static void insert(Connection con, Pelanggan pelanggan) throws SQLException{
        System.out.print("PelangganDao");
        String sql = "insert into pelanggan values(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pelanggan.getAlamat_p());
        ps.setString(2, pelanggan.getId_p());
        ps.setString(3, pelanggan.getNama_p());
        ps.setString(4, pelanggan.getNohp_p());
        ps.executeUpdate();
    }
    
        public static void main(String[] args) {
        try {
            Koneksi k = new Koneksi();
            Pelanggan pelanggan = new Pelanggan();
            pelanggan.setAlamat_p("");
            pelanggan.setId_p("");
            pelanggan.setNama_p("");
            pelanggan.setNohp_p("");
            PelangganDao.insert(k.getKoneksi(), pelanggan);
            JOptionPane.showMessageDialog(null, "Insert Ok");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error "+ex.getMessage()); 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error "+ex.getMessage()); 
        }
    }
        
        public static Pelanggan getPelanggan(Connection con, String Id_p) throws SQLException{
        String sql = "Select * from pelanggan where id_p=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, Id_p);
        Pelanggan pelanggan = null;
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            pelanggan = new Pelanggan();
            pelanggan.setAlamat_p(rs.getString(1));
            pelanggan.setId_p(rs.getString(2));
            pelanggan.setNama_p(rs.getString(3));
            pelanggan.setNohp_p(rs.getString(4));
        }
        return pelanggan;
    }
        
        public String getNamaPelanggan(Connection con, String Id_p) throws SQLException{
        String sql = "Select nama_p from pelanggan where id_p=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, Id_p);
        Pelanggan pelanggan = null;
        ResultSet rs = ps.executeQuery();
        String x = "";
        while(rs.next()){
            x = rs.getString(1);
        }
        return x;
    }
        
        public static void update(Connection con, Pelanggan pelanggan) throws SQLException{
        String sql = "update pelanggan set alamat_p=?, nama_p=?, nohp_p=? "
                + "where id_p=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pelanggan.getAlamat_p());
        ps.setString(2, pelanggan.getId_p());
        ps.setString(3, pelanggan.getNama_p());
        ps.setString(4, pelanggan.getNohp_p());
        ps.executeUpdate();
    }
        
        public static void delete(Connection con, Pelanggan pelanggan) throws SQLException{
        String sql = "delete from pelanggan where id_p=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pelanggan.getId_p());
        ps.executeUpdate();
    }
}