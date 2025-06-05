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

    @Override
    public List<Barang> getAll() {
        List<Barang> list = new ArrayList<>();
        try (Connection conn = Connector.Connect()) {
            String sql = "SELECT * FROM barang";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Barang b = new Barang();
                b.setIdBarang(rs.getInt("id_barang"));
                b.setNamaBarang(rs.getString("nama_barang"));
                b.setIdKategori(rs.getInt("id_kategori"));
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
    public List<Barang> getByKategori(int idKategori) {
        List<Barang> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM barang WHERE id_kategori = ?";
            PreparedStatement ps = Connector.Connect().prepareStatement(sql);
            ps.setInt(1, idKategori);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Barang b = new Barang();
                b.setIdBarang(rs.getInt("id_barang"));
                b.setNamaBarang(rs.getString("nama_barang"));
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

    @Override
    public Barang getById(int id) {
        Barang b = null;
        try (Connection conn = Connector.Connect()) {
            String sql = "SELECT * FROM barang WHERE id_barang=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                b = new Barang();
                b.setIdBarang(rs.getInt("id_barang"));
                b.setNamaBarang(rs.getString("nama_barang"));
                b.setIdKategori(rs.getInt("id_kategori"));
                b.setStok(rs.getInt("stok"));
                b.setHargaBeli(rs.getDouble("harga_beli"));
                b.setHargaJual(rs.getDouble("harga_jual"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

    @Override
    public void insert(Barang b) {
        try (Connection conn = Connector.Connect()) {
            String sql = "INSERT INTO barang (nama_barang, id_kategori, stok, harga_beli, harga_jual) " +
                         "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, b.getNamaBarang());
            ps.setInt(2, b.getIdKategori());
            ps.setInt(3, b.getStok());
            ps.setDouble(4, b.getHargaBeli());
            ps.setDouble(5, b.getHargaJual());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Barang b) {
        try (Connection conn = Connector.Connect()) {
            String sql = "UPDATE barang SET nama_barang=?, id_kategori=?, stok=?, harga_beli=?, harga_jual=? " +
                         "WHERE id_barang=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, b.getNamaBarang());
            ps.setInt(2, b.getIdKategori());
            ps.setInt(3, b.getStok());
            ps.setDouble(4, b.getHargaBeli());
            ps.setDouble(5, b.getHargaJual());
            ps.setInt(6, b.getIdBarang());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection conn = Connector.Connect()) {
            String sql = "DELETE FROM barang WHERE id_barang = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
