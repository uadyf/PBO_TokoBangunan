/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Supplier;

import Model.Supplier.Supplier;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface SupplierDAO {
    void insert(Supplier supplier);
    void update(Supplier supplier);
    void delete(int idSupplier);
    List<Supplier> getAll();
    Supplier getById(int idSupplier);
}