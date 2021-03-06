package DAO;
import java.sql.Connection;
import java.util.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Model.Reserva;
import Model.Sala;
import Model.Socio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReservaDAO {
	
	private Connection con; //Conexão com o bd
	
	public ReservaDAO(){
		this.con = new ConnectionFactory().getConnection();
	}
	
	public void insert(Reserva reserva){
		boolean alreadyIn = false;			
		try{
			//Verifica se já existe alguma reserva para a data
			PreparedStatement stmt = this.con.prepareStatement(""
					+ "SELECT * "
					+ "FROM reserva "
					+ "ORDER BY idSalaFK ASC");
			ResultSet rs = stmt.executeQuery();
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			while(rs.next() && !alreadyIn){
				String dateString = df.format(rs.getTimestamp("data"));				
				if(reserva.getData() == dateString){
					alreadyIn = true;
				}
			}
			
			if(alreadyIn != true){
				String sql = "INSERT INTO reserva (idSalaFK, idSocioFK, data, usada) VALUES (?, ?, ?, 0)";
				PreparedStatement stmt1 = this.con.prepareStatement(sql);
					
				stmt1.setLong(1, reserva.getSala().getIdSala());
				stmt1.setLong(2, reserva.getSocio().getIdSocio());
				stmt1.setString(3, reserva.getData());
				
				stmt1.execute();
				
				stmt1.close();
			}
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//Controle do uso das salas reservadas
	public List<Reserva> getListAll(){
		try{
			List<Reserva> reservas = new ArrayList<Reserva>();
			PreparedStatement stmt = this.con.prepareStatement("SELECT * "
					+ "FROM reserva "
					+ "INNER JOIN socio ON (socio.idSocio = reserva.idSocioFK) "
					+ "INNER JOIN sala ON (reserva.idSalaFK = sala.idSala) ");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Sala sala = new Sala(rs.getLong("idSalaFK"), rs.getString("tipoSala"));
				Socio socio = new Socio(rs.getLong("idSocioFK"), rs.getString("nome"));
				Reserva reserva = new Reserva(sala, socio, rs.getString("data"));
				reservas.add(reserva);
			}
			rs.close();
			stmt.close();	
			return reservas;
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
	}

	// Lista de reservas de uma sala por dia.
	public List<Reserva> getDayList(Sala sala){
		try{
			List<Reserva> reservas = new ArrayList<Reserva>();
			PreparedStatement stmt = this.con.prepareStatement(""
					+ "SELECT idSocioFK, idSalaFK, DAYOFMONTH(data) "
					+ "FROM reserva "
					+ "WHERE idSalaFK = ?");
			stmt.setLong(1, sala.getIdSala());
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				//Cria objeto reserva				
				Socio socio = new Socio(rs.getLong("idSocioFK"), rs.getString("nome"));
				Reserva reserva = new Reserva(sala, socio, rs.getString("DAYOFMONTH(data)"));
				reservas.add(reserva);
			}
			rs.close();
			stmt.close();	
			return reservas;
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	//Listar os usuários com o maior número de reservas por mês.
	public List<Reserva> getReserveMonth(int monthNumber){
		try{
			List<Reserva> reservas = new ArrayList<Reserva>();
			PreparedStatement stmt = this.con.prepareStatement(""
					+ "SELECT nome, idSalaFK, idSocioFK, data, max(counted) "
					+ "FROM ("
						+ "SELECT nome, idSalaFK, idSocioFK, data, count(idSocioFK) as counted "
						+ "FROM reserva "
						+ "INNER JOIN socio ON (socio.idSocio = reserva.idSocioFK) "
						+ "INNER JOIN sala ON (reserva.idSalaFK = sala.idSala) "
						+ "WHERE month(data) = ? "
						+ "GROUP BY idSocioFK"
					+ ") AS tempTable");
			
			stmt.setLong(1, monthNumber);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				//Cria objeto reserva
				Sala sala = new Sala(rs.getLong("idSalaFK"), rs.getString("tipoSala"));
				Socio socio = new Socio(rs.getLong("idSocioFK"), rs.getString("nome"));
				Reserva reserva = new Reserva(sala, socio, rs.getString("data"));
				//reserva.setQtt(rs.getLong("max(counted)"));

				reservas.add(reserva);
			}
			rs.close();
			stmt.close();	
			return reservas;
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
	

	//Liste os usuários por número de reservas não usadas.
	public List<Reserva> getMemberUnusedReserve(){
		try{
			//System.out.println(socio.getReserva().getData());
			
			List<Reserva> reservas = new ArrayList<Reserva>();

			PreparedStatement stmt = this.con.prepareStatement(""
					+ "SELECT * "
					+ "FROM socio "
					+ "INNER JOIN reserva ON (socio.idSocio = reserva.idSocioFK) "
					+ "INNER JOIN sala ON (reserva.idSalaFK = sala.idSala) "
					+ "WHERE reserva.usada = 0");
			
			//stmt.setLong(1, reserva.getIdSalaFK());
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				//Cria objeto socio

				Socio socio = new Socio(rs.getLong("idSocio"), rs.getString("nome"));
				Sala sala = new Sala(rs.getLong("idSalaFK"), rs.getString("tipoSala"));
				Reserva reserva = new Reserva(sala, socio, rs.getDate("data").toString());
				
				//socio.setReserva(reserva);
				
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
