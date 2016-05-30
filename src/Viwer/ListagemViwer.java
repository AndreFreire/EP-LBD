package Viwer;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.mysql.jdbc.ResultSetMetaData;

import DAO.ReservaDAO;
import DAO.SocioDAO;
import Model.Reserva;
import Model.Socio;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class ListagemViwer extends JFrame {
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListagemViwer usuarioViwer = new ListagemViwer();					
					usuarioViwer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ListagemViwer() {
		getContentPane().setLayout(null);
		listagemReservas();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
	}


	private void listagemReservas(){

		JLabel lblListagemDeUsuarios = new JLabel("Listagem de usuarios");
		lblListagemDeUsuarios.setBounds(137, 0, 153, 54);
		getContentPane().add(lblListagemDeUsuarios);

		List<String> columns = new ArrayList<String>();
		List<String[]> values = new ArrayList<String[]>();

		ReservaDAO reservaDAO = new ReservaDAO();
		List<Reserva> reservaList = reservaDAO.getListAll();

		columns.add("Sala");
		columns.add("Socio");
		columns.add("Data");

		for (int i = 0; i < reservaList.size(); i++) {
			values.add(new String[] {reservaList.get(i).getSala().getNome(),
					reservaList.get(i).getSocio().getNome(),reservaList.get(i).getData()});
		}

		TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainViwer mv = new MainViwer();
				mv.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnVoltar.setBounds(12, 236, 117, 25);
		getContentPane().add(btnVoltar);

		table = new JTable(tableModel);
		table.setBounds(0, 41, 450, 136);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 41, 450, 136);
		getContentPane().add(scrollPane);

	}


}
