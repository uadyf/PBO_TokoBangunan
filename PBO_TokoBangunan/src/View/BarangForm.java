/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.BarangController;
import Model.Barang;
import Model.Kategori;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author ASUS
 */
public class BarangForm extends JDialog {
    private JTextField tfNama, tfSatuan, tfStok, tfHargaBeli, tfHargaJual;
    private JComboBox<Kategori> comboKategori;
    private JButton btnSimpan, btnBatal;
    private BarangController controller = new BarangController();
    private Barang barang; // untuk edit

    public BarangForm(JFrame parent, Barang barang) {
        super(parent, true);
        this.barang = barang;

        setTitle(barang == null ? "Tambah Barang" : "Edit Barang");
        setSize(400, 350);
        setLayout(new GridLayout(8, 2, 10, 5));
        setLocationRelativeTo(parent);

        // Komponen
        tfNama = new JTextField();
        comboKategori = new JComboBox<>();
        tfSatuan = new JTextField();
        tfStok = new JTextField();
        tfHargaBeli = new JTextField();
        tfHargaJual = new JTextField();

        if (barang != null) {
            tfNama.setText(barang.getNamaBarang());
            for (int i = 0; i < comboKategori.getItemCount(); i++) {
                if (comboKategori.getItemAt(i).getIdKategori() == barang.getIdKategori()) {
                    comboKategori.setSelectedIndex(i);
                    break;
                }
            }
            tfSatuan.setText(barang.getSatuan());
            tfStok.setText(String.valueOf(barang.getStok()));
            tfHargaBeli.setText(String.valueOf(barang.getHargaBeli()));
            tfHargaJual.setText(String.valueOf(barang.getHargaJual()));
        }

        btnSimpan = new JButton("Simpan");
        btnBatal = new JButton("Batal");

        // Layout
        add(new JLabel("Nama Barang"));
        add(tfNama);
        add(new JLabel("ID Kategori"));
        loadKategori();
        add(comboKategori);
        add(new JLabel("Satuan"));
        add(tfSatuan);
        add(new JLabel("Stok"));
        add(tfStok);
        add(new JLabel("Harga Beli"));
        add(tfHargaBeli);
        add(new JLabel("Harga Jual"));
        add(tfHargaJual);
        add(btnSimpan);
        add(btnBatal);

        // Aksi tombol
        btnSimpan.addActionListener(e -> simpanBarang());
        btnBatal.addActionListener(e -> dispose());
    }
    
    private void loadKategori() {
        KategoriController kategoriController = new KategoriController();
        List<Kategori> listKategori = kategoriController.getAllKategori();
        for (Kategori k : listKategori) {
            comboKategori.addItem(k);
        }
    }

    private void simpanBarang() {
        try {
            Barang b = barang != null ? barang : new Barang();
            b.setNamaBarang(tfNama.getText());
            b.setIdKategori(Integer.parseInt(tfKategori.getText()));
            b.setSatuan(tfSatuan.getText());
            b.setStok(Integer.parseInt(tfStok.getText()));
            b.setHargaBeli(Double.parseDouble(tfHargaBeli.getText()));
            b.setHargaJual(Double.parseDouble(tfHargaJual.getText()));

            if (barang == null) {
                controller.tambahBarang(b);
            } else {
                controller.updateBarang(b);
            }

            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Input tidak valid: " + e.getMessage());
        }
    }
}