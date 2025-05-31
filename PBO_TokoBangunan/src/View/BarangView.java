/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.BarangController;
import Model.Barang;

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
    private JButton btnTambah, btnEdit, btnHapus;

    public BarangView() {
        setTitle("Inventaris Toko Bangunan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(new String[]{"ID", "Nama", "Kategori", "Satuan", "Stok", "Beli", "Jual"}, 0);
        table = new JTable(tableModel);
        JPanel panelAtas = new JPanel();
        btnTambah = new JButton("Tambah Barang");
        btnEdit = new JButton("Edit Barang");
        btnHapus = new JButton("Hapus Barang");
        
        panelAtas.add(btnTambah);
        panelAtas.add(btnEdit);
        panelAtas.add(btnHapus);
        loadData();
        
        add(panelAtas, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        
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
}