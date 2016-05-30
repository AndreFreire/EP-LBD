package Viwer;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.text.DateFormatter;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilCalendarModel;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
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
		
		UtilCalendarModel model = new UtilCalendarModel();
		model.setDate(1990, 1, 1);
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		final JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		getContentPane().add(datePicker, "6, 14, fill, default");
		
		Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 24); // 24 == 12 PM == 00:00:00
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        
        SpinnerDateModel model2 = new SpinnerDateModel();
        model2.setValue(calendar.getTime());

        final JSpinner spinner = new JSpinner(model2);

        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm:ss");
        DateFormatter formatter = (DateFormatter)editor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false); // this makes what you want
        formatter.setOverwriteMode(true);
     
        getContentPane().add(spinner, "6, 18, fill, default");
        
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
			long idSocio;
			long idSala;
			public void actionPerformed(ActionEvent e) {
				
				ReservaDAO reservaDAO = new ReservaDAO();

				for (Iterator iterator = socios.iterator(); iterator.hasNext(); ) {  
					   Socio c = (Socio) iterator.next();
					   if(c.getNome() == String.valueOf(associado.getSelectedItem())){
						   idSocio = c.getIdSocio();
					   }
				}
				Socio socio = new Socio(idSocio, String.valueOf(associado.getSelectedItem()));
				for (Iterator iterator = salas.iterator(); iterator.hasNext(); ) {  
					   Sala c = (Sala) iterator.next();
					   if(c.getNome() == String.valueOf(salaComboBox.getSelectedItem())){
						   idSala = c.getIdSala();
					   }
				}
				
				Sala sala = new Sala(idSala, String.valueOf(salaComboBox.getSelectedItem()));
				Date selectedHour = (Date) spinner.getValue();
				DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ");
				String dateString = df.format(selectedHour);
				Reserva reserva = new Reserva(sala, socio, dateString);
				reservaDAO.insert(reserva);
			}
		});
		getContentPane().add(btnSalvar, "6, 20");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
	}

}
