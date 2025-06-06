/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.BarangController;
import Controller.KategoriController;
import Model.Barang;
import Model.BarangMasuk.BarangMasuk;
import Model.Kategori;
import View.BarangMasuk.BarangMasukForm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

//        btnBarangMasuk.addActionListener(e -> {
//            int row = table.getSelectedRow();
//            if (row >= 0) {
//                int idBarang = (int) tableModel.getValueAt(row, 0);
//                String namaBarang = (String) tableModel.getValueAt(row, 1);
//                showBarangMasukDialog(idBarang, namaBarang);
//            } else {
//                JOptionPane.showMessageDialog(this, "Pilih barang terlebih dahulu.");
//            }
//        });
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<Barang> barangList = controller.tampilkanSemua();
        for (Barang b : barangList) {
            tableModel.addRow(new Object[]{
                    b.getIdBarang(),
                    b.getNamaBarang(),
                    b.getIdKategori(),
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
    
//    private void showBarangMasukDialog(int idBarang, String namaBarang) {
//        JTextField tfTanggal = new JTextField(10);
//        JTextField tfNamaSupplier = new JTextField(20);
//        JTextField tfJumlah = new JTextField(5);
//        JTextField tfHarga = new JTextField(10);
//
//        JPanel panel = new JPanel(new GridLayout(0, 2));
//        panel.add(new JLabel("Tanggal Masuk (YYYY-MM-DD):"));
//        panel.add(tfTanggal);
//        panel.add(new JLabel("Nama Supplier:"));
//        panel.add(tfNamaSupplier);
//        panel.add(new JLabel("Jumlah:"));
//        panel.add(tfJumlah);
//        panel.add(new JLabel("Harga Satuan:"));
//        panel.add(tfHarga);
//
//        int result = JOptionPane.showConfirmDialog(this, panel, "Input Barang Masuk untuk: " + namaBarang,
//                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
//
//        if (result == JOptionPane.OK_OPTION) {
//            String tanggal = tfTanggal.getText().trim();
//            String namaSupplier = tfNamaSupplier.getText().trim();
//            int jumlah = Integer.parseInt(tfJumlah.getText().trim());
//            double harga = Double.parseDouble(tfHarga.getText().trim());
//
//            Controller.BarangMasukController controller = new Controller.BarangMasukController();
//            BarangMasuk barang = new BarangMasuk();
//            
//            boolean success = controller.simpanBarangMasuk(tanggal, namaSupplier, idBarang, jumlah, harga);
//
//            if (success) {
//                JOptionPane.showMessageDialog(this, "Data barang masuk berhasil disimpan!");
//                loadData();
//            } else {
//                JOptionPane.showMessageDialog(this, "Gagal menyimpan data barang masuk.", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    }

}
