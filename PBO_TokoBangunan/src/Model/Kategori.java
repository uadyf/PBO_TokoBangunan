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

    public int getIdKategori() { return idKategori; }
    public String getNamaKategori() { return namaKategori; }

    @Override
    public String toString() {
        return namaKategori; // untuk tampil di JComboBox
    }
}