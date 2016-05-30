package Model;

public class Reserva {

	private Sala sala;
	private Socio socio;
	private String data;
	
	public Reserva(Sala sala, Socio socio, String data) {
		this.sala = sala;
		this.socio = socio;
		this.data = data;
	}
	
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public Socio getSocio() {
		return socio;
	}
	public void setSocio(Socio socio) {
		this.socio = socio;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
}
