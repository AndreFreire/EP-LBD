import java.sql.*;
import java.util.List;

import DAO.SocioDAO;
import Model.Reserva;
import Model.Socio;
import Viwer.MainViwer;

public class TestaConexao {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		//dao.insert(reserva);
		//dao.delete(aula);
/*		
		Reserva reserva = new Reserva();
		reserva.setData("2016-01-17 10:00:00");
		Socio socio = new Socio();
		socio.setReserva(reserva);

		SocioDAO dao = new SocioDAO();
		
		//Impressão	
		List<Socio> socios = dao.getMemberUnusedReserve();
		for(Socio socio1 : socios){
			System.out.println("Sócio numero: "+ socio1.getIdSocio());
			System.out.println("Nome: "+ socio1.getNome());
			System.out.println("Endereço: "+ socio1.getEndereco());
			System.out.println("Profissão: "+ socio1.getProfissao());
			System.out.println("Sala reservada: "+ socio1.getReserva().getData());
			System.out.println("");
			System.out.println("--");
			System.out.println("");
		}
*/		
		MainViwer mv = new MainViwer();
		mv.setVisible(true);
	
	}
}
