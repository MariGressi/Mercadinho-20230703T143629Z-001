/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexaoClasse;

import classes.Pedido;
import classes.Produtos;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author gabri
 */
public class PedidoDAO {
    
    public int codigoUltimoPedido(){
        Connection conexao = Conexao.getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        int maxValor = 0;
                
        try { 
            statement = conexao.prepareStatement("SELECT max(idpedido) as Ultimo from pedidos");
            
            result = statement.executeQuery();
            
            if (result.next()) {
                maxValor = result.getInt("Ultimo");
            }
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar IdPedido");
        }finally{
            Conexao.fecharConexao(conexao, statement, result);
        }
        
        return maxValor;
    }
    
    
    public void criaPedido(Pedido pedido) {
        ItensPedidoDAO itens = new ItensPedidoDAO();
        
        Connection conexao = Conexao.getConnection();
        PreparedStatement statement = null;
        
        try {
            
            statement = conexao.prepareStatement("INSERT INTO Pedidos (cpf, valortotal, atendente) Values (?, ?, ?)");
            statement.setString(1, pedido.getCPF());
            statement.setFloat(2, pedido.getValorTotal());
            statement.setInt(3, pedido.getAtendente());
            
            int idPedido = codigoUltimoPedido();
            
            for(Produtos p : pedido.getItensPedido()){
                itens.addItemPedido(p, idPedido);
            }
            
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto Salvo Com Sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar Produto!");
        }finally{
            Conexao.fecharConexao(conexao, statement);
        }  
    }
    

    
}
