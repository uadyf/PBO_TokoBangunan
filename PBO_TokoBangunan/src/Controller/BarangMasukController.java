/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.BarangMasuk.BarangMasukDAO;
import DAO.BarangMasuk.BarangMasukDAOImpl;
import Model.BarangMasuk.BarangMasuk;
import Model.BarangMasuk.DetailBarangMasuk;
import Utils.Connector;
import java.util.List;
import java.sql.*;
/**
 *
 * @author ASUS
 */
public class BarangMasukController {
    private final BarangMasukDAO dao;

    public BarangMasukController() {
        Connection conn = Connector.Connect();
        this.dao = new BarangMasukDAOImpl(conn);
    }

    public void simpanBarangMasuk(BarangMasuk barangMasuk, List<DetailBarangMasuk> detailList) {
        dao.insert(barangMasuk, detailList);
    }
}


