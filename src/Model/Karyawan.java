package Model;

/**
 *
 * @author GF63-622
 */
public class Karyawan {
    String id_k;
    String password;
    String keterangan;
    String nama_k;
    String nohp_k;
    
    public String getId_k() {
        return id_k;
    }
    
    public void setId_k(String id_k) {
        this.id_k = id_k;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
     public String getKeterangan() {
        return keterangan;
    }
    
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
    
    public String getNama_k() {
        return nama_k;
    }
    
    public void setNama_k(String nama_k) {
        this.nama_k = nama_k;
    }
    
    public String getNohp_k() {
        return nohp_k;
    }
    
    public void setNohp_k(String nohp_k) {
        this.nohp_k = nohp_k;
    }
}