/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package despachantefacil.models;

import despachantefacil.models.enums.SexoENUM;

/**
 *
 * @author vinic
 */
public class Cliente {
    
    private int id;
    private String nome;
    private String cpf;
    private Endereco endereco;
    private SexoENUM sexoENUM;
    private String contato1;
    private String contato2;
    private String observacoes;
    private String filePath;

    public Cliente(int id, String nome, String cpf, Endereco endereco, SexoENUM sexoENUM, String contato1, String contato2, String observacoes) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.sexoENUM = sexoENUM;
        this.contato1 = contato1;
        this.contato2 = contato2;
        this.observacoes = observacoes;
    }

    public Cliente() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public SexoENUM getSexoENUM() {
        return sexoENUM;
    }

    public void setSexoENUM(SexoENUM sexoENUM) {
        this.sexoENUM = sexoENUM;
    }

    public String getContato1() {
        return contato1;
    }

    public void setContato1(String contato1) {
        this.contato1 = contato1;
    }

    public String getContato2() {
        return contato2;
    }

    public void setContato2(String contato2) {
        this.contato2 = contato2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    
}
