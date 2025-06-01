/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.BarangMasuk;

import Model.BarangMasuk.BarangMasuk;
import Model.BarangMasuk.DetailBarangMasuk;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface BarangMasukDAO {
    void insert(BarangMasuk barangMasuk, List<DetailBarangMasuk> detailList);
    List<BarangMasuk> getAll();
}
