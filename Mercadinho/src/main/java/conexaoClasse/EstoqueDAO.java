/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexaoClasse;

import classes.Estoque;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author resid
 */
public class EstoqueDAO {
    
    public void criaEstoque() {
        Connection conexao = Conexao.getConnection();
        PreparedStatement statement = null;

        try {
            
            statement = conexao.prepareStatement("INSERT INTO estoque () Values ()");
            
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Estoque Criado Com Sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao criar Estoque!");
        }finally{
            Conexao.fecharConexao(conexao, statement);
        }

    }
    
    public int idUltimoItem(){
        Connection conexao = Conexao.getConnection();
        PreparedStatement statement = null;
        ResultSet result;
        int maxValor = 0;
                
        try { 
            statement = conexao.prepareStatement("SELECT max(idproduto) as Ultimo from estoque");
            
            result = statement.executeQuery();
            
            if (result.next()) {
                maxValor = result.getInt("Ultimo");
            }
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar IdPedido");
        }finally{
            Conexao.fecharConexao(conexao, statement);
        }
        
        return maxValor;
    }
    
    public int buscarItemEstoqueID(int idProduto){
        Connection conexao = Conexao.getConnection();
        PreparedStatement statement = null;
        ResultSet result;
        int maxValor = 0;
                
        try { 
            statement = conexao.prepareStatement("SELECT * from estoque where idproduto = ?");
            
            result = statement.executeQuery();
            
            if (result.next()) {
                maxValor = result.getInt("Ultimo");
            }
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar IdPedido");
        }finally{
            Conexao.fecharConexao(conexao, statement);
        }
        
        return maxValor;
    }
    
    public int buscarNumEntradasProduto(int idProdutoBuscado){
        Connection conexao = Conexao.getConnection();
        PreparedStatement statement = null;
        ResultSet result;
        int numEntradas = 0;
                
        try { 
            statement = conexao.prepareStatement("SELECT entradas from estoque where idproduto = ?");
            statement.setInt(0,idProdutoBuscado);
            
            result = statement.executeQuery();
            
            if (result.next()) {
                numEntradas = result.getInt("Ultimo");
            }
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar IdPedido");
        }finally{
            Conexao.fecharConexao(conexao, statement);
        }
        
        return numEntradas;
    }
    
    public int buscarNumSaidasProduto(int idProdutoBuscado){
        Connection conexao = Conexao.getConnection();
        PreparedStatement statement = null;
        ResultSet result;
        int numSaidas = 0;
                
        try { 
            statement = conexao.prepareStatement("SELECT saidas from estoque where idproduto = ?");
            statement.setInt(0,idProdutoBuscado);
            
            result = statement.executeQuery();
            
            if (result.next()) {
                numSaidas = result.getInt("Ultimo");
            }
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar IdPedido");
        }finally{
            Conexao.fecharConexao(conexao, statement);
        }
        
        return numSaidas;
    }
    
    public ArrayList<Estoque> buscaTodoEstoque() {
        Connection conexao = Conexao.getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Estoque> estoque = new ArrayList<>(); 

        try {
            statement = conexao.prepareStatement("SELECT * FROM Estoque");
            result = statement.executeQuery();

            while(result.next()){
                
                Estoque novo = new Estoque(Integer.parseInt(result.getString("IDProduto")), 
                                            Integer.parseInt(result.getString("Entradas")),
                                            Integer.parseInt(result.getString("Saidas")),
                                            Integer.parseInt(result.getString("QtdAtual")));

                estoque.add(novo);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro Ao Recuperar Dados Estoque!");
            Logger.getLogger(FuncionariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Conexao.fecharConexao(conexao, statement, result);
        }

        return estoque;
    }
    
    public void ExcluiEstoque(Estoque e) {

         Connection conexao = Conexao.getConnection();
         PreparedStatement statement = null;

        try {
            statement = conexao.prepareStatement("DELETE FROM estoque WHERE IDProduto = ?");
            statement.setInt(1, e.getIDProduto());

            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            Conexao.fecharConexao(conexao, statement);
        }

    }
   
   public void AtualizaEstoqueGerente(Estoque e) {

        Connection conexao = Conexao.getConnection();
        PreparedStatement statement = null;
        
       
        try {
            statement = conexao.prepareStatement("UPDATE estoque SET ENTRADAS = ? WHERE IDPRODUTO = ?");
            
            statement.setInt(1, e.getEntradas() + buscarNumEntradasProduto(e.getIDProduto()));
            statement.setInt(2, e.getIDProduto());
            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            Conexao.fecharConexao(conexao, statement);
        }

    }
   
   public void AtualizaEstoqueFuncionario(Estoque e) {

        Connection conexao = Conexao.getConnection();
        PreparedStatement statement = null;
        
       
        try {
            statement = conexao.prepareStatement("UPDATE estoque SET SAIDAS = ? WHERE IDPRODUTO = ?");
            
            statement.setInt(1, e.getSaidas() + buscarNumSaidasProduto(e.getIDProduto()));
            statement.setInt(2, e.getIDProduto());
            
            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            Conexao.fecharConexao(conexao, statement);
        }

    }
    
}
