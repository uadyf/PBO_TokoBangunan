/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.BarangMasuk;

import Model.Barang;

/**
 *
 * @author ASUS
 */

public class DetailBarangMasuk {
    private Barang barang;
    private int jumlah;
    private double hargaSatuan;
    
    public DetailBarangMasuk(Barang barang, int jumlah, double hargaSatuan) {
        this.barang = barang;
        this.jumlah = jumlah;
        this.hargaSatuan = hargaSatuan;
    }
    
    public Barang getBarang() {
        return barang;
    }
    public void setBarang(Barang barang) {
        this.barang = barang;
    }
    
    public int getJumlah() {
        return jumlah;
    }
    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
    
    public double getHargaSatuan() {
        return hargaSatuan;
    }
    public void setHargaSatuan(double hargaSatuan) {
        this.hargaSatuan = hargaSatuan;
    }
}