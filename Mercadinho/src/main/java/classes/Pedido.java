package classes;

import java.util.ArrayList;

public class Pedido {

    //------------------------------------------- Atributos ---------------------------------------------------------//

    private String CPF;
    private int IDPedidos;
    private float valorTotal;
    private int atendente;
    private ArrayList <Produtos> itensPedido = new ArrayList<>();

    //----------------------------------------- Fim Atributos -------------------------------------------------------//


    //------------------------------------------- Construtor --------------------------------------------------------//

    public Pedido(String CPF, int IDPedidos, float valorTotal, int atendente, ArrayList itensPedido){
        this.CPF = CPF;
        this.IDPedidos = IDPedidos;
        this.valorTotal = valorTotal;
        this.atendente = atendente;
        this.itensPedido = new ArrayList<>();
    }
    
    public Pedido(){}

    //------------------------------------------ Fim Construtor ----------------------------------------------------//


    //---------------------------------------- Getters and Setters -------------------------------------------------//

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public int getIDPedidos() {
        return IDPedidos;
    }

    public void setIDPedidos(int IDPedidos) {
        this.IDPedidos = IDPedidos;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getAtendente() {
        return atendente;
    }

    public void setAtendente(int atendente) {
        this.atendente = atendente;
    }

    public ArrayList<Produtos> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(ArrayList<Produtos> itensPedido) {
        this.itensPedido = itensPedido;
    }

    //-------------------------------------- Fim Getters and Setters -----------------------------------------------//

}
