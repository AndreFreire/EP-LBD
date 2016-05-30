package DAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Sala;
import Model.Socio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SalaDAO {
	
	private Connection con; //Conexão com o bd
	
	public SalaDAO(){
		this.con = new ConnectionFactory().getConnection();
	}
	
	public List<Sala> getListAll(){
		try{
			List<Sala> salas = new ArrayList<Sala>();
			PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM sala");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				//Cria objeto sala
				Sala sala = new Sala(rs.getLong("idSala"), rs.getString("tipoSala"));
				salas.add(sala);
			}
			rs.close();
			stmt.close();	
			return salas;
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
}