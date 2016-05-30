package Model;

public class Reserva {

	private long idSalaFK;
	private long idSocioFK;
	private String data;
	private long qtt;
	
	public long getQtt() {
		return qtt;
	}
	public void setQtt(long qtt) {
		this.qtt = qtt;
	}
	public long getIdSalaFK() {
		return idSalaFK;
	}
	public void setIdSalaFK(long idSalaFK) {
		this.idSalaFK = idSalaFK;
	}
	public long getIdSocioFK() {
		return idSocioFK;
	}
	public void setIdSocioFK(long idSocioFK) {
		this.idSocioFK = idSocioFK;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
}
