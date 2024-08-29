package entity;

public class Calcado {

    private int codigoCalcado;
    private String nome;
    private String marca;
    private double valor;
    private String cor;

    public Calcado(int codigoCalcado, String nome, String marca, double valor, String cor) {
        this.codigoCalcado = codigoCalcado;
        this.nome = nome;
        this.marca = marca;
        this.valor = valor;
        this.cor = cor;
    }

    public int getCodigoCalcado() {
        return codigoCalcado;
    }

    public void setCodigoCalcado(int codigoCalcado) {
        this.codigoCalcado = codigoCalcado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
