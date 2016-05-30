package Model;

public class Aparelho {

	private long idAparelho;
	private long idSalaFK;
	private String descricao;
	private String estado;
	
	public long getIdAparelho() {
		return idAparelho;
	}
	public void setIdAparelho(long idAparelho) {
		this.idAparelho = idAparelho;
	}
	public long getIdSalaFK() {
		return idSalaFK;
	}
	public void setIdSalaFK(long idSalaFK) {
		this.idSalaFK = idSalaFK;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
