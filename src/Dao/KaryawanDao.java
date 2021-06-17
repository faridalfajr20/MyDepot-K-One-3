package Dao;

/**
 *
 * @author GF63-622
 */

import Koneksi.Koneksi;
import Model.Karyawan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class KaryawanDao {
     public static void insert(Connection con, Karyawan karyawan) throws SQLException{
        String sql = "insert into karyawan values(?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, karyawan.getId_k());
        ps.setString(2, karyawan.getPassword());
        ps.setString(3, karyawan.getKeterangan());
        ps.setString(4, karyawan.getNama_k());
        ps.setString(5, karyawan.getNohp_k());
        ps.executeUpdate();
    }
    
       
     public static Karyawan getKaryawan(Connection con, String Id_k) throws SQLException{
        String sql = "Select * from karyawan where id_k=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, Id_k);
        Karyawan karyawan = null;
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            karyawan = new Karyawan();
            karyawan.setId_k(rs.getString(1));
            karyawan.setPassword(rs.getString(2));
            karyawan.setKeterangan(rs.getString(3));
            karyawan.setNama_k(rs.getString(4));
            karyawan.setNohp_k(rs.getString(5));
        }
        return karyawan;
    }
    
    public static void update(Connection con, Karyawan karyawan) throws SQLException{
        String sql = "update karyawan set password=?, keterangan=?, nama_k=?, nohp_k=? "
                + "where id_k=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, karyawan.getId_k());
        ps.setString(2, karyawan.getPassword());
        ps.setString(3, karyawan.getKeterangan());
        ps.setString(4, karyawan.getNama_k());
        ps.setString(5, karyawan.getNohp_k());
        ps.executeUpdate();
    }
    
    public static void delete(Connection con, Karyawan karyawan) throws SQLException{
        String sql = "delete from karyawan where id_k=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, karyawan.getId_k());
        ps.executeUpdate();
    }
}
