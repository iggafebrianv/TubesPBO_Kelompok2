/*
Topik   : Sekolah
Anggota : Arpriansah Yonathan (1301194112)
          Balqis Sayyidahtul Atikah (1301193480)
          Igga Febrian Virgiani (1301194283)
          Manuel Benedict (1301194182)
 */
package Model;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class admin {
    public void addKelas(String kelas, String nama_siswa, int nis, String[] mapel, Database dbsisfo){
        try {
            dbsisfo.connect() ;
            String sql = "INSERT INTO kelas VALUES ('"
                    +nis+"','"
                    +nama_siswa+"','"
                    +kelas+"','"
                    +mapel+"')";
            dbsisfo.setRs(dbsisfo.getStmt().executeQuery(sql));
            dbsisfo.disconnect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void deleteKelas(String kelas, Database dbsisfo){
        try{
            dbsisfo.connect();
            String sql = "DELETE FROM kelas WHERE id = '" + kelas +"'";
            dbsisfo.setRs(dbsisfo.getStmt().executeQuery(sql));
            dbsisfo.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}