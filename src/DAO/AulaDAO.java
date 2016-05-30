package DAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Aula;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AulaDAO {
	
	private Connection con; //Conexão com o bd
	
	public AulaDAO(){
		this.con = new ConnectionFactory().getConnection();
	}
	
	public void delete(Aula aula){
		String sql = "DELETE FROM aula WHERE idAula = ? AND idSalaFK = ? AND rgMonitorFK = ?";
		try{
			//Inicializa o statement
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setLong(1, aula.getIdAula());
			stmt.setLong(2, aula.getIdSalaFK());
			stmt.setLong(3, aula.getRgMonitorFK());
			
			stmt.execute();
			stmt.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void insert(Aula aula){
		String sql = "INSERT INTO aula (idSalaFK, rgMonitorFK, descricao, dia_hora) VALUES (?, ?, ?, ?)";
		try{
			//Inicializa o statement
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setLong(1, aula.getIdSalaFK());
			stmt.setLong(2, aula.getRgMonitorFK());
			stmt.setString(3, aula.getDescricao());
			stmt.setString(4, aula.getDia_hora());
			
			stmt.execute();
			stmt.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Aula> getListAll(){
		try{
			List<Aula> aulas = new ArrayList<Aula>();
			PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM aula");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				//Cria objeto aula
				Aula aula = new Aula();
				aula.setIdAula(rs.getLong("idAula"));
				aula.setIdSalaFK(rs.getLong("idSalaFK"));
				aula.setRgMonitorFK(rs.getLong("rgMonitorFK"));
				aula.setDescricao(rs.getString("descricao"));
				aula.setDia_hora(rs.getString("dia_hora"));
				
				aulas.add(aula);
			}
			rs.close();
			stmt.close();	
			return aulas;
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
}