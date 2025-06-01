/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.BarangController;
import Controller.KategoriController;
import Model.Barang;
import Model.Kategori;
import View.BarangMasuk.BarangMasukForm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
/**
 *
 * @author ASUS
 */
public class BarangView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private BarangController controller = new BarangController();
    private JButton btnTambah, btnEdit, btnHapus, btnFilter, btnBarangMasuk;
    private JComboBox<Kategori> comboFilterKategori;

    public BarangView() {
        setTitle("Inventaris Toko Bangunan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 450);
        setLocationRelativeTo(null);

        // Tabel
        tableModel = new DefaultTableModel(new String[]{"ID", "Nama", "Kategori", "Satuan", "Stok", "Beli", "Jual"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Panel atas
        JPanel panelAtas = new JPanel();

        // Tombol-tombol utama
        btnTambah = new JButton("Tambah Barang");
        btnEdit = new JButton("Edit Barang");
        btnHapus = new JButton("Hapus Barang");
        btnBarangMasuk = new JButton("Barang Masuk");
        
        panelAtas.add(btnBarangMasuk);
        panelAtas.add(btnTambah);
        panelAtas.add(btnEdit);
        panelAtas.add(btnHapus);

        // Combo filter kategori
        comboFilterKategori = new JComboBox<>();
        btnFilter = new JButton("Filter");

        KategoriController kategoriController = new KategoriController();
        List<Kategori> listKategori = kategoriController.getAllKategori();
        comboFilterKategori.addItem(new Kategori(0, "Semua Kategori"));
        for (Kategori k : listKategori) {
            comboFilterKategori.addItem(k);
        }

        panelAtas.add(new JLabel("Kategori:"));
        panelAtas.add(comboFilterKategori);
        panelAtas.add(btnFilter);

        // Aksi tombol
        btnTambah.addActionListener(e -> {
            BarangForm form = new BarangForm(this, null);
            form.setVisible(true);
            loadData();
        });

        btnEdit.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int id = (int) tableModel.getValueAt(row, 0);
                Barang barang = controller.cariById(id);
                if (barang != null) {
                    BarangForm form = new BarangForm(this, barang);
                    form.setVisible(true);
                    loadData();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Pilih barang yang ingin diedit.");
            }
        });

        btnHapus.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus?");
                if (confirm == JOptionPane.YES_OPTION) {
                    int id = (int) tableModel.getValueAt(row, 0);
                    controller.hapusBarang(id);
                    loadData();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Pilih barang yang ingin dihapus.");
            }
        });

        btnFilter.addActionListener(e -> {
            Kategori k = (Kategori) comboFilterKategori.getSelectedItem();
            if (k != null) {
                if (k.getIdKategori() == 0) {
                    loadData();
                } else {
                    loadDataByKategori(k.getIdKategori());
                }
            }
        });

        btnBarangMasuk.addActionListener(e -> {
            BarangMasukForm form = new BarangMasukForm(this);
            form.setVisible(true);
        });
        add(panelAtas, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        loadData();
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<Barang> barangList = controller.tampilkanSemua();
        for (Barang b : barangList) {
            tableModel.addRow(new Object[]{
                    b.getIdBarang(),
                    b.getNamaBarang(),
                    b.getIdKategori(), // Jika ingin menampilkan nama kategori, harus join
                    b.getSatuan(),
                    b.getStok(),
                    b.getHargaBeli(),
                    b.getHargaJual()
            });
        }
    }

    private void loadDataByKategori(int idKategori) {
        tableModel.setRowCount(0);
        List<Barang> barangList = controller.getBarangByKategori(idKategori);
        for (Barang b : barangList) {
            tableModel.addRow(new Object[]{
                    b.getIdBarang(),
                    b.getNamaBarang(),
                    b.getIdKategori(), // Jika ingin nama kategori, perlu relasi
                    b.getSatuan(),
                    b.getStok(),
                    b.getHargaBeli(),
                    b.getHargaJual()
            });
        }
    }
}
