package DAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Matricula;
import Model.SalaSquash;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SalaSquashDAO {
	
	private Connection con; //Conexão com o bd
	
	public SalaSquashDAO(){
		this.con = new ConnectionFactory().getConnection();
	}
	
	public void delete(SalaSquash salaSquash){
		String sql = "DELETE FROM salaSquash WHERE idSalaFK = ?";
		try{
			//Inicializa o statement
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setLong(1, salaSquash.getIdSalaFK());
			
			stmt.execute();
			stmt.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void insert(SalaSquash salaSquash){
		String sql = "INSERT INTO salaSquash (idSalaFK, estado) VALUES (?, ?)";
		try{
			//Inicializa o statement
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setLong(1, salaSquash.getIdSalaFK());
			stmt.setString(2, salaSquash.getEstado());
			
			stmt.execute();
			stmt.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Matricula> getListAll(){
		try{
			List<Matricula> matriculas = new ArrayList<Matricula>();
			PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM matricula");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				//Cria objeto matricula
				Matricula matricula = new Matricula();
				matricula.setIdAulaFK(rs.getLong("idAulaFK"));
				matricula.setIdSocioFK(rs.getLong("idSocioFK"));
				
				matriculas.add(matricula);
			}
			rs.close();
			stmt.close();	
			return matriculas;
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
}