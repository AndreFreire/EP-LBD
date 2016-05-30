package Viwer;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UsuarioViwer extends JFrame {
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsuarioViwer usuarioViwer = new UsuarioViwer();					
					usuarioViwer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public UsuarioViwer() {
		getContentPane().setLayout(null);
		
		JLabel lblListagemDeUsuarios = new JLabel("Listagem de usuarios");
		lblListagemDeUsuarios.setBounds(137, 0, 153, 54);
		getContentPane().add(lblListagemDeUsuarios);
		
		table = new JTable();
		table.setBounds(0, 41, 450, 136);
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
