/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class kategori {
    private int idKategori;
    private String namaKategori;

    public int getId() { return idKategori; }
    public void setId(int idKategori) { this.idKategori = idKategori; }
    public String getNama() { return namaKategori; }
    public void setNama(String namaKategori ) { this.namaKategori = namaKategori; }

    @Override
    public String toString() {
        return namaKategori; // untuk tampil di JComboBox
    }
}