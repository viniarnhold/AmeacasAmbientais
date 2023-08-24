package com.example.ameacasambientais;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Ameaca {
    private Long id;
    private String endereco;
    private String data;
    private String descricao;

    public Ameaca() {
    }

    public Ameaca(Long id, String endereco, String data, String descricao) {
        this.id = id;
        this.endereco = endereco;
        this.data = data;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Ameaca{" +
                "id=" + id +
                ", endereco='" + endereco + '\'' +
                ", data='" + data + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
