package Viwer;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListagemReservaViwer extends JFrame {
	private JTable table;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListagemReservaViwer listagemReservaViwer = new ListagemReservaViwer();					
					listagemReservaViwer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ListagemReservaViwer() {
		getContentPane().setLayout(null);
		
		JLabel lblListagemDeUsuarios = new JLabel("Listagem de reservas");
		lblListagemDeUsuarios.setBounds(137, 0, 153, 54);
		getContentPane().add(lblListagemDeUsuarios);
		
		table = new JTable();
		table.setBounds(0, 41, 450, 154);
		getContentPane().add(table);
		
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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
	}

}
