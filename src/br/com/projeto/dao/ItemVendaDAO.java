/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.ItensVenda;
import br.com.projeto.model.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Lacmar
 */
public class ItemVendaDAO {
    private Connection con;

    public ItemVendaDAO() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    public void cadastraItem(ItensVenda obj){
        try {
            String sql = "insert tb_itensvendas(venda_id, produto_id, qtd, subtotal)"
                    + "value(?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getVenda().getId());
            stmt.setInt(2, obj.getProdutos().getId());
            stmt.setInt(3, obj.getQtd());
            stmt.setDouble(4, obj.getSubtotal());


            stmt.execute();
            stmt.close();
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro:" + erro);
        }
    }
    
    //Metodo que lista itens vendidos por id
    
    public List<ItensVenda> listaItensPorVenda(int venda_id) {
        try {

            List<ItensVenda> lista = new ArrayList<>();
            String sql = "select i.id, p.nome, i.qtd, p.preco, i.subtotal from tb_itensvendas as i "
                    + " inner join tb_produtos as p on(i.produto_id = p.id) where i.venda_id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,venda_id);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ItensVenda item= new ItensVenda();
                Produtos prod = new Produtos();
                
                item.setId(rs.getInt("i.id"));
                prod.setNome(rs.getString("p.nome"));
                item.setQtd(rs.getInt("i.qtd")); 
                prod.setPreco(rs.getDouble("p.preco"));
                item.setSubtotal(rs.getDouble("i.subtotal")); 
                
                item.setProdutos(prod);
                
                
                lista.add(item);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }
    
    
    
}
