package DAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Aparelho;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AparelhoDAO {
	
	private Connection con; //Conexão com o bd
	
	public AparelhoDAO(){
		this.con = new ConnectionFactory().getConnection();
	}
	
	public void delete(Aparelho aparelho){
		String sql = "DELETE FROM aparelho WHERE idAparelho = ?";
		try{
			//Inicializa o statement
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setLong(1, aparelho.getIdAparelho());
					
			stmt.execute();
			stmt.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void insert(Aparelho aparelho){
		String sql = "INSERT INTO aparelho (idAparelho, idSalaFK, descricao, estado) VALUES (?, ?, ?, ?)";
		try{
			//Inicializa o statement
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setLong(1, aparelho.getIdAparelho());
			stmt.setLong(2, aparelho.getIdSalaFK());
			stmt.setString(3, aparelho.getDescricao());
			stmt.setString(4, aparelho.getEstado());
			
			stmt.execute();
			stmt.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Aparelho> getListAll(){
		try{
			List<Aparelho> aparelhos = new ArrayList<Aparelho>();
			PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM aparelho");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				//Cria objeto aparelho
				Aparelho aparelho = new Aparelho();
				aparelho.setIdAparelho(rs.getLong("idAparelho"));
				aparelho.setIdSalaFK(rs.getLong("idSalaFK"));
				aparelho.setDescricao(rs.getString("descricao"));
				aparelho.setEstado(rs.getString("estado"));
				
				aparelhos.add(aparelho);
			}
			rs.close();
			stmt.close();	
			return aparelhos;
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
}