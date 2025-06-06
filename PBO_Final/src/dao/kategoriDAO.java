/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.kategori;
import utils.connector;
/**
 *
 * @author ASUS
 */
public class kategoriDAO implements kategoriDAOInterface {
    private Connection conn;

    public kategoriDAO() {
        conn = connector.getConnection(); // pastikan class koneksi tersedia
    }

    @Override
    public List<kategori> getAll() {
        List<kategori> list = new ArrayList<>();
        String sql = "SELECT * FROM kategori_barang";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                kategori k = new kategori();
                k.setId(rs.getInt("id_kategori"));
                k.setNama(rs.getString("nama_kategori"));
                list.add(k);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
}