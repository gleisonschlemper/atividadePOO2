package DTO;

public class Coluna {
	private String chavePrimaria;
	private String tipo;
	private String restricoes;
	private String nome;
	
	public Coluna(String nome,String chavePrimaria, String tipo,String restricoes) {
		setChavePrimaria(chavePrimaria);
		setRestricoes(restricoes);
		setTipo(tipo);
		setNome(nome);
	}
	
	public String getChavePrimaria() {
		return chavePrimaria;
	}
	
	public void setChavePrimaria(String chavePrimaria) {
		this.chavePrimaria = chavePrimaria;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getRestricoes() {
		return restricoes;
	}
	
	public void setRestricoes(String restricoes) {
		this.restricoes = restricoes;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
