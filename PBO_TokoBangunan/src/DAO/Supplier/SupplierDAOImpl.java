/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Supplier;

import Model.Supplier.Supplier;
import Utils.Connector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ASUS
 */
public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public void insert(Supplier supplier) {
        try (Connection conn = Connector.Connect()) {
            String sql = "INSERT INTO supplier (nama_supplier, kontak, alamat) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, supplier.getNama());
            ps.setString(2, supplier.getKontak());
            ps.setString(3, supplier.getAlamat());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Supplier supplier) {
        try (Connection conn = Connector.Connect()) {
            String sql = "UPDATE supplier SET nama_supplier = ?, kontak = ?, alamat = ? WHERE id_supplier = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, supplier.getNama());
            ps.setString(2, supplier.getKontak());
            ps.setString(3, supplier.getAlamat());
            ps.setInt(4, supplier.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int idSupplier) {
        try (Connection conn = Connector.Connect()) {
            String sql = "DELETE FROM supplier WHERE id_supplier = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idSupplier);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> list = new ArrayList<>();
        try (Connection conn = Connector.Connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM supplier")) {

            while (rs.next()) {
                list.add(new Supplier(
                    rs.getInt("id_supplier"),
                    rs.getString("nama_supplier"),
                    rs.getString("kontak"),
                    rs.getString("alamat")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Supplier getById(int idSupplier) {
        Supplier s = null;
        try (Connection conn = Connector.Connect()) {
            String sql = "SELECT * FROM supplier WHERE id_supplier = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idSupplier);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                s = new Supplier("", "", "");
                s.setId(rs.getInt("id_supplier"));
                s.setNama(rs.getString("nama_supplier"));
                s.setKontak(rs.getString("kontak"));
                s.setAlamat(rs.getString("alamat"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }
}
