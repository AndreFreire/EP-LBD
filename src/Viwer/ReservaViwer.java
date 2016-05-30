package Viwer;
import javax.swing.JPanel;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import DAO.ReservaDAO;
import DAO.SalaDAO;
import DAO.SocioDAO;
import Model.Reserva;
import Model.Sala;
import Model.Socio;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class ReservaViwer extends JFrame {
	SocioDAO socioDAO = new SocioDAO();
	SalaDAO salaDAO = new SalaDAO();
	List<Socio> socios = socioDAO.getListAll();
	List<Sala> salas = salaDAO.getListAll();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservaViwer frame = new ReservaViwer();					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ReservaViwer() {
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblFazerReserva = new JLabel("Fazer reserva");
		getContentPane().add(lblFazerReserva, "4, 2");
		
		JLabel lblSa = new JLabel("Sala:");
		getContentPane().add(lblSa, "4, 6, right, default");
		
		final JComboBox salaComboBox = new JComboBox();
		getContentPane().add(salaComboBox, "6, 6, fill, default");
		salaComboBox.setModel(new DefaultComboBoxModel(this.salas.toArray()));
		
		JLabel lblAssociado = new JLabel("Associado:");
		getContentPane().add(lblAssociado, "4, 10, right, default");
		
		final JComboBox associado = new JComboBox();
		getContentPane().add(associado, "6, 10, fill, default");
		associado.setModel(new DefaultComboBoxModel(this.socios.toArray()));
		
		JLabel lblData = new JLabel("Data:");
		getContentPane().add(lblData, "4, 14");		
		
		UtilDateModel model = new UtilDateModel();
		model.setDate(1990, 1, 1);
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		//JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, null);
		//getContentPane().add(datePicker, "6, 14, fill, default");
		
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainViwer mv = new MainViwer();
				mv.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		getContentPane().add(btnNewButton, "4, 20");
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ReservaDAO reservaDAO = new ReservaDAO();
				Socio socio = new Socio(associado.getSelectedIndex(), String.valueOf(associado.getSelectedItem()));
				Sala sala = new Sala(salaComboBox.getSelectedIndex(), String.valueOf(salaComboBox.getSelectedItem()));
				//Reserva reserva = new Reserva(socio, sala);
				//reservaDAO.insert(reserva);
			}
		});
		getContentPane().add(btnSalvar, "6, 20");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
	}

}
