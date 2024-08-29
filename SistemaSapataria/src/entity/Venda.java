package entity;

import java.sql.Date;

public class Venda {
    private int codigoVenda;
    private Date dataVenda;
    private int codigoCalcado;
    private int codigoVendedor;
    private int quantidadeVendida;
    private double valorTotal;

    public Venda(int codigoVenda, Date dataVenda, int codigoCalcado, int codigoVendedor, int quantidadeVendida, double valorTotal) {
        this.codigoVenda = codigoVenda;
        this.dataVenda = dataVenda;
        this.codigoCalcado = codigoCalcado;
        this.codigoVendedor = codigoVendedor;
        this.quantidadeVendida = quantidadeVendida;
        this.valorTotal = valorTotal;
    }

    public int getCodigoVenda() {
        return codigoVenda;
    }

    public void setCodigoVenda(int codigoVenda) {
        this.codigoVenda = codigoVenda;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public int getCodigoCalcado() {
        return codigoCalcado;
    }

    public void setCodigoCalcado(int codigoCalcado) {
        this.codigoCalcado = codigoCalcado;
    }

    public int getCodigoVendedor() {
        return codigoVendedor;
    }

    public void setCodigoVendedor(int codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
