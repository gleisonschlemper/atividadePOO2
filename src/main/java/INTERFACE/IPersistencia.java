package INTERFACE;

import java.sql.SQLException;
import java.util.List;

import DB.Conexao;
import DTO.Coluna;
import DTO.Tabela;

public interface IPersistencia {
	public boolean insertTabela(String sql, Conexao conexao) throws SQLException;
	public boolean insertNovoBanco(String sql, Conexao conexao) throws SQLException;
	public boolean deleteBanco(String sql, Conexao conexao) throws SQLException;
	public String getCharsets(Conexao conexao)throws SQLException;
	public boolean verificarCharset(String charset,Conexao conexao) throws SQLException;
	public String getCollate(String charset,Conexao conexao) throws SQLException;
	public boolean verificarBanco(String banco,Conexao conexao) throws SQLException;
	public boolean criarBancoDeDados(String banco,String charset,String collate,Conexao conexao) throws SQLException;
}
