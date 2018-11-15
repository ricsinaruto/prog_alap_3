package swingmvclab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

/*
 * A megjelen�tend� ablakunk oszt�lya.
 */
public class StudentFrame extends JFrame {
	JTextField name_field;
    JTextField neptun_field;
    
    /*
     * Ebben az objektumban vannak a hallgat�i adatok.
     * A program indul�s ut�n bet�lti az adatokat f�jlb�l, bez�r�skor pedig kimenti oda.
     * 
     * NE M�DOS�TSD!
     */
    private StudentData data;
    
    /*
     * Itt hozzuk l�tre �s adjuk hozz� az ablakunkhoz a k�l�nb�z� komponenseket
     * (t�bl�zat, beviteli mez�, gomb).
     */
    private void initComponents() {
        this.setLayout(new BorderLayout());
        JTable table = new JTable(data);
        //table.setShowGrid(false);
        JScrollPane scrollpane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        table.setDefaultRenderer(String.class, new StudentTableCellRenderer(table.getDefaultRenderer(String.class)));
        table.setDefaultRenderer(Boolean.class, new StudentTableCellRenderer(table.getDefaultRenderer(Boolean.class)));
        table.setDefaultRenderer(Integer.class, new StudentTableCellRenderer(table.getDefaultRenderer(Integer.class)));
        
        this.add(scrollpane, BorderLayout.CENTER);
        
        // Add jpanel for inserting.
        name_field = new JTextField(20);
        neptun_field = new JTextField(6);
        JButton add_line = new JButton("Felvesz");
        add_line.addActionListener(new addButtonListener());
        
        JPanel lower_panel = new JPanel();
        lower_panel.add(new JLabel("N�v:"));
        lower_panel.add(name_field);
        lower_panel.add(new JLabel("Neptun:"));
        lower_panel.add(neptun_field);
        lower_panel.add(add_line);
        
        this.add(lower_panel, BorderLayout.SOUTH);
        
        // ...
    }
    
    public class StudentTableCellRenderer implements TableCellRenderer {
    	private TableCellRenderer renderer;
    	
    	public StudentTableCellRenderer(TableCellRenderer defRenderer) {
    		this.renderer = defRenderer;
    	}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			Component component = renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			int index = table.getRowSorter().convertRowIndexToModel(row);
			
			if (data.students.get(index).hasSignature() && data.students.get(index).getGrade() > 1) {
				component.setBackground(new Color(0, 255, 0));
			}
			else component.setBackground(new Color(255, 0, 0));
			
			return component;
		}
    	
    }
    
    public class addButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			data.addStudent(name_field.getText(), neptun_field.getText());
			
		}
    	
    }

    /*
     * Az ablak konstruktora.
     * 
     * NE M�DOS�TSD!
     */
    @SuppressWarnings("unchecked")
    public StudentFrame() {
        super("Hallgat�i nyilv�ntart�s");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Indul�skor bet�ltj�k az adatokat
        try {
            data = new StudentData();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students.dat"));
            data.students = (List<Student>)ois.readObject();
            ois.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        // Bez�r�skor mentj�k az adatokat
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.dat"));
                    oos.writeObject(data.students);
                    oos.close();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Fel�p�tj�k az ablakot
        setMinimumSize(new Dimension(500, 200));
        initComponents();
    }

    /*
     * A program bel�p�si pontja.
     * 
     * NE M�DOS�TSD!
     */
    public static void main(String[] args) {
        // Megjelen�tj�k az ablakot
        StudentFrame sf = new StudentFrame();
        sf.setVisible(true);
    }
}
