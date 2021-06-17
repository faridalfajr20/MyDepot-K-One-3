/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExportExcel;

/**
 *
 * @author Kuuhaku
 */

import ExportExcel.FormExport;
import Koneksi.Koneksi;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;


public class ExportData {
    FormExport view;
    Connection con;
    
    public ExportData(FormExport view) {
         try {
            this.view = view;
            Koneksi k= new Koneksi();
            con = k.getKoneksi();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ExportData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ExportData.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }

    public void karyawan(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mydepot_k_one","root","");
            Statement statement = con.createStatement();
            FileOutputStream fileOut;
            fileOut = new FileOutputStream("C:\\MyDepot K'One\\MyDepot K'One\\Laporan_Pesanan\\karyawan.xls");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet("Sheet 0");
            Row row1 = worksheet.createRow((short)0);
            row1.createCell(0).setCellValue("id_p") ;
            row1.createCell(1).setCellValue("id_k");
            row1.createCell(2).setCellValue("nama_p");
            row1.createCell(3).setCellValue("jmlpesanan");
            row1.createCell(4).setCellValue("tglpesanan");
            row1.createCell(5).setCellValue("diskon");
            row1.createCell(6).setCellValue("voucher");
            row1.createCell(7).setCellValue("biaya");
            
            Row row2 ;
            ResultSet rs1 = statement.executeQuery("SELECT id_k, jmlpesanan, diskon, voucher, tglpesanan, biaya FROM memesan ");
            
            ResultSet rs2 = statement.executeQuery("SELECT id_p, nama_p, nohp_p,alamat_p FROM pelanggan ");
           
            while(rs1.next() && rs2.next()){
                int a = rs1.getRow();
                int b = rs2.getRow();
                row2 = worksheet.createRow((short)a);
                row2.createCell(0).setCellValue(rs2.getString(1));
                row2.createCell(1).setCellValue(rs1.getString(2));
                row2.createCell(2).setCellValue(rs2.getString(3));
                row2.createCell(3).setCellValue(rs1.getString(4));
                row2.createCell(4).setCellValue(rs1.getString(5));
                row2.createCell(5).setCellValue(rs1.getString(6));
                row2.createCell(6).setCellValue(rs1.getString(7));
                row2.createCell(7).setCellValue(rs1.getString(8));
                
                }
                workbook.write(fileOut);
                fileOut.flush();
                fileOut.close();
                rs1.close();
                rs2.close();
                statement.close();
                con.close();
                JOptionPane.showMessageDialog(null,"Export Berhasi! File Tersimpan pada Folder Laporan_Pesanan");
            }catch(ClassNotFoundException e){
                System.out.println(e);
            }catch(SQLException ex){
                System.out.println(ex);
            }catch(IOException ioe){
                System.out.println(ioe);
        }JOptionPane.showMessageDialog(null,"TERIMAKASIH!");
    }
    
    public void memesan(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mydepot_k_one","root","");
            Statement statement = con.createStatement();
            FileOutputStream fileOut;
            fileOut = new FileOutputStream("C:\\MyDepot K'One\\MyDepot K'One\\Laporan_Pesanan\\pelanggan.xls");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet("Sheet 0");
            Row row1 = worksheet.createRow((short)0);
            row1.createCell(0).setCellValue("id_p") ;
            row1.createCell(1).setCellValue("nama_p");
            row1.createCell(2).setCellValue("alamat_p");
            row1.createCell(3).setCellValue("nohp_p");
            row1.createCell(4).setCellValue("jmlpesanan");
            row1.createCell(5).setCellValue("biaya");
            row1.createCell(6).setCellValue("keterangan_k");
            row1.createCell(7).setCellValue("id_k");
            Row row2 ;
            ResultSet rs1 = statement.executeQuery("SELECT id_p, nama_p,alamat_p, nohp_k FROM pelanggan ");
            
            ResultSet rs2 = statement.executeQuery("SELECT id_k, keterangan_k FROM karyawan ");
            
            ResultSet rs3 = statement.executeQuery("SELECT jmlpesanan, biaya FROM memesan ");
            
            
           
            while(rs1.next() && rs2.next() && rs3.next()){
                int a = rs1.getRow();
                int b = rs2.getRow();
                int c = rs3.getRow();
                row2 = worksheet.createRow((short)a);
                row2.createCell(0).setCellValue(rs1.getString(1));
                row2.createCell(1).setCellValue(rs1.getString(2));
                row2.createCell(2).setCellValue(rs1.getString(3));
                row2.createCell(3).setCellValue(rs1.getString(4));
                row2.createCell(4).setCellValue(rs3.getString(5));
                row2.createCell(5).setCellValue(rs3.getString(6));
                row2.createCell(6).setCellValue(rs2.getString(7));
                row2.createCell(7).setCellValue(rs2.getString(8));
                
                }
                workbook.write(fileOut);
                fileOut.flush();
                fileOut.close();
                rs1.close();
                rs2.close();
                rs3.close();
                statement.close();
                con.close();
                JOptionPane.showMessageDialog(null,"Export Berhasi! File Tersimpan pada Folder Laporan_Pesanan");
            }catch(ClassNotFoundException e){
                System.out.println(e);
            }catch(SQLException ex){
                System.out.println(ex);
            }catch(IOException ioe){
                System.out.println(ioe);
        }JOptionPane.showMessageDialog(null,"TERIMAKASIH!");
    }
    
    
    public void viewTable1(){
        try {
            DefaultTableModel tabelModel =
                    (DefaultTableModel) view.getTabelDB().getModel();
            tabelModel.setRowCount(0);
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mydepot_k_one","root","");
            Statement statement = con.createStatement();
            ResultSet rs1 = statement.executeQuery("SELECT * FROM karyawan");
            
            ResultSet rs2 = statement.executeQuery("SELECT * FROM memesan");
            while(rs1.next() && rs2.next()){
                Object[] data={
                    rs2.getString(1),
                    rs1.getString(2),
                    rs2.getString(3),
                    rs1.getString(4),
                    rs1.getString(5),
                    rs1.getString(6),
                    rs1.getString(7),
                    rs1.getString(8)
                    
                };
                tabelModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExportData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void viewTable2(){
        try {
            DefaultTableModel tabelModel =
                    (DefaultTableModel) view.getTabelDB().getModel();
            tabelModel.setRowCount(0);
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mydepot_k_one","root","");
            Statement statement = con.createStatement();
            ResultSet rs1 = statement.executeQuery("SELECT * FROM pelanggan");
            ResultSet rs2 = statement.executeQuery("SELECT * FROM karyawan");
            ResultSet rs3 = statement.executeQuery("SELECT * FROM memesan");
            while(rs1.next() && rs2.next() && rs3.next()){
                Object[] data={
                    rs1.getString(1),
                    rs1.getString(2),
                    rs1.getString(3),
                    rs1.getString(4),
                    rs3.getString(5),
                    rs3.getString(6),
                    rs2.getString(7),
                    rs2.getString(8)
                };
                tabelModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExportData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
