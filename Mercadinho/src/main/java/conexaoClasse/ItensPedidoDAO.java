/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexaoClasse;

import classes.Produtos;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Walla
 */
public class ItensPedidoDAO {

    public void addItemPedido(Produtos produto, int idPedido) {
        Connection conexao = Conexao.getConnection();
        PreparedStatement statement = null;

        try {

            statement = conexao.prepareStatement("INSERT INTO itenspedido (idproduto, idpedido, nomeproduto, quantidade) Values (?, ?, ?, ?)");
            statement.setInt(1, produto.getIDProduto());
            statement.setInt(2, idPedido);
            statement.setString(3, produto.getNome());
            statement.setInt(4, produto.getQuantidade());

            statement.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar Produto no Pedido: " + idPedido);
        } finally {
            Conexao.fecharConexao(conexao, statement);
        }
    }

    public ArrayList<Produtos> buscaItensPedido(int idPedidoBuscado) {
        Connection conexao = Conexao.getConnection();
        PreparedStatement statement = null;
        ResultSet result;
        ArrayList<Produtos> itensPedido;
        
        try {
            statement = conexao.prepareStatement("SELECT * FROM itenspedido where idpedido=?");
            statement.setInt(0, idPedidoBuscado);
            
            result = statement.executeQuery();
            
            while(result.next()){
                Produtos item = new Produtos();
                itensPedido.add();
            }
            
        }catch (SQLException ex)
            JOptionPane.showMessageDialog(null, "Erro ao Recuperar Dados");
        }finally{
            Conexao.fecharConexao(conexao, statement, result);
        }

    }
