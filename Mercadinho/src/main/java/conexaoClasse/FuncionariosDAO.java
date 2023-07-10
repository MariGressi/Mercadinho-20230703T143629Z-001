/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexaoClasse;

import classes.Funcionarios;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class FuncionariosDAO {

    public void cria(Funcionarios funcionario) {
        Connection conexao = Conexao.getConnection();
        PreparedStatement statement = null;

        try {

            statement = conexao.prepareStatement("INSERT INTO Funcionarios (nome, cargo, salario) Values (?, ?, ?)");
            statement.setString(1, funcionario.getNome());
            statement.setString(2, funcionario.getCargo());
            statement.setFloat(3, funcionario.getSalario());

            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Funcionário Salvo Com Sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar Funcionário!");

        } finally {
            Conexao.fecharConexao(conexao, statement);
        }
    }

    public Funcionarios VerificaLogin(String Matricula) {
        Connection conexao = Conexao.getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;

        Funcionarios verificado = new Funcionarios();

        try {
            statement = conexao.prepareStatement("SELECT * FROM funcionarios WHERE Matricula =?");
            statement.setString(1, Matricula);

            result = statement.executeQuery();

            if (result.next()) {

                verificado.setMatricula(result.getString("Matricula"));
                verificado.setNome(result.getString("Nome"));
                verificado.setCargo(result.getString("Cargo"));
                verificado.setSalario(result.getFloat("Salario"));
                verificado.setSenha(result.getString("Senha"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.fecharConexao(conexao, statement, result);
        }

        return verificado;
    }

    public void AtualizaFuncionario(Funcionarios f) {

        Connection conexao = Conexao.getConnection();
        PreparedStatement statement = null;

        try {
            statement = conexao.prepareStatement("UPDATE funcionarios SET NOME = ?, CARGO = ?, SALARIO = ? WHERE MATRICULA = ?");
            statement.setString(1, f.getNome());
            statement.setString(2, f.getCargo());
            statement.setFloat(3, f.getSalario());
            statement.setString(4, f.getMatricula());
            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            Conexao.fecharConexao(conexao, statement);
        }

    }

    public ArrayList<Funcionarios> buscaTodosFuncionarios() {
        Connection conexao = Conexao.getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Funcionarios> funcionarios = new ArrayList<>();

        try {
            statement = conexao.prepareStatement("SELECT * FROM Funcionarios");
            result = statement.executeQuery();

            while (result.next()) {
                Funcionarios novo = new Funcionarios();

                novo.setMatricula(result.getString("Matricula"));
                novo.setNome(result.getString("Nome"));
                novo.setCargo(result.getString("Cargo"));
                novo.setSalario(result.getFloat("Salario"));
                novo.setSenha(result.getString("Senha"));

                funcionarios.add(novo);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar Funcionário!");
            Logger.getLogger(FuncionariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.fecharConexao(conexao, statement, result);
        }

        return funcionarios;
    }
    
    public ArrayList<Funcionarios> buscaPeloNome(String nome){
        Connection conexao = Conexao.getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Funcionarios> funcionarios = new ArrayList<>();

        try {
            statement = conexao.prepareStatement("SELECT * FROM Funcionarios WHERE nome = ?");
            statement.setString(1, nome);
            
            result = statement.executeQuery();

            while (result.next()) {
                Funcionarios novo = new Funcionarios();

                novo.setMatricula(result.getString("Matricula"));
                novo.setNome(result.getString("Nome"));
                novo.setCargo(result.getString("Cargo"));
                novo.setSalario(result.getFloat("Salario"));
                novo.setSenha(result.getString("Senha"));

                funcionarios.add(novo);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar Funcionário!");
            Logger.getLogger(FuncionariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.fecharConexao(conexao, statement, result);
        }

        return funcionarios;
    }
    
    public ArrayList<Funcionario> buscaPeloCargo (String cargo){
    
    }
    
    public Funcionarios buscarPelaMatricula (int matricula){
    
    }

    public void ExcluiFuncionario(Funcionarios f) {

        Connection conexao = Conexao.getConnection();
        PreparedStatement statement = null;

        try {
            statement = conexao.prepareStatement("DELETE FROM funcionarios WHERE Matricula = ?");
            statement.setString(1, f.getMatricula());

            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            Conexao.fecharConexao(conexao, statement);
        }

    }
}
