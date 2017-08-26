package com.example.demo.model;

public class Moto {
    private long id;
    private String marca;
    private int cc;

    public Moto(){
        id = 0;
    }

    public Moto(long id, String marca, int cc) {
        this.id = id;
        this.marca = marca;
        this.cc = cc;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getCc() {
        return cc;
    }

    public void setCc(int cc) {
        this.cc = cc;
    }

    @Override
    public String toString() {
        return "Moto{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", cc=" + cc +
                '}';
    }
}
