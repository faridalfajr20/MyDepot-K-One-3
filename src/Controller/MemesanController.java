/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import Dao.MemesanDao;
import Dao.PelangganDao;
import Form.Form_CRUD;
import Form.Form_Caridata;
import java.lang.Double;
import Form.Form_Input;
import Koneksi.Koneksi;
import Model.Memesan;
import Model.Pelanggan;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RessM
 */

public class MemesanController {


    Form_Input view;
    Form_Caridata viewc;
    Form_CRUD viewd;
    Memesan memesan;
    Pelanggan pelanggan;
    MemesanDao memesandao;
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    
    public MemesanController(Form_Input view) {
        try {
            this.view = view;
            Koneksi k= new Koneksi();
            con = k.getKoneksi();
            clearForm();
            isiCbo_IdGalonman();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KaryawanController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KaryawanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public MemesanController(Form_Caridata view) {
        try {
            this.viewc = view;
            Koneksi k= new Koneksi();
            con = k.getKoneksi();
            viewTable();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KaryawanController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KaryawanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateMemesan() {
        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
        memesan = new Memesan();
        pelanggan = new Pelanggan();
        memesan.setId_p(viewd.getTxt_idpelanggan().getText());
        pelanggan.setNama_p(viewd.getTxt_namapelanggan().getText());
        pelanggan.setAlamat_p(viewd.getTxt_alamat().getText());
        pelanggan.setNohp_p(viewd.getTxt_nohppelanggan().getText());
        memesan.setJmlpesanan(Integer.parseInt(viewd.getTxt_pesanan().getText()));
        memesan.setBiaya(Double.parseDouble(viewd.getTxt_biaya().getText()));
        memesan.setNama_k(viewd.getTxt_namagalonman().getText());
        //memesan.setId_k();
        memesan.setId_air("1");
        memesan.setTglpesanan(sqlDate);
        System.out.print(viewd.getTxt_namagalonman().getText());
        try {
            MemesanDao.update(con, memesan);
            PelangganDao.update(con, pelanggan);
            JOptionPane.showMessageDialog(view, "Data berhasil Diperbaharui");
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(view, "Error "+ex.getMessage()); 
        }
    }
    
    public void deleteMemesan() {
        try {
            MemesanDao.update(con, memesan);
            PelangganDao.update(con, pelanggan);
            JOptionPane.showMessageDialog(viewd, "Data Sudah di Hapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(viewd, "Error " +e);
        }
    }
    
    public void onClickTabelKandang() {
        try {
            String kode = viewd.getTable_Edit().getValueAt(viewd.getTable_Edit().getSelectedRow(), 0).toString();
            kandang = KandangDao.getKandang(con, kode);
            if (kandang != null) {
                viewd.getTxtNamaKandang().setText(kandang.getNamaKandang());
                viewd.getTxtJumlahTernak().setText(""+kandang.getJmlTernak());
            } else {
                javax.swing.JOptionPane.showMessageDialog(viewAdmin, "Data Tidak Ada");
                clearFormKandang();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewTable(){
       try {
            DefaultTableModel tabelModel = 
                    (DefaultTableModel) viewc.getTable().getModel();
            tabelModel.setRowCount(0);
            ResultSet m = con.createStatement().executeQuery("select * from memesan");
            PelangganDao pelanggandao = new PelangganDao();
            
            //pelanggandao.getNamaPelanggan(con,);
            //ResultSet p = con.createStatement().executeQuery("select * from pelanggan");
            //ResultSet k = con.createStatement().executeQuery("select * from karyawan");
            //while(m.next() && p.next() && k.next()){
            while(m.next()){ 
                //String dateStr = dateFormat.format(m.getDate(7));
                //java.sql.Date date = m.getDate(7);
                //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                //String dateStr = dateFormat.format(date);
                Object[] data={
                    m.getInt(1),
                    m.getString(2),
                    m.getString(3),
                    pelanggandao.getNamaPelanggan(con,m.getString(4)),
                    m.getString(5),
                    m.getInt(6),
                    m.getInt(9),
                    m.getString(10),
                    m.getString(2),
                    m.getDate(7),
                    m.getDouble(2)
                };
                tabelModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MemesanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void search(String x){
       try {
            DefaultTableModel tabelModel = 
                    (DefaultTableModel) viewc.getTable().getModel();
            tabelModel.setRowCount(0);
            String sql = "select * from memesan where id_p = ?";
            //ResultSet m = con.createStatement().executeQuery("select * from memesan where nopesanan = ?");
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, x);
            ResultSet m = ps.executeQuery();
            //ResultSet p = con.createStatement().executeQuery("select * from pelanggan");
            //ResultSet k = con.createStatement().executeQuery("select * from karyawan");
            //while(m.next() && p.next() && k.next()){
            while(m.next()){ 
                Object[] data={
                    m.getInt(1),
                    m.getString(2),
                    m.getString(3),
                    m.getString(4),
                    m.getString(5),
                    m.getInt(6),
                    m.getInt(9),
                    m.getString(10),
                    m.getString(2),
                    m.getDate(7),
                    m.getDouble(8)
                };
                tabelModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MemesanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void viewTableInputKandang(){
        try {
            DefaultTableModel tabelModel = (DefaultTableModel) viewAdmin.getTblInputDataKandang().getModel();
            tabelModel.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("select * from kandang");
            while(rs.next()){
                Object[] data={
                    rs.getString(1),
                    rs.getInt(2)+ " Ekor"
                };
                tabelModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void clearForm(){
        view.getTxt_idpelanggan().setText("");
        view.getTxt_namapelanggan().setText("");
        view.getTxt_alamat().setText("");
        view.getTxt_nohppelanggan().setText("");
        //view.getTxt_pesanan().setInteger.("");
        //view.getTxt_biaya().setDouble("");
        view.getTxt_namagalonman().setText("");
        
    }
    
    public void Tes(){
        System.out.print("tes");
    }
    public void isiComboK(){
        view.getCbo_idgalonman().removeAllItems();
        view.getCbo_idgalonman().addItem("");
        view.getCbo_idgalonman().addItem("");
    }
    
    public boolean NohpCheck(){
        try{
            String nohp = view.getTxt_nohppelanggan().getText();
            memesan = memesandao.getMemesan(con,nohp);
            if(memesan == null) return true;
        }catch (SQLException ex) {
            Logger.getLogger(KaryawanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void Kirim(){
        System.out.print("Kirim");
        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
        memesan = new Memesan();
        pelanggan = new Pelanggan();
        memesan.setId_p(view.getTxt_idpelanggan().getText());
        pelanggan.setNama_p(view.getTxt_namapelanggan().getText());
        pelanggan.setAlamat_p(view.getTxt_alamat().getText());
        pelanggan.setNohp_p(view.getTxt_nohppelanggan().getText());
        pelanggan.setId_p("1");
        memesan.setJmlpesanan(Integer.parseInt(view.getTxt_pesanan().getText()));
        memesan.setBiaya(Double.parseDouble(view.getTxt_biaya().getText()));
        memesan.setNama_k(view.getTxt_namagalonman().getText());
        memesan.setId_k("Hasibuan Ganteng");
        memesan.setId_air("1");
        memesan.setTglpesanan(sqlDate);
        System.out.print(view.getTxt_namagalonman().getText());
        try {
            MemesanDao.insert(con, memesan);
            PelangganDao.insert(con, pelanggan);
            JOptionPane.showMessageDialog(view, "Entri Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error "+ex.getMessage()); 
        }
    }
    public void isiCbo_IdGalonman(){
        view.getCbo_idgalonman().removeAllItems();
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from karyawan");
            while (rs.next()) {                
                view.getCbo_idgalonman().addItem(rs.getString(1)+"-"+rs.getString(2)); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(MemesanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getIdgalonman(){
        String[] s = view.getCbo_idgalonman().getSelectedItem().toString().split("-");
        return s[0];
    }
}