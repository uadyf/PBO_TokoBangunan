/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Model.Barang;
import java.util.List;
/**
 *
 * @author ASUS
 */
public interface BarangDAO {
    void insert(Barang barang);
    void update(Barang barang);
    void delete(int idBarang);
    Barang getById(int idBarang);
    List<Barang> getAll();
}