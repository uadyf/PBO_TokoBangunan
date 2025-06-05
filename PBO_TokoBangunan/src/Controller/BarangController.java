/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.BarangDAO;
import DAO.BarangDAOImpl;
import Model.Barang;
import java.util.List;
/**
 *
 * @author ASUS
 */
public class BarangController {
    private BarangDAO dao = new BarangDAOImpl();

    public void tambahBarang(Barang b) {
        dao.insert(b);
    }

    public void updateBarang(Barang b) {
        dao.update(b);
    }

    public void hapusBarang(int id) {
        dao.delete(id);
    }

    public List<Barang> tampilkanSemua() {
        return dao.getAll();
    }

    public Barang cariById(int id) {
        return dao.getById(id);
    }
    
    public List<Barang> getBarangByKategori(int idKategori) {
        return dao.getByKategori(idKategori);
    }
}
