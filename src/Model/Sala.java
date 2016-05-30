package Model;

public class Sala {
	
	private long idSala;
	private String nome;
	
	
	
	public Sala(long idSala, String nome) {
		this.idSala = idSala;
		this.nome = nome;
	}
	public long getIdSala() {
		return idSala;
	}
	public void setIdSala(long idSala) {
		this.idSala = idSala;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
