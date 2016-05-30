package Model;

public class Socio {

	private long idSocio;
	private String nome;
		
	public Socio(long idSocio, String nome) {
		this.idSocio = idSocio;
		this.nome = nome;
	}

	public long getIdSocio() {
		return idSocio;
	}

	public void setIdSocio(long idSocio) {
		this.idSocio = idSocio;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String toString(){
		return this.nome;
	}
		
}
