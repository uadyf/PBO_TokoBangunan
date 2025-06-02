/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.Supplier.SupplierDAO;
import DAO.Supplier.SupplierDAOImpl;
import Model.Supplier.Supplier;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class SupplierController {
    private SupplierDAO dao;

    public SupplierController() {
        dao = new SupplierDAOImpl();  // Tidak lagi memerlukan Connector
    }

    public void simpan(Supplier s) {
        if (s.getId() == 0) {
            dao.insert(s);
        } else {
            dao.update(s);
        }
    }

    public void hapus(int id) {
        dao.delete(id);
    }

    public List<Supplier> getAllSupplier() {
        return dao.getAll();
    }

    public Supplier getById(int id) {
        return dao.getById(id);
    }
    
    public Supplier getOrCreateSupplier(String nama, String kontak, String alamat) {
        SupplierDAO supplierDAO = new SupplierDAOImpl();
        Supplier existing = supplierDAO.getByName(nama);
        if (existing != null) {
            return existing;
        } else {
            Supplier newSupplier = new Supplier(nama, kontak, alamat);
            supplierDAO.insert(newSupplier);
            return supplierDAO.getByName(nama); // Ambil ulang untuk mendapatkan ID-nya
        }
    }
}


