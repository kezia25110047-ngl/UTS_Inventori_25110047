package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Barang;

public class BarangDAO {

    public boolean insert(Barang b) {
        String sql = "INSERT INTO barang (kode_barang, nama_barang, kategori, stok) VALUES (?,?,?,?)";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, b.getKodeBarang());
            ps.setString(2, b.getNamaBarang());
            ps.setString(3, b.getKategori());
            ps.setInt(4, b.getStok());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Insert gagal: " + e.getMessage());
            return false;
        }
    }

    public boolean update(Barang b) {
        String sql = "UPDATE barang SET nama_barang=?, kategori=?, stok=? WHERE kode_barang=?";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, b.getNamaBarang());
            ps.setString(2, b.getKategori());
            ps.setInt(3, b.getStok());
            ps.setString(4, b.getKodeBarang());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Update gagal: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(String kode) {
        String sql = "DELETE FROM barang WHERE kode_barang=?";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, kode);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Hapus gagal: " + e.getMessage());
            return false;
        }
    }

    public List<Barang> getAll() {
        List<Barang> list = new ArrayList<>();
        String sql = "SELECT * FROM barang ORDER BY kode_barang";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Barang(rs.getString("kode_barang"),
                        rs.getString("nama_barang"),
                        rs.getString("kategori"),
                        rs.getInt("stok")));
            }
        } catch (Exception e) {
            System.out.println("Ambil data gagal: " + e.getMessage());
        }
        return list;
    }
}