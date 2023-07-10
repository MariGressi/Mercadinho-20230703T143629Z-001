/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexaoClasse;


import classes.Clientes;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ClientesDAO {
    
    public void cria(Clientes cliente) {
        Connection conexao = Conexao.getConnection();
        PreparedStatement statement = null;

        try {
            
            statement = conexao.prepareStatement("INSERT INTO Clientes (CPF, NOME, ENDERECO, SENHA) Values (?, ?, ?, ?)");
            statement.setString(1, cliente.getCPF());
            statement.setString(2,cliente.getNome());
            statement.setString(3, cliente.getEndereco());
            
            
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente Salvo Com Sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar Cliente!");
            Logger.getLogger(FuncionariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Conexao.fecharConexao(conexao, statement);
        }
    }

    public Clientes buscaClienteCPF(String CPF){
        Connection conexao = Conexao.getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        Clientes cliente = null; 

        try {
            
            statement = conexao.prepareStatement("SELECT * FROM clientes where CPF = ?");
            statement.setString(1, CPF);
            
            result = statement.executeQuery();

            while(result.next()){
                
                cliente = new Clientes(result.getString("Nome"), 
                                        result.getString("CPF"),
                                        result.getString("Endereco"));

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro Ao Carregar Produtos");
        }finally{
            Conexao.fecharConexao(conexao, statement, result);
        }

        return cliente;
    }
    
    public ArrayList<Clientes> buscaClientesNome(String nome){
        Connection conexao = Conexao.getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        Clientes cliente; 
        ArrayList<Clientes> clientes = new ArrayList<>();

        try {
            
            statement = conexao.prepareStatement("SELECT * FROM clientes where nome = ?");
            statement.setString(1, nome);
            
            result = statement.executeQuery();

            while(result.next()){
                
                cliente = new Clientes(result.getString("Nome"), 
                                        result.getString("CPF"),
                                        result.getString("Endereco"));
                
                clientes.add(cliente);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro Ao Carregar Produtos");
        }finally{
            Conexao.fecharConexao(conexao, statement, result);
        }

        return clientes;
    }
    
    public void atualizarDadosCliente(Clientes cliente){
        Connection conexao = Conexao.getConnection();
        PreparedStatement statement = null;
        
       
        try {
            statement = conexao.prepareStatement("UPDATE clientes SET NOME = ?, ENDERECO = ? WHERE CPF = ?");
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getEndereco());
            statement.setString(3, cliente.getCPF());
            
            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            Conexao.fecharConexao(conexao, statement);
        }
    }
    
    public void excluirCliente(Clientes cliente){
        
         Connection conexao = Conexao.getConnection();
         PreparedStatement statement = null;

        try {
            statement = conexao.prepareStatement("DELETE FROM clientes WHERE CPF = ?");
            statement.setString(1, cliente.getCPF());

            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            Conexao.fecharConexao(conexao, statement);
        }

    }
 
}
