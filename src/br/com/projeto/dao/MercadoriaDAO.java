/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Mercadoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Matheus
 */
public class MercadoriaDAO {

    private Connection con;

    public MercadoriaDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    //Cadastrar Mercadoria
    public void cadastrarMercadoria(Mercadoria obj) {
        try {
            String sql = "insert into tb_mercadoria(nome,descricao, tipo, preco, qtd_estoque) "
                    + "values(?,?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getDescricao());
            stmt.setString(3, obj.getTipo());
            stmt.setDouble(4, obj.getPreco());
            stmt.setInt(5, obj.getQtd_estoque());

            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar. Erro:" + erro);
        }
    }

    //Alterar Cliente
    public void alterarMercadoria(Mercadoria obj) {
        try {
            //Criar o comando sql
            String sql = "update tb_mercadoria set nome=?, descricao = ?, "
                    + "tipo = ?, preco = ?, qtd_estoque = ? where id =?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getDescricao());
            stmt.setString(3, obj.getTipo());
            stmt.setDouble(4, obj.getPreco());
            stmt.setInt(5, obj.getQtd_estoque());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterado com Sucesso");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar. Erro:" + erro);
        }
    }

    //Excluir mercadoria
    public void excluirMercadoria(Mercadoria obj) {
        try {
            //Criar o comando sql
            String sql = "delete from tb_mercadoria where id=?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            //Executar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluido com Sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar. Erro:" + erro);
        }
    }
    
    
        //Metodo Listar todos as mercadoria
    public List<Mercadoria> listarMercadoria() {
        try {
            //Criar lista
            List<Mercadoria> lista = new ArrayList<>();

            //Criar comando sql, organizar e executar
            String sql = "select*from tb_mercadoria";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Mercadoria obj = new Mercadoria();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setDescricao(rs.getString("descricao"));                
                obj.setTipo(rs.getString("tipo"));
                obj.setPreco(rs.getDouble("preco"));
                obj.setQtd_estoque(rs.getInt("qtd_estoque"));

                lista.add(obj);
            }
            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }

    }
    
    
        //metodo buscar mercadoria por nome
    public List<Mercadoria> buscaClientePorNome(String nome) {
        try {
            //Criar lista
            List<Mercadoria> lista = new ArrayList<>();

            //Criar comando sql, organizar e executar
            String sql = "select*from tb_mercadoria where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Mercadoria obj = new Mercadoria();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setDescricao(rs.getString("descricao"));                
                obj.setTipo(rs.getString("tipo"));
                obj.setPreco(rs.getDouble("preco"));
                obj.setQtd_estoque(rs.getInt("qtd_estoque"));
                    
                

                lista.add(obj);
            }
            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }

    }
    
        //Consulta Mercadoria por nome
    public Mercadoria cosultaPorNome(String nome) {
        try {
            String sql = "select*from tb_mercadoria where nome = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            Mercadoria obj = new Mercadoria();

            if (rs.next()) {

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setDescricao(rs.getString("descricao"));                
                obj.setTipo(rs.getString("tipo"));
                obj.setPreco(rs.getDouble("preco"));
                obj.setQtd_estoque(rs.getInt("qtd_estoque"));
            }
            return obj;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Mercadoria não encontrado");
            return null;
        }
    }

}
