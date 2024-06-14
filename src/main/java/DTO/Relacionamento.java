package DTO;

public class Relacionamento {
	private String tabelaPrincipal;
	private String tabelaRelacionada;
	private String colunaRelacionada;
	
	public Relacionamento(String tabelaPrincipal,String tabelaRelacionada,String colunaRelacionada ) {
		setColunaRelacionada(colunaRelacionada);
		setTabelaPrincipal(tabelaPrincipal);
		setTabelaRelacionada(tabelaRelacionada);
	}
	
	public String getTabelaPrincipal() {
		return tabelaPrincipal;
	}
	public void setTabelaPrincipal(String tabelaPrincipal) {
		this.tabelaPrincipal = tabelaPrincipal;
	}
	public String getTabelaRelacionada() {
		return tabelaRelacionada;
	}
	public void setTabelaRelacionada(String tabelaRelacionada) {
		this.tabelaRelacionada = tabelaRelacionada;
	}
	public String getColunaRelacionada() {
		return colunaRelacionada;
	}
	public void setColunaRelacionada(String colunaRelacionada) {
		this.colunaRelacionada = colunaRelacionada;
	}
	
	
}
