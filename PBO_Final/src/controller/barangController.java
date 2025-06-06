/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.barangDAO;
import dao.barangDAOInterface;
import java.util.List;
import model.barang;

/**
 *
 * @author ASUS
 */
public class barangController {
    private barangDAOInterface dao = new barangDAO();

    public void tambahBarang(barang b) {
        dao.insert(b);
    }

    public void updateBarang(barang b) {
        dao.update(b);
    }

    public void hapusBarang(int id) {
        dao.delete(id);
    }

    public List<barang> getAllBarang() {
        return dao.getAll();
    }

    public barang getBarangById(int id) {
        return dao.getById(id);
    }
    
    public List<barang> getBarangByKategori(int idKategori) {
        return dao.getByKategori(idKategori);
    }
}