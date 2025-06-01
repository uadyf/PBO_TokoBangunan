/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Supplier;

/**
 *
 * @author ASUS
 */
public class Supplier {
    private int idSupplier;
    private String nama;
    private String kontak;
    private String alamat;

    public Supplier(int idSupplier, String nama, String kontak, String alamat) {
        this.idSupplier = idSupplier;
        this.nama = nama;
        this.kontak = kontak;
        this.alamat = alamat;
    }

    public Supplier(String nama, String kontak, String alamat) {
        this(0, nama, kontak, alamat);
    }

    public void setId(int idSupplier) {
        this.idSupplier = idSupplier;
    }
    public int getId() {
        return idSupplier;
    }
    
    public void setNama(String NamaSupplier) {
        this.nama = NamaSupplier;
    }
    public String getNama() {
        return nama;
    }
    
    public void setKontak(String KontakSupplier) {
        this.kontak = KontakSupplier;
    }
    public String getKontak() {
        return kontak;
    }
    
    public void setAlamat(String AlamatSupplier) {
        this.kontak = AlamatSupplier;
    }
    public String getAlamat() {
        return alamat;
    }

    @Override
    public String toString() {
        return nama;
    }
}
