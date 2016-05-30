package Viwer;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

@SuppressWarnings("serial")
public class MainViwer extends JFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainViwer frame = new MainViwer();					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public MainViwer() {
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("44px"),
				ColumnSpec.decode("97px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("95px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("160px"),},
			new RowSpec[] {
				FormSpecs.LINE_GAP_ROWSPEC,
				RowSpec.decode("25px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JButton btnListaDeReservas = new JButton("Lista de Reservas");
		btnListaDeReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListagemReservaViwer lv = new ListagemReservaViwer();
				lv.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		
		JLabel lblTelaPrincipal = new JLabel("Tela Principal");
		add(lblTelaPrincipal, "4, 2, left, center");
		
		JButton btnNewButton = new JButton("Reservar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReservaViwer rv = new ReservaViwer();
				rv.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		add(btnNewButton, "2, 6, left, top");
		add(btnListaDeReservas, "2, 10, 3, 1, left, top");
		
		JButton btnListaDeUsuarios = new JButton("Lista de Usuarios");
		btnListaDeUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioViwer uv = new UsuarioViwer();
				uv.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		add(btnListaDeUsuarios, "2, 14, 3, 1");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
	}
}
