/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import model.barang;

/**
 *
 * @author ASUS
 */
public interface barangDAOInterface {
    void insert(barang barang);
    void update(barang barang);
    void delete(int id);
    barang getById(int id);
    List<barang> getAll();
    List<barang> getByKategori(int idKategori);
}
