package com.example.perpus.data;

public class DataBuku {
    private String buku_id,buku_judul,buku_kategori,buku_jenis,buku_penulis,
            buku_penerbit,buku_tahunterbit,buku_tanggal,buku_cover,buku_pdf,
            buku_desk,buku_status;

    public DataBuku() {
    }

    public DataBuku(String buku_id, String buku_judul, String buku_kategori, String buku_jenis, String buku_penulis, String buku_penerbit, String buku_tahunterbit, String buku_tanggal, String buku_cover, String buku_pdf, String buku_desk, String buku_status) {
        this.buku_id = buku_id;
        this.buku_judul = buku_judul;
        this.buku_kategori = buku_kategori;
        this.buku_jenis = buku_jenis;
        this.buku_penulis = buku_penulis;
        this.buku_penerbit = buku_penerbit;
        this.buku_tahunterbit = buku_tahunterbit;
        this.buku_tanggal = buku_tanggal;
        this.buku_cover = buku_cover;
        this.buku_pdf = buku_pdf;
        this.buku_desk = buku_desk;
        this.buku_status = buku_status;
    }

    public String getBuku_id() {
        return buku_id;
    }

    public void setBuku_id(String buku_id) {
        this.buku_id = buku_id;
    }

    public String getBuku_judul() {
        return buku_judul;
    }

    public void setBuku_judul(String buku_judul) {
        this.buku_judul = buku_judul;
    }

    public String getBuku_kategori() {
        return buku_kategori;
    }

    public void setBuku_kategori(String buku_kategori) {
        this.buku_kategori = buku_kategori;
    }

    public String getBuku_jenis() {
        return buku_jenis;
    }

    public void setBuku_jenis(String buku_jenis) {
        this.buku_jenis = buku_jenis;
    }

    public String getBuku_penulis() {
        return buku_penulis;
    }

    public void setBuku_penulis(String buku_penulis) {
        this.buku_penulis = buku_penulis;
    }

    public String getBuku_penerbit() {
        return buku_penerbit;
    }

    public void setBuku_penerbit(String buku_penerbit) {
        this.buku_penerbit = buku_penerbit;
    }

    public String getBuku_tahunterbit() {
        return buku_tahunterbit;
    }

    public void setBuku_tahunterbit(String buku_tahunterbit) {
        this.buku_tahunterbit = buku_tahunterbit;
    }

    public String getBuku_tanggal() {
        return buku_tanggal;
    }

    public void setBuku_tanggal(String buku_tanggal) {
        this.buku_tanggal = buku_tanggal;
    }

    public String getBuku_cover() {
        return buku_cover;
    }

    public void setBuku_cover(String buku_cover) {
        this.buku_cover = buku_cover;
    }

    public String getBuku_pdf() {
        return buku_pdf;
    }

    public void setBuku_pdf(String buku_pdf) {
        this.buku_pdf = buku_pdf;
    }

    public String getBuku_desk() {
        return buku_desk;
    }

    public void setBuku_desk(String buku_desk) {
        this.buku_desk = buku_desk;
    }

    public String getBuku_status() {
        return buku_status;
    }

    public void setBuku_status(String buku_status) {
        this.buku_status = buku_status;
    }
}

