package BO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.Persistencia;
import DB.Conexao;
import DTO.Coluna;
import DTO.Relacionamento;
import DTO.Tabela;
import INTERFACE.IPersistencia;


public class Sistema  {
	private Conexao conexao;
	private Tabela tabela;

	public Sistema(String host, String port, String user,String pass, String banco) {
		Conexao conexao = new Conexao();
		conexao.setBanco("poo2").setHost("localhost").setPass("").setPort("3306").setUser("root");
		this.conexao = conexao;
	}
	
	public void conectarBanco(String banco) {
		conexao.setBanco(banco);
	}
	
	public void criarTabela(String nomeTabela) {
		Tabela tabela = new Tabela(nomeTabela);
		this.tabela = tabela;
	}
	
	public void criarColuna(String nometabela,String nomeColuna,String chavePrimaria, String tipo,String autoIncrement) {
		
		if(nometabela == tabela.getNome()) {
			Coluna coluna = null;
			
			if(chavePrimaria.equalsIgnoreCase("PRIMARY KEY") || chavePrimaria.equalsIgnoreCase("")) {
				coluna = new Coluna(nomeColuna,chavePrimaria,tipo,autoIncrement);
			}
			else {
				System.out.println("Chave primaria setado errado!");
			}
			
			if(
				(tipo.equalsIgnoreCase("int") && autoIncrement.equalsIgnoreCase("AUTO_INCREMENT")) || 
				 tipo.equalsIgnoreCase("String") || 
				(tipo.equalsIgnoreCase("int") && autoIncrement.equalsIgnoreCase(""))
			  ) 
				coluna = new Coluna(nomeColuna,chavePrimaria,tipo,autoIncrement);
			else {
				if(tipo.equalsIgnoreCase("int")) 
					coluna = new Coluna(nomeColuna,chavePrimaria,tipo,autoIncrement);
				else
					System.out.println("Tipo da coluna setado errado!");
			}
			
			
			
			tabela.addColuna(coluna);
		}
		else {
			System.out.println("Nome de tabela diferente");
		}
	}
	
	public void criarRelacionamento(String tabelaPrincipal, String tabelaRelacionado,String colunaRelacionado) {
		System.out.println(tabelaPrincipal);
		System.out.println(tabela.getNome());
		if(tabela.getNome().equalsIgnoreCase(tabelaPrincipal)) {
			System.out.println("Passou");
			Relacionamento rel = new Relacionamento(tabelaPrincipal,tabelaRelacionado,colunaRelacionado);
			tabela.addRelacionamento(rel);
		}
		else {
			System.out.println("Tabela Já existe");
		}
		
	}
	
	public void criarBancoDeDados(String banco,String charset,String collate) throws SQLException {
		Persistencia ps = new Persistencia();
		if(ps.verificarBanco(banco, conexao)) {
			System.out.println("Banco de dados com esse nome já existe");
		}
		else {	
			if(ps.criarBancoDeDados(banco, charset,collate, conexao)) System.out.println("Banco de dados criado com sucesso!");	
		}
	}
	
	public void dropSql(String banco) throws SQLException {
		String sql =  "DROP DATABASE "+banco;
		Persistencia ps = new Persistencia();
		if(ps.verificarBanco(banco, conexao)) {
			 if(ps.deleteBanco(sql, conexao))  System.out.println("Deletado com sucesso!");
		}
		System.out.println("Banco de dados não existe");
	}
	
	public String bancoSQL(String banco) {
		return "CREATE DATABASE "+banco+" DEFAULT CHARACTER SET utf8";
	}
	
	public boolean insertNovoBanco(String banco) throws SQLException {
		Persistencia ps = new Persistencia();
		return ps.insertNovoBanco(bancoSQL(banco), conexao);
	}
	
	public boolean deletarBanco(String nameBanco) throws SQLException {
		Persistencia ps = new Persistencia();
		return ps.deleteBanco(nameBanco, conexao);
	}
	
	
	public String tabelaSQL() {
		String sql = "";
		sql+= "CREATE TABLE "+tabela.getNome()+"(\n";
		List <Relacionamento> rel = tabela.getRelacionamento();
		for(int i=0; i<rel.size(); i++) {
			sql+= "  CONSTRAINT FK_" +rel.get(i).getTabelaRelacionada()
				 +" FOREIGN KEY ("+rel.get(i).getColunaRelacionada()+")\n"
				 +"  REFERENCES "+rel.get(i).getTabelaRelacionada()+"("+rel.get(i).getColunaRelacionada()+"),\n";
		}	
		List <Coluna> colunas = tabela.getColunas();
		for(int i=0;i<colunas.size(); i++) {
			if(i == colunas.size() - 1) {
				sql+= "  "+colunas.get(i).getNome()+" "+colunas.get(i).getTipo()+" "+colunas.get(i).getChavePrimaria()+" "+colunas.get(i).getRestricoes()+"\n";
			}
			else{
				sql+= "  "+colunas.get(i).getNome()+" "+colunas.get(i).getTipo()+" "+colunas.get(i).getChavePrimaria()+" "+colunas.get(i).getRestricoes()+",\n";
			}
		}
		sql+=");";
		return sql;
	}
	
	public void verificarBanco(String banco)throws SQLException  {
		Persistencia ps = new Persistencia();
		if(ps.verificarBanco(banco, conexao)) {
			System.out.println("Banco de dados existe");
		}
		else {
			System.out.println("Banco de dados nao existe");
		}
	}
	
	public boolean insertTabela() throws SQLException {
		Persistencia ps = new Persistencia();
		System.out.println(tabelaSQL());
		return ps.insertTabela(tabelaSQL(), conexao);
	}
	
	public void listarCharsets() throws SQLException {
		Persistencia ps = new Persistencia();
		System.out.println(ps.getCharsets(conexao));
	}
	
	public void verificarCharset(String charset) throws SQLException{
		Persistencia ps = new Persistencia();
		if(ps.verificarCharset(charset,conexao)) {
			System.out.println("Existe esse charset");
		}else {
			System.out.println("Não existe esse charset");
		}
	}

	public void listarCollates(String charset) throws SQLException {
		Persistencia ps = new Persistencia();
		System.out.println(ps.getCollate(charset,conexao));
	}

}
