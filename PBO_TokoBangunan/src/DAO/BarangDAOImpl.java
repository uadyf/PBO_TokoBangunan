/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Barang;
import Utils.Connector;
import java.sql.*;
import java.util.*;

/**
 *
 * @author ASUS
 */
public class BarangDAOImpl implements BarangDAO {
    private Connection conn;

    public BarangDAOImpl() {
        conn = Connector.Connect();
    }

    @Override
    public void insert(Barang b) {
        String sql = "INSERT INTO barang (nama_barang, id_kategori, satuan, stok, harga_beli, harga_jual) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, b.getNamaBarang());
            ps.setInt(2, b.getIdKategori());
            ps.setString(3, b.getSatuan());
            ps.setInt(4, b.getStok());
            ps.setDouble(5, b.getHargaBeli());
            ps.setDouble(6, b.getHargaJual());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Barang b) {
        String sql = "UPDATE barang SET nama_barang=?, id_kategori=?, satuan=?, stok=?, harga_beli=?, harga_jual=? WHERE id_barang=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, b.getNamaBarang());
            ps.setInt(2, b.getIdKategori());
            ps.setString(3, b.getSatuan());
            ps.setInt(4, b.getStok());
            ps.setDouble(5, b.getHargaBeli());
            ps.setDouble(6, b.getHargaJual());
            ps.setInt(7, b.getIdBarang());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM barang WHERE id_barang=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Barang getById(int id) {
        String sql = "SELECT * FROM barang WHERE id_barang=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Barang b = new Barang();
                b.setIdBarang(rs.getInt("id_barang"));
                b.setNamaBarang(rs.getString("nama_barang"));
                b.setIdKategori(rs.getInt("id_kategori"));
                b.setSatuan(rs.getString("satuan"));
                b.setStok(rs.getInt("stok"));
                b.setHargaBeli(rs.getDouble("harga_beli"));
                b.setHargaJual(rs.getDouble("harga_jual"));
                return b;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Barang> getAll() {
        List<Barang> list = new ArrayList<>();
        String sql = "SELECT * FROM barang";
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Barang b = new Barang();
                b.setIdBarang(rs.getInt("id_barang"));
                b.setNamaBarang(rs.getString("nama_barang"));
                b.setIdKategori(rs.getInt("id_kategori"));
                b.setSatuan(rs.getString("satuan"));
                b.setStok(rs.getInt("stok"));
                b.setHargaBeli(rs.getDouble("harga_beli"));
                b.setHargaJual(rs.getDouble("harga_jual"));
                list.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    @Override
    public List<Barang> getBarangByKategori(int idKategori) {
        List<Barang> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM barang WHERE id_kategori = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idKategori);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Barang b = new Barang();
                b.setIdBarang(rs.getInt("id_barang"));
                b.setNamaBarang(rs.getString("nama_barang"));
                b.setIdKategori(rs.getInt("id_kategori"));
                b.setSatuan(rs.getString("satuan"));
                b.setStok(rs.getInt("stok"));
                b.setHargaBeli(rs.getDouble("harga_beli"));
                b.setHargaJual(rs.getDouble("harga_jual"));
                list.add(b);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}