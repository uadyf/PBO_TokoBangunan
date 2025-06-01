/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.BarangMasukController;
import Controller.SupplierController;
import Model.Barang;
import Model.BarangMasuk.BarangMasuk;
import Model.BarangMasuk.DetailBarangMasuk;
import Model.Supplier.Supplier;
import DAO.BarangDAOImpl;
import Utils.Connector;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class BarangMasukForm extends JFrame {
    private JComboBox<Barang> cbBarang;
    private JTextField tfJumlah, tfHarga;
    private JButton btnTambahDetail, btnSimpan;
    private JTable tblDetail;
    private DefaultTableModel tableModel;
    private List<DetailBarangMasuk> detailList = new ArrayList<>();
    private Connection conn = Connector.Connect();
    
    public BarangMasukForm() {
        setTitle("Form Barang Masuk");
        setSize(600, 400);
        setLayout(new BorderLayout());

        // --- Input Panel ---
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        
        cbBarang = new JComboBox<>(new BarangDAOImpl().getAll().toArray(new Barang[0]));
        tfJumlah = new JTextField();
        tfHarga = new JTextField();
        btnTambahDetail = new JButton("Tambah Barang");

        inputPanel.add(new JLabel("Pilih Barang:"));
        inputPanel.add(cbBarang);
        inputPanel.add(new JLabel("Jumlah:"));
        inputPanel.add(tfJumlah);
        inputPanel.add(new JLabel("Harga Satuan:"));
        inputPanel.add(tfHarga);
        inputPanel.add(btnTambahDetail);

        add(inputPanel, BorderLayout.NORTH);

        // --- Table Panel ---
        tableModel = new DefaultTableModel(new String[]{"Barang", "Jumlah", "Harga Satuan"}, 0);
        tblDetail = new JTable(tableModel);
        add(new JScrollPane(tblDetail), BorderLayout.CENTER);

        // --- Button Panel ---
        btnSimpan = new JButton("Simpan Barang Masuk");
        add(btnSimpan, BorderLayout.SOUTH);

        // --- Action Listeners ---
        btnTambahDetail.addActionListener(e -> {
            Barang selected = (Barang) cbBarang.getSelectedItem();
            int jumlah = Integer.parseInt(tfJumlah.getText());
            double harga = Double.parseDouble(tfHarga.getText());
            detailList.add(new DetailBarangMasuk(selected, jumlah, harga));

            tableModel.addRow(new Object[]{selected.getNamaBarang(), jumlah, harga});
        });

        btnSimpan.addActionListener(e -> {
            BarangMasuk barangMasuk = new BarangMasuk();
            barangMasuk.setTanggalMasuk(new Date()); // atau pilih tanggal
            barangMasuk.setSupplier(new Supplier(1, "Default")); // bisa ganti pilihan supplier
            barangMasuk.setKeterangan("Barang Masuk");

            BarangMasukController controller = new BarangMasukController();
            controller.simpanBarangMasuk(barangMasuk, detailList);
            JOptionPane.showMessageDialog(this, "Barang masuk disimpan!");
        });

        setVisible(true);
    }
}

