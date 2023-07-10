package classes;

public class Funcionarios {

    //------------------------------------------- Atributos ---------------------------------------------------------//

    public static int numFuncionarios;

    private String nome;
    private String matricula;
    private String cargo;
    private float salario;
    private String senha;

    //----------------------------------------- Fim Atributos -------------------------------------------------------//


    //------------------------------------------- Construtor --------------------------------------------------------//

    public Funcionarios(String nome, String cargo, float salario){
        this.nome = nome;
        this.matricula = ("1000" + numFuncionarios);
        this.cargo = cargo;
        this.salario = salario;
        this.senha = this.getMatricula();

    }
    public Funcionarios(){
    
    }

    //------------------------------------------ Fim Construtor ----------------------------------------------------//


    //---------------------------------------- Getters and Setters -------------------------------------------------//

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    //-------------------------------------- Fim Getters and Setters -----------------------------------------------//

}
