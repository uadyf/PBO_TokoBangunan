/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.SupplierController;
import Model.Supplier.Supplier;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
;

/**
 *
 * @author ASUS
 */
public class SupplierView extends JFrame {
    private SupplierController controller;
    private JTable table;
    private DefaultTableModel tableModel;

    private JTextField tfNama, tfKontak, tfAlamat;
    private JButton btnSimpan, btnHapus, btnReset;
    private int selectedId = 0;

    public SupplierView() {
        controller = new SupplierController();

        setTitle("Manajemen Supplier - Inventaris Toko Bangunan");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tabel
        tableModel = new DefaultTableModel(new String[]{"ID", "Nama", "Kontak", "Alamat"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Form
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 5));
        tfNama = new JTextField();
        tfKontak = new JTextField();
        tfAlamat = new JTextField();
        panelForm.add(new JLabel("Nama Supplier:"));
        panelForm.add(tfNama);
        panelForm.add(new JLabel("Kontak:"));
        panelForm.add(tfKontak);
        panelForm.add(new JLabel("Alamat:"));
        panelForm.add(tfAlamat);

        // Tombol
        btnSimpan = new JButton("Simpan");
        btnHapus = new JButton("Hapus");
        btnReset = new JButton("Reset");

        JPanel panelButton = new JPanel();
        panelButton.add(btnSimpan);
        panelButton.add(btnHapus);
        panelButton.add(btnReset);

        // Panel utama
        setLayout(new BorderLayout());
        add(panelForm, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);

        // Load awal
        loadSupplier();

        // Event handler
        btnSimpan.addActionListener(e -> simpanSupplier());
        btnHapus.addActionListener(e -> hapusSupplier());
        btnReset.addActionListener(e -> resetForm());

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                selectedId = (int) tableModel.getValueAt(row, 0);
                tfNama.setText(tableModel.getValueAt(row, 1).toString());
                tfKontak.setText(tableModel.getValueAt(row, 2).toString());
                tfAlamat.setText(tableModel.getValueAt(row, 3).toString());
            }
        });
    }

    private void loadSupplier() {
        tableModel.setRowCount(0);
        List<Supplier> list = controller.getAllSupplier();
        for (Supplier s : list) {
            tableModel.addRow(new Object[]{
                    s.getId(), s.getNama(), s.getKontak(), s.getAlamat()
            });
        }
    }

    private void simpanSupplier() {
        String nama = tfNama.getText();
        String kontak = tfKontak.getText();
        String alamat = tfAlamat.getText();

        if (nama.isEmpty() || kontak.isEmpty() || alamat.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi.");
            return;
        }

        Supplier s = new Supplier(selectedId, nama, kontak, alamat);
        controller.simpan(s);
        loadSupplier();
        resetForm();
    }

    private void hapusSupplier() {
        if (selectedId == 0) {
            JOptionPane.showMessageDialog(this, "Pilih supplier yang ingin dihapus.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus?");
        if (confirm == JOptionPane.YES_OPTION) {
            controller.hapus(selectedId);
            loadSupplier();
            resetForm();
        }
    }

    private void resetForm() {
        selectedId = 0;
        tfNama.setText("");
        tfKontak.setText("");
        tfAlamat.setText("");
        table.clearSelection();
    }
}