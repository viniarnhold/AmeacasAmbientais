package com.example.ameacasambientais;

import java.io.Serializable;

public class Ameaca implements Serializable {
    private String key;
    private String endereco;
    private String data;
    private String descricao;
    private String imagem;

    public Ameaca() {
    }

    public Ameaca(String key, String endereco, String data, String descricao, String imagem) {
        this.key = key;
        this.endereco = endereco;
        this.data = data;
        this.descricao = descricao;
        this.imagem = imagem;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    @Override
    public String toString() {
        return "Ameaca{" +
                "key=" + key +
                ", endereco='" + endereco + '\'' +
                ", data='" + data + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
