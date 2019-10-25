package com.example.perpus.data;

public class DataPeminjaman {
    private String pinjaman_id,siswa_id,siswa_nama,buku_judul,cover,detail_pinjaman_jumlah,
    admin_id,admin_nama,pinjaman_tanggal,pinjaman_kembali,keterangan;

    public DataPeminjaman(){}
    public DataPeminjaman(String pinjaman_id, String siswa_id, String siswa_nama, String buku_judul, String cover, String detail_pinjaman_jumlah, String admin_id, String admin_nama, String pinjaman_tanggal, String pinjaman_kembali, String keterangan) {
        this.pinjaman_id = pinjaman_id;
        this.siswa_id = siswa_id;
        this.siswa_nama = siswa_nama;
        this.buku_judul = buku_judul;
        this.cover = cover;
        this.detail_pinjaman_jumlah = detail_pinjaman_jumlah;
        this.admin_id = admin_id;
        this.admin_nama = admin_nama;
        this.pinjaman_tanggal = pinjaman_tanggal;
        this.pinjaman_kembali = pinjaman_kembali;
        this.keterangan = keterangan;
    }

    public String getPinjaman_id() {
        return pinjaman_id;
    }

    public void setPinjaman_id(String pinjaman_id) {
        this.pinjaman_id = pinjaman_id;
    }

    public String getSiswa_id() {
        return siswa_id;
    }

    public void setSiswa_id(String siswa_id) {
        this.siswa_id = siswa_id;
    }

    public String getSiswa_nama() {
        return siswa_nama;
    }

    public void setSiswa_nama(String siswa_nama) {
        this.siswa_nama = siswa_nama;
    }

    public String getBuku_judul() {
        return buku_judul;
    }

    public void setBuku_judul(String buku_judul) {
        this.buku_judul = buku_judul;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDetail_pinjaman_jumlah() {
        return detail_pinjaman_jumlah;
    }

    public void setDetail_pinjaman_jumlah(String detail_pinjaman_jumlah) {
        this.detail_pinjaman_jumlah = detail_pinjaman_jumlah;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_nama() {
        return admin_nama;
    }

    public void setAdmin_nama(String admin_nama) {
        this.admin_nama = admin_nama;
    }

    public String getPinjaman_tanggal() {
        return pinjaman_tanggal;
    }

    public void setPinjaman_tanggal(String pinjaman_tanggal) {
        this.pinjaman_tanggal = pinjaman_tanggal;
    }

    public String getPinjaman_kembali() {
        return pinjaman_kembali;
    }

    public void setPinjaman_kembali(String pinjaman_kembali) {
        this.pinjaman_kembali = pinjaman_kembali;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
