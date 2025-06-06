/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.KategoriDAO;
import DAO.KategoriDAOImpl;
import Utils.Connector;
import Model.Kategori;

import java.util.List;
/**
 *
 * @author ASUS
 */
public class KategoriController {
    private KategoriDAO kategoriDAO;

    public KategoriController() {
        kategoriDAO = new KategoriDAOImpl(Connector.Connect());
    }

    public List<Kategori> getAllKategori() {
        return kategoriDAO.getAllKategori();
    }
}