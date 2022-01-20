/*
Topik   : Sekolah
Anggota : Arpriansah Yonathan (1301194112)
          Balqis Sayyidahtul Atikah (1301193480)
          Igga Febrian Virgiani (1301194283)
          Manuel Benedict (1301194182)
 */
package Model;

import Model.siswa ;
import java.sql.*;
import java.util.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import javax.swing.JList;
import GUI.guiSiswa;

public class Database {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dbsisfo";
    private static final String USER = "root";
    private static final String PASS = "";
    
    private static Connection conn;
    private static Statement stmt;
    private static ResultSet rs;
    private String sql;
    
    private ArrayList<kelas> listKelas = new ArrayList();
    private ArrayList<siswa> listSiswa = new ArrayList();
    private ArrayList<matapelajaran> listMapel = new ArrayList();
    private guiSiswa viewSiswa;
    
    public Database() {
        loadSiswa();
        loadNilai();
        loadKelas();
    }
    
    
    public void connect() throws SQLException, ClassNotFoundException{
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
        stmt = conn.createStatement();
    }
    
    public void disconnect() throws SQLException {
        stmt.close();
        conn.close();
    }
    
    public Connection getConn() {
        return conn;
    }

    public ResultSet getRs() {
        return rs;
    }
    
    public Statement getStmt() {
        return stmt;
    }

    public static void setRs(ResultSet rs) {
        Database.rs = rs;
    }


    public void loadSiswa() {
        try {
            connect();
            sql = "SELECT * FROM siswa ";
            rs = stmt.executeQuery(sql);
            String [] namasiswa = new String[rs.getRow()];
            int i=0;
            while (rs.next()) {
                siswa student;
                namasiswa[i] = rs.getString("nama_siswa");
                student = new siswa(
                    rs.getString("nama_siswa"),
                    rs.getString("nis")
                );
                listSiswa.add(student);
                i++;
            }
            disconnect();
            //viewSiswa.daftarsiswa().setListData(namasiswa);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadKelas() {
        try {
           connect();
           sql = "SELECT * FROM kelas" ;
           rs = stmt.executeQuery(sql);
           kelas kls;
           while (rs.next()) {
               kls = new kelas(
                   rs.getString("nama_kelas")
               );
            listKelas.add(kls);
           }
           disconnect();
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    public void loadNilai() {
        try {
           connect();
           sql = "SELECT * FROM mata_kuliah NATURAL JOIN dosen";
           rs = stmt.executeQuery(sql);
           matapelajaran course;
           guru teacher;
           while (rs.next()) {
                teacher = new guru(
                    rs.getString("nama_guru"),
                    rs.getString("kode_guru")
                );
                course = new matapelajaran(
                    rs.getString("nama_mapel")
                );
                listMapel.add(course);
           }
           disconnect();
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    public ArrayList<kelas> getListKelas() {
        return listKelas;
    }

    public ArrayList<siswa> getListSiswa() {
        return listSiswa;
    }

    public ArrayList<matapelajaran> getListMapel() {
        return listMapel;
    }
}
