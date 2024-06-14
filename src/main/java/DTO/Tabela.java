package DTO;

import java.util.ArrayList;
import java.util.List;

public class Tabela {
	private String nome;
	private List <Coluna> colunas  = new ArrayList();
	private List <Relacionamento> relacionamento = new ArrayList();
	
	public Tabela(String nome) {
		setNome(nome);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void addColuna(Coluna coluna) {
		colunas.add(coluna);
	}
	
	public List <Coluna> getColunas(){
		return this.colunas;
	}
	
	public void addRelacionamento(Relacionamento rel) {
		relacionamento.add(rel);
	}

	public List<Relacionamento> getRelacionamento() {
		return this.relacionamento ;
	}
	
}
