/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.BarangMasuk.BarangMasukDAO;
import DAO.BarangMasuk.BarangMasukDAOImpl;
import Controller.SupplierController;
import Model.Barang;
import Model.BarangMasuk.BarangMasuk;
import Model.BarangMasuk.DetailBarangMasuk;
import Model.Supplier.Supplier;
import Utils.Connector;
import java.util.List;
import java.sql.*;
/**
 *
 * @author ASUS
 */
public class BarangMasukController {
    private final BarangMasukDAO dao;

    public BarangMasukController() {
        Connection conn = Connector.Connect();
        this.dao = new BarangMasukDAOImpl(conn);
    }

    public void simpanBarangMasuk(BarangMasuk barangMasuk, List<DetailBarangMasuk> detailList) {
        dao.insert(barangMasuk, detailList);
    }
    
    public void barangMasuk(Barang barang, Date tanggal, Supplier supplier, int jumlah, double hargaSatuan, String keterangan) {
        SupplierController findSupplier = new SupplierController();
        Supplier validSupplier = findSupplier.getOrCreateSupplier(
            supplier.getNama(), supplier.getKontak(), supplier.getAlamat()
        );

        BarangMasuk barangMasuk = new BarangMasuk();
        barangMasuk.setTanggal(tanggal);
        barangMasuk.setSupplier(validSupplier);
        barangMasuk.setKeterangan(keterangan);

        DetailBarangMasuk detail = new DetailBarangMasuk(barang, jumlah, hargaSatuan);

        dao.insert(barangMasuk, List.of(detail));
    }
}


