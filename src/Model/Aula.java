package Model;

public class Aula {
	
	private long idAula;
	private long idSalaFK;
	private long rgMonitorFK;
	private String descricao;
	private String dia_hora;
	
	public long getIdAula() {
		return this.idAula;
	}
	public void setIdAula(long idAula) {
		this.idAula = idAula;
	}
	public long getIdSalaFK() {
		return this.idSalaFK;
	}
	public void setIdSalaFK(long idSalaFK) {
		this.idSalaFK = idSalaFK;
	}
	public long getRgMonitorFK() {
		return this.rgMonitorFK;
	}
	public void setRgMonitorFK(long rgMonitorFK) {
		this.rgMonitorFK = rgMonitorFK;
	}
	public String getDescricao() {
		return this.descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDia_hora() {
		return this.dia_hora;
	}
	public void setDia_hora(String dia_hora) {
		this.dia_hora = dia_hora;
	}
}
