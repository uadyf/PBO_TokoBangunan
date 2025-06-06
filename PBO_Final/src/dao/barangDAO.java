/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import utils.connector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.barang;

/**
 *
 * @author ASUS
 */
public class barangDAO implements barangDAOInterface {
    private Connection conn;

    public barangDAO() {
        this.conn = connector.getConnection(); // pastikan class Koneksi sudah ada
    }

    @Override
    public void insert(barang b) {
        String sql = "INSERT INTO barang (id_kategori, nama_barang, nama_kategori, stok, harga_beli, harga_jual) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, b.getIdKategori());
            stmt.setString(2, b.getNamaBarang());
            stmt.setString(3, b.getNamaKategori());
            stmt.setInt(4, b.getStok());
            stmt.setDouble(5, b.getHargaBeli());
            stmt.setDouble(6, b.getHargaJual());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(barang b) {
        String sql = "UPDATE barang SET id_kategori=?, nama_barang=?, nama_kategori=?, stok=?, harga_beli=?, harga_jual=? WHERE id_barang=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, b.getIdKategori());
            stmt.setString(2, b.getNamaBarang());
            stmt.setString(3, b.getNamaKategori());
            stmt.setInt(4, b.getStok());
            stmt.setDouble(5, b.getHargaBeli());
            stmt.setDouble(6, b.getHargaJual());
            stmt.setInt(7, b.getIdBarang());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM barang WHERE id_barang=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public barang getById(int id) {
        String sql = "SELECT * FROM barang WHERE id_barang=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                barang b = new barang();
                b.setIdBarang(rs.getInt("id_barang"));
                b.setIdKategori(rs.getInt("id_kategori"));
                b.setNamaBarang(rs.getString("nama_barang"));
                b.setNamaKategori(rs.getString("nama_kategori"));
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
    public List<barang> getAll() {
        List<barang> list = new ArrayList<>();
        String sql = "SELECT * FROM barang";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                barang b = new barang();
                b.setIdBarang(rs.getInt("id_barang"));
                b.setIdKategori(rs.getInt("id_kategori"));
                b.setNamaBarang(rs.getString("nama_barang"));
                b.setNamaKategori(rs.getString("nama_kategori"));
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
    public List<barang> getByKategori(int idKategori) {
        List<barang> list = new ArrayList<>();
        String sql = "SELECT * FROM barang WHERE id_kategori=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idKategori);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                barang b = new barang();
                b.setIdBarang(rs.getInt("id_barang"));
                b.setIdKategori(rs.getInt("id_kategori"));
                b.setNamaBarang(rs.getString("nama_barang"));
                b.setNamaKategori(rs.getString("nama_kategori"));
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
}