/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.kategoriDAO;
import dao.kategoriDAOInterface;
import java.util.List;
import model.kategori;

/**
 *
 * @author ASUS
 */
public class kategoriController {
    private kategoriDAOInterface kategoriDAO = new kategoriDAO();
    
    public List<kategori> getAllKategori() {
        return kategoriDAO.getAll();
    }
}
