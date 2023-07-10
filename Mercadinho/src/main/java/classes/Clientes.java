package classes;

import javax.print.DocFlavor;

public class Clientes {

    //------------------------------------------- Atributos ---------------------------------------------------------//

    private String nome;
    private String CPF;
    private String endereco;

    //----------------------------------------- Fim Atributos -------------------------------------------------------//


    //------------------------------------------- Construtor --------------------------------------------------------//

    public Clientes(String nome, String CPF, String endereco){
        this.CPF=CPF;
        this.nome = nome;
        this.endereco = endereco;
    }

    //------------------------------------------ Fim Construtor ----------------------------------------------------//


    //---------------------------------------- Getters and Setters -------------------------------------------------//

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    //-------------------------------------- Fim Getters and Setters -----------------------------------------------//
}

