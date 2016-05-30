package DAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Reserva;
import Model.Sala;
import Model.Socio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SocioDAO {
	
	private Connection con; //Conexão com o bd
	
	public SocioDAO(){
		this.con = new ConnectionFactory().getConnection();
	}

	public List<Socio> getListAll(){
		try{
			List<Socio> socios = new ArrayList<Socio>();
			PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM socio");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				//Cria objeto socio
				Socio socio = new Socio(rs.getLong("idSocio"), rs.getString("nome"));
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