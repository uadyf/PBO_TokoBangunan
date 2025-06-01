/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.BarangMasuk;

import Model.BarangMasuk.BarangMasuk;
import Model.BarangMasuk.DetailBarangMasuk;
import Utils.Connector;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class BarangMasukDAOImpl implements BarangMasukDAO {
    private final Connection conn;

    public BarangMasukDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(BarangMasuk barangMasuk, List<DetailBarangMasuk> detailList) {
        PreparedStatement psMasuk = null;
        PreparedStatement psDetail = null;
        PreparedStatement psUpdateStok = null;
        ResultSet rsGeneratedKeys = null;

        String sqlMasuk = "INSERT INTO barang_masuk (tanggal_masuk, id_supplier, keterangan) VALUES (?, ?, ?)";
        String sqlDetail = "INSERT INTO detail_barang_masuk (id_masuk, id_barang, jumlah, harga_satuan) VALUES (?, ?, ?, ?)";
        String sqlUpdateStok = "UPDATE barang SET stok = stok + ? WHERE id_barang = ?";

        try {
            conn.setAutoCommit(false);

            psMasuk = conn.prepareStatement(sqlMasuk, Statement.RETURN_GENERATED_KEYS);
            psMasuk.setDate(1, new java.sql.Date(barangMasuk.getTanggalMasuk().getTime()));
            psMasuk.setInt(2, barangMasuk.getSupplier().getId()); // Pastikan getter benar
            psMasuk.setString(3, barangMasuk.getKeterangan());
            psMasuk.executeUpdate();

            rsGeneratedKeys = psMasuk.getGeneratedKeys();
            if (rsGeneratedKeys.next()) {
                int idMasuk = rsGeneratedKeys.getInt(1);

                for (DetailBarangMasuk detail : detailList) {
                    psDetail = conn.prepareStatement(sqlDetail);
                    psDetail.setInt(1, idMasuk);
                    psDetail.setInt(2, detail.getBarang().getIdBarang());
                    psDetail.setInt(3, detail.getJumlah());
                    psDetail.setDouble(4, detail.getHargaSatuan());
                    psDetail.executeUpdate();

                    psUpdateStok = conn.prepareStatement(sqlUpdateStok);
                    psUpdateStok.setInt(1, detail.getJumlah());
                    psUpdateStok.setInt(2, detail.getBarang().getIdBarang());
                    psUpdateStok.executeUpdate();
                }
            }

            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (rsGeneratedKeys != null) rsGeneratedKeys.close();
                if (psMasuk != null) psMasuk.close();
                if (psDetail != null) psDetail.close();
                if (psUpdateStok != null) psUpdateStok.close();
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<BarangMasuk> getAll() {
        // Belum diimplementasikan
        return new ArrayList<>();
    }
}
