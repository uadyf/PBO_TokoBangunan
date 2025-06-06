/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Kategori;
import Utils.Connector;
import java.sql.*;
import java.util.*;
/**
 *
 * @author ASUS
 */
public class KategoriDAOImpl implements KategoriDAO {
    private Connection conn = Connector.Connect();
    
    public KategoriDAOImpl(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public List<Kategori> getAllKategori() {
        List<Kategori> list = new ArrayList<>();
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM kategori_barang");
            while (rs.next()) {
                list.add(new Kategori(rs.getInt("id_kategori"), rs.getString("nama_kategori")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    @Override
    public String getNamaKategori(int id) {
        String ret = "";
        try (Connection conn = Connector.Connect()) {
            String sql = "SELECT * FROM kategori_barang WHERE id_barang=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                ret = rs.getString("nama_kategori");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
