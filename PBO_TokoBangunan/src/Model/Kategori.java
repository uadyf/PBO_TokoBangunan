/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ASUS
 */
public class Kategori {
    private int idKategori;
    private String namaKategori;

    public Kategori(int id, String nama) {
        this.idKategori = id;
        this.namaKategori = nama;
    }
    public int getId() { return idKategori; }
    public void setId(int idKategori) { this.idKategori = idKategori; }
    public String getNama() { return namaKategori; }
    public void setNama(String namaKategori ) { this.namaKategori = namaKategori; }

    @Override
    public String toString() {
        return namaKategori;
    }
}