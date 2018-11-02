package swingmvclab;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/*
 * A hallgatók adatait tároló osztály.
 */
public class StudentData extends AbstractTableModel{

    /*
     * Ez a tagváltozó tárolja a hallgatói adatokat.
     * NE MÓDOSÍTSD!
     */
    List<Student> students = new ArrayList<Student>();
    List<String> column_names = new ArrayList<String>();
    List<Class<?>> column_classes = new ArrayList<Class<?>>();
    
    public StudentData() {
    	column_names.add("Név");
    	column_names.add("Neptun");
    	column_names.add("Aláírás");
    	column_names.add("Jegy");
    	
    	column_classes.add(String.class);
    	column_classes.add(String.class);
    	column_classes.add(Boolean.class);
    	column_classes.add(Integer.class);
    }

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		return students.size();
	}
	
	@Override
	public Class<?> getColumnClass(int column) {
		return column_classes.get(column);
	}
	
	@Override
	public String getColumnName(int column) {
		return column_names.get(column);
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		if (column > 1) return true;
		return false;
	}
	
	@Override
	public void setValueAt(Object value, int row, int column) {
		if (column==2) {
			students.get(row).setSignature((Boolean)value);
		}
		else if (column==3) {
			students.get(row).setGrade((Integer)value);
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Student student = students.get(rowIndex);
		switch(columnIndex) {
			case 0: return student.getName();
			case 1: return student.getNeptun();
			case 2: return student.hasSignature();
			default: return student.getGrade();
		}
	}
	
	public void addStudent(String name, String neptun) {
		students.add(new Student(name, neptun, false, 0));
		
		this.fireTableRowsInserted(students.size() - 1, students.size() - 1);
	}
    
}
