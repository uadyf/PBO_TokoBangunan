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

    @Override
    public List<Kategori> getAll() {
        List<Kategori> list = new ArrayList<>();
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM kategori");
            while (rs.next()) {
                list.add(new Kategori(rs.getInt("id_kategori"), rs.getString("nama_kategori")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
