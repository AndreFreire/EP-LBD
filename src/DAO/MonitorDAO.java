package DAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Matricula;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MonitorDAO {
	
	private Connection con; //Conexão com o bd
	
	public MonitorDAO(){
		this.con = new ConnectionFactory().getConnection();
	}
	
	public void delete(Matricula matricula){
		String sql = "DELETE FROM matricula WHERE idAulaFK = ? AND idSocioFK = ?";
		try{
			//Inicializa o statement
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setLong(1, matricula.getIdAulaFK());
			stmt.setLong(2, matricula.getIdSocioFK());			
			
			stmt.execute();
			stmt.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void insert(Matricula matricula){
		String sql = "INSERT INTO matricula (idAulaFK, idSocioFK) VALUES (?, ?)";
		try{
			//Inicializa o statement
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setLong(1, matricula.getIdAulaFK());
			stmt.setLong(2, matricula.getIdSocioFK());
			
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