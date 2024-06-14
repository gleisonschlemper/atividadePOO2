package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.DatabaseMetaData;
import com.mysql.cj.xdevapi.Statement;

import DB.Conexao;
import DTO.Coluna;
import DTO.Tabela;
import INTERFACE.IPersistencia;

public class Persistencia implements IPersistencia{
	
	@Override
	public boolean insertTabela(String sql, Conexao conexao) throws SQLException {
	    Connection conn = conexao.ConexaoDB();   
	    PreparedStatement stmt = conn.prepareStatement(sql); // Prepara o comando SQL
	    try {
	        int result = stmt.executeUpdate(); // Executa o comando de criação de tabela
	        return result >= 0;
	    } catch (SQLException e) {
	        return false;
	    } finally {
	        stmt.close();
	        conn.close();
	    }
	}
	
	@Override
	public boolean insertNovoBanco(String sql, Conexao conexao) throws SQLException {
		Connection conn = conexao.ConexaoDB();   
	    PreparedStatement stmt = conn.prepareStatement(sql); // Prepara o comando SQL
	    try {
	        int result = stmt.executeUpdate(); // Executa o comando de criação de tabela
	        return result >= 0;
	    } catch (SQLException e) {
	        return false;
	    } finally {
	        stmt.close();
	        conn.close();
	    }
	}

	@Override
	public boolean deleteBanco(String sql, Conexao conexao) throws SQLException {
		Connection conn = conexao.ConexaoDB();   
	    PreparedStatement stmt = conn.prepareStatement(sql); // Prepara o comando SQL
	    try {
	        int result = stmt.executeUpdate(); // Executa o comando de criação de tabela
	        return result >= 0;
	    } catch (SQLException e) {
	        return false;
	    } finally {
	        stmt.close();
	        conn.close();
	    }
	}
	
	@Override
	public String getCharsets(Conexao conexao) throws SQLException {
        String dados = ""; 
		try {
			    Connection conn = conexao.ConexaoDB();
		        String querySelect = "SHOW CHARACTER SET";
		        PreparedStatement stmt = conn.prepareStatement(querySelect);
		        ResultSet rs = stmt.executeQuery();
		        while (rs.next()) {
	                String charset = rs.getString("Charset");
	                String description = rs.getString("Description");
	                dados+="Charset: "+charset+", Descricao: "+description+"\n";
	               
	            }
		        conn.close();
		        stmt.close();
		        rs.close();
		    } catch (SQLException e) {
		    	e.printStackTrace();
		    }
		return dados;
	}
	
	public boolean verificarCharset(String charset, Conexao conexao) throws SQLException {
	    boolean existeCharset = false;
	    try {
	        Connection conn = conexao.ConexaoDB();
	        String querySelect = "SELECT COUNT(*) AS count FROM INFORMATION_SCHEMA.CHARACTER_SETS WHERE CHARACTER_SET_NAME = ?";
	        PreparedStatement stmt = conn.prepareStatement(querySelect);
	        stmt.setString(1, charset);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            int count = rs.getInt("count");
	            existeCharset = (count > 0); // Se count for maior que zero, o charset existe
	        }
	        conn.close();
	        stmt.close();
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return existeCharset;
	}	
	
	@Override
	public boolean verificarBanco(String banco, Conexao conexao) throws SQLException {
	    try {
	        Connection conn = conexao.ConexaoDB();
	        String querySelect = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = ?";
	        PreparedStatement stmt = conn.prepareStatement(querySelect);
	        stmt.setString(1, banco);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) return true;
	        rs.close();
	        stmt.close();
	        conn.close();
	    } catch (SQLException e) {
	       System.out.println("Erro ao verificar o banco de dados: " + e.getMessage());
	    }
	    return false;
	}
	
	public boolean criarBancoDeDados(String banco, String charset,String collate, Conexao conexao) throws SQLException {
	    try {
	        Connection conn = conexao.ConexaoDB();
	        String queryCreate = "CREATE DATABASE " + banco + " DEFAULT CHARACTER SET " + charset+ " COLLATE "+collate;
	        PreparedStatement stmt = conn.prepareStatement(queryCreate);
	        stmt.executeUpdate();
	        stmt.close();
	        conn.close();
	        return true; // Retorna true se a criação for bem-sucedida
	    } catch (SQLException e) {
	        System.out.println("Erro ao criar o banco de dados: " + e.getMessage());
	        return false; // Retorna false em caso de erro
	    }
	}
	
	public String getCollate(String charset, Conexao conexao) throws SQLException {
	    String dados = ""; 
	    try {
	        Connection conn = conexao.ConexaoDB();
	        String querySelect = "SHOW COLLATION WHERE Charset = ?";
	        PreparedStatement stmt = conn.prepareStatement(querySelect);
	        stmt.setString(1, charset);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            String collate = rs.getString("Collation");
	            dados += "Collate: " + collate + "\n";
	        }
	        conn.close();
	        stmt.close();
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dados;
	}

	

}
