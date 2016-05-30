package Model;

public class Socio {

	private long idSocio;
	private String nome;
	private String endereco;
	private String telefone;
	private String profissao;
	private long dadosBancarios;
	private Reserva reserva;
	
	public Reserva getReserva() {
		return reserva;
	}
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
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
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	public long getDadosBancarios() {
		return dadosBancarios;
	}
	public void setDadosBancarios(long dadosBancarios) {
		this.dadosBancarios = dadosBancarios;
	}
	
	
}
