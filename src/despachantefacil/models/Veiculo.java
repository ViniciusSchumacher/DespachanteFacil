/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package despachantefacil.models;

import despachantefacil.models.enums.CarroceriaENUM;
import despachantefacil.models.enums.CategoriaENUM;

/**
 *
 * @author vinic
 */
public class Veiculo {
    
    private int id;
    private String placa;
    private String renavam;
    private String chassi;
    private String marcaModelo;
    private int anoFab;
    private int anoMod;
    private CategoriaENUM categoriaENUM;
    private CarroceriaENUM carroceriaENUM;
    
    private String observacoes;

    public Veiculo() {
    }

    public Veiculo(int id, String placa, String renavam, String chassi, String marcaModelo, int anoFab, int anoMod, CategoriaENUM categoriaENUM, CarroceriaENUM carroceriaENUM, String observacoes) {
        this.id = id;
        this.placa = placa;
        this.renavam = renavam;
        this.chassi = chassi;
        this.marcaModelo = marcaModelo;
        this.anoFab = anoFab;
        this.anoMod = anoMod;
        this.categoriaENUM = categoriaENUM;
        this.carroceriaENUM = carroceriaENUM;
        this.observacoes = observacoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getRenavam() {
        return renavam;
    }

    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public String getMarcaModelo() {
        return marcaModelo;
    }

    public void setMarcaModelo(String marcaModelo) {
        this.marcaModelo = marcaModelo;
    }

    public int getAnoFab() {
        return anoFab;
    }

    public void setAnoFab(int anoFab) {
        this.anoFab = anoFab;
    }

    public int getAnoMod() {
        return anoMod;
    }

    public void setAnoMod(int anoMod) {
        this.anoMod = anoMod;
    }

    public CategoriaENUM getCategoriaENUM() {
        return categoriaENUM;
    }

    public void setCategoriaENUM(CategoriaENUM categoriaENUM) {
        this.categoriaENUM = categoriaENUM;
    }

    public CarroceriaENUM getCarroceriaENUM() {
        return carroceriaENUM;
    }

    public void setCarroceriaENUM(CarroceriaENUM carroceriaENUM) {
        this.carroceriaENUM = carroceriaENUM;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

}
