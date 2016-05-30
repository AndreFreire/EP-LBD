package DAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Reserva;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReservaDAO {
	
	private Connection con; //Conexão com o bd
	
	public ReservaDAO(){
		this.con = new ConnectionFactory().getConnection();
	}
	
	public void delete(Reserva reserva){
		String sql = "DELETE FROM reserva WHERE idSalaFK = ? AND idSocioFK = ?";
		try{
			//Inicializa o statement
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setLong(1, reserva.getIdSalaFK());
			stmt.setLong(2, reserva.getIdSocioFK());			
			
			stmt.execute();
			stmt.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void insert(Reserva reserva){
		boolean alreadyIn = false;			
		try{
			//Verifica se já existe alguma reserva para a data
			PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM reserva ORDER  BY idSalaFK ASC");
			ResultSet rs = stmt.executeQuery();
			while(rs.next() && !alreadyIn){
				if(reserva.getData() == rs.getString("data")){
					alreadyIn = true;
				}
			}
			
			if(alreadyIn == true){
				String sql = "INSERT INTO reserva (idSalaFK, idSocioFK, data) VALUES (?, ?, ?)";
				PreparedStatement stmt1 = this.con.prepareStatement(sql);
					
				stmt1.setLong(1, reserva.getIdSalaFK());
				stmt1.setLong(2, reserva.getIdSocioFK());
				stmt1.setString(3, reserva.getData());
					
				stmt1.execute();
				
				stmt1.close();
			}
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Reserva> getListAll(){
		try{
			List<Reserva> reservas = new ArrayList<Reserva>();
			PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM reserva");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				//Cria objeto reserva
				Reserva reserva = new Reserva();
				reserva.setIdSocioFK(rs.getLong("idSocioFK"));
				reservas.add(reserva);
			}
			rs.close();
			stmt.close();	
			return reservas;
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public List<Reserva> getDayList(Reserva reserva){
		try{
			List<Reserva> reservas = new ArrayList<Reserva>();
			PreparedStatement stmt = this.con.prepareStatement("SELECT idSocioFK, idSalaFK, DAYOFMONTH(data) FROM reserva WHERE idSalaFK = ?");
			stmt.setLong(1, reserva.getIdSalaFK());
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				//Cria objeto reserva
				reserva.setIdSocioFK(rs.getLong("idSocioFK"));
				reserva.setIdSalaFK(rs.getLong("idSalaFK"));
				reserva.setData(rs.getString("DAYOFMONTH(data)"));
				reservas.add(reserva);
			}
			rs.close();
			stmt.close();	
			return reservas;
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public List<Reserva> getReserveMonth(int monthNumber){
		try{
			List<Reserva> reservas = new ArrayList<Reserva>();
			PreparedStatement stmt = this.con.prepareStatement("SELECT idSalaFK, idSocioFK, data, max(counted) from ("
					+ "SELECT idSalaFK, idSocioFK, data, count(idSocioFK) as counted FROM reserva WHERE month(data) = ? GROUP BY idSocioFK"
					+ ") AS tempTable");
			
			stmt.setLong(1, monthNumber);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				//Cria objeto reserva
				Reserva reserva = new Reserva();
				reserva.setIdSocioFK(rs.getLong("idSocioFK"));
				reserva.setIdSalaFK(rs.getLong("idSalaFK"));
				reserva.setData(rs.getString("data"));
				reserva.setQtt(rs.getLong("max(counted)"));
				reservas.add(reserva);
			}
			rs.close();
			stmt.close();	
			return reservas;
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
}
