package MAIN;

import java.sql.SQLException;

import BO.Sistema;
import DB.Conexao;
import DTO.Tabela;

public class Main {
	public static void main(String[] args) throws SQLException {

		Sistema sistema = new Sistema("localhost","3306","root","","");
		sistema.conectarBanco("mybanco");
		sistema.listarCollates("utf8");
		sistema.listarCharsets();
		//sistema.verificarCharset("utf8");
		//sistema.verificarBanco("mybanco");
		//sistema.dropSql("meubanco");
		
		//sistema.criarBancoDeDados("meubanco","utf8","utf8_bin");
		
		
		sistema.criarTabela("Pessoa");
		sistema.criarColuna("Pessoa","cod_pessoa","PRIMARY KEY","int","AUTO_INCREMNT");
		sistema.criarColuna("Pessoa","idade_pessoa","","int","");
		System.out.println(sistema.tabelaSQL());
		//sistema.insertTabela();
		/*
		sistema.criarTabela("Carro");
		sistema.criarColuna("Carro","cod_carro","PRIMARY KEY","int","AUTO_INCREMENT");
		sistema.criarColuna("Carro","cor_carro","","varchar(200)","");
		sistema.criarColuna("Carro","cod_pessoa","","int","");
		sistema.criarRelacionamento("Carro", "pessoa", "cod_pessoa");
		System.out.println(sistema.tabelaSQL());
		//sistema.insertTabela();
		*/
		
		//System.out.println(sistema.tabelaSQL());
		//System.out.println(sistema.tabelaSQL());
		//sistema.insertNovoBanco("mybanco");

		//System.out.println(sistema.insertTabela());
		//System.out.println(sistema.deletarBanco("poo2"));
		//System.out.println(sistema.insertNovoBanco("mybanco"));
		
	}
}
