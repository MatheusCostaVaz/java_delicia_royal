/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
//import br.com.projeto.model.Clientes;
import br.com.projeto.model.Funcionarios;
import br.com.projeto.model.WebServiceCep;
import br.com.projeto.view.FrmLogin;
import br.com.projeto.view.FrmMenu;
//import br.com.projeto.model.WebServiceCep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import java.util.ArrayList;
//import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Lacmar
 */
    public class FuncionarioDAO {
    //Conexão com o banco de dados
    private Connection con;
    public FuncionarioDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    //Metodo Alterar funcionarios
    public void alterarFuncionarios(Funcionarios obj) {
        try {
            //Criar o comando sql
            String sql = "update tb_funcionarios set  nome=?, rg=?, cpf=?,email=?,"
                    + " senha=?, cargo=?, nivel_acesso=?,"
                    + " telefone=?, celular=?, cep=?, endereco=?, numero=?,"
                    + "complemento=?,bairro=?,cidade=?, estado=?  where id =?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());

            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getNivel_acesso());

            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setInt(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getUf());
            stmt.setInt(17, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterado com Sucesso");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao Alterar. Erro:" + erro);
        }
    }

    //Metodo excluir funcionario
    public void excluirFuncionarios(Funcionarios obj) {
        try {
            //Criar o comando sql
            String sql = "delete from tb_funcionarios where id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            //Executar o comando sql
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Excluido com Sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir. Erro:" + erro);
        }
    }

    //Metodo cadastrar funcionarios
    public void cadastrarFuncionarios(Funcionarios obj) {

        try {
            //Criar o comando sql
            String sql = "insert into tb_funcionarios(nome,rg,cpf,email,senha, "
                    + "cargo, nivel_acesso, telefone,celular,cep,endereco,"
                    + "numero,complemento,bairro,cidade,estado)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getNivel_acesso());
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setInt(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getUf());

            //Executar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar. Erro:" + erro);
        }

    }

    //Metodo listar funcionarios
    public List<Funcionarios> listarFuncionarios() {
        try {
            //Criar lista
            List<Funcionarios> lista = new ArrayList<>();

            //Criar comando sql, organizar e executar
            String sql = "select*from tb_funcionarios";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionarios obj = new Funcionarios();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCpf(rs.getString("cpf"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));

                lista.add(obj);
            }
            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }

    }

    //metodo buscar cliente por nome
    public List<Funcionarios> buscaFuncionarioPorNome(String nome) {
        try {
            //Criar lista
            List<Funcionarios> lista = new ArrayList<>();

            //Criar comando sql, organizar e executar
            String sql = "select*from tb_funcionarios where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionarios obj = new Funcionarios();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCpf(rs.getString("cpf"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));

                lista.add(obj);
            }
            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Não achou funcionário");
            return null;
        }

    }

    //Consultar por CPF
    public Funcionarios cosultaPorCpf(String nome) {
        try {
            String sql = "select*from tb_funcionarios where cpf = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            Funcionarios obj = new Funcionarios();

            if (rs.next()) {

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCpf(rs.getString("cpf"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
            }
            return obj;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado");
            return null;
        }
    }

    //Metodo fazer Login
    public void efetuaLogin(String cpf, String senha) {
        try {
            //SQl
            String sql = "select * from tb_funcionarios where cpf=? and senha=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                //Caso o usuario seja do tipo admin
                if (rs.getString("nivel_acesso").equals("Administrador")) {
                    FrmMenu tela = new FrmMenu();
                    tela.usuarioLogado = rs.getString("nome");
                    tela.setVisible(true);
                    
                    
                } //Caso o usuario seja do tipo limitado
                else if (rs.getString("nivel_acesso").equals("Usuário")) {
                    FrmMenu tela = new FrmMenu();
                    tela.usuarioLogado = rs.getString("nome");
                    
////                    Desabilitar os menu
                    tela.menu_CadastrarFuncionario.setVisible(false);
                    tela.menu_posicaododia.setVisible(false);
                    tela.menu_relatorio.setVisible(false);
                    tela.menu_controlefuncionarios.setVisible(false);
                    
                    tela.setVisible(true);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Dados incorretos!");
                new FrmLogin().setVisible(true);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro:" + erro);
        }
    }

    public Funcionarios buscaCep(String cep) {

        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);

        Funcionarios obj = new Funcionarios();

        if (webServiceCep.wasSuccessful()) {
            obj.setEndereco(webServiceCep.getLogradouroFull());
            obj.setBairro(webServiceCep.getBairro());
            return obj;
        } else {
            JOptionPane.showMessageDialog(null, "Erro numero: " + webServiceCep.getResulCode());
            JOptionPane.showMessageDialog(null, "Descrião do erro: " + webServiceCep.getResultText());
            return null;
        }

    }
}
