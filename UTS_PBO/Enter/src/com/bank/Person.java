package com.bank;

public class Person {
    private String kode;
    private String nama;
    private String noHP;

    public Person(String kode, String nama, String noHP) {
        this.kode = kode;
        this.nama = nama;
        this.noHP = noHP;
    }

    public Person() {
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNoHP(String noHP) {
        this.noHP = noHP;
    }

    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public String getNoHP() {
        return noHP;
    }
}