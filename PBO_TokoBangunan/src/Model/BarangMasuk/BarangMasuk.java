/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.BarangMasuk;

import Model.Supplier.Supplier;
import java.util.Date;

/**
 *
 * @author ASUS
 */
import java.util.Date;

public class BarangMasuk {
    private int idMasuk;
    private Date tanggalMasuk;
    private Supplier supplier;
    private String keterangan;

    public int getIdMasuk() {
        return idMasuk;
    }

    public void setIdMasuk(int idMasuk) {
        this.idMasuk = idMasuk;
    }

    public Date getTanggalMasuk() {
        return tanggalMasuk;
    }

    public void setTanggalMasuk(Date tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}