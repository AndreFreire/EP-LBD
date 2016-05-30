package DAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Reserva;
import Model.Socio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SocioDAO {
	
	private Connection con; //Conexão com o bd
	
	public SocioDAO(){
		this.con = new ConnectionFactory().getConnection();
	}
	
	public void delete(Socio socio){
		String sql = "DELETE FROM socio WHERE idSocio = ?";
		try{
			//Inicializa o statement
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setLong(1, socio.getIdSocio());	
			
			stmt.execute();
			stmt.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void insert(Socio socio){
		String sql = "INSERT INTO socio (idSocio, nome, endereco, telefone, profissao, dadosBancarios) VALUES (?, ?, ?, ?, ?, ?)";
		try{
			//Inicializa o statement
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setLong(1, socio.getIdSocio());
			stmt.setString(2, socio.getNome());
			stmt.setString(3, socio.getEndereco());
			stmt.setString(4, socio.getTelefone());
			stmt.setString(5, socio.getProfissao());
			stmt.setLong(6, socio.getDadosBancarios());
			
			stmt.execute();
			stmt.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Socio> getListAll(){
		try{
			List<Socio> socios = new ArrayList<Socio>();
			PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM socio");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				//Cria objeto socio
				Socio socio = new Socio();
				socio.setIdSocio(rs.getLong("idSocio"));
				socio.setNome(rs.getString("nome"));
				socio.setEndereco(rs.getString("endereco"));
				socio.setTelefone(rs.getString("telefone"));
				socio.setProfissao(rs.getString("profissao"));
				socio.setDadosBancarios(rs.getLong("dadosBancarios"));
				
				socios.add(socio);
			}
			rs.close();
			stmt.close();	
			return socios;
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public List<Socio> getMemberUnusedReserve(){
		try{
			//System.out.println(socio.getReserva().getData());
			
			List<Socio> socios = new ArrayList<Socio>();
			PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM socio INNER JOIN reserva ON (socio.idSocio = reserva.idSocioFK) WHERE reserva.usada = 0");
			
			//stmt.setLong(1, reserva.getIdSalaFK());
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				//Cria objeto socio
				Socio socio = new Socio();
				socio.setIdSocio(rs.getLong("idSocio"));
				socio.setNome(rs.getString("nome"));
				socio.setEndereco(rs.getString("endereco"));
				socio.setTelefone(rs.getString("telefone"));
				socio.setProfissao(rs.getString("profissao"));
				socio.setDadosBancarios(rs.getLong("dadosBancarios"));
				
				Reserva reserva = new Reserva();
				reserva.setData(rs.getString("data"));
				socio.setReserva(reserva);
				
				socios.add(socio);
			}
			rs.close();
			stmt.close();	
			return socios;
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
}