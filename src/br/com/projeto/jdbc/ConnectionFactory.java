/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author Matheus
 */
public class ConnectionFactory {
    
    public Connection getConnection(){
       
        //Tratamento de erro
        try {
            
            //Conecta de verdade é o DriverManager
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost/BDDELICIAROYAL3",
                    "matheux",
                    "1234");
            
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
    }
    
}
