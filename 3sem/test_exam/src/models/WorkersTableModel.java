package models;

import essenses.Worker;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class WorkersTableModel extends AbstractTableModel {
    WorkersModel workersModel;

    public WorkersTableModel() {
        workersModel = new WorkersModel();
    }

    public WorkersTableModel(WorkersModel workersModel) {
        this.workersModel = workersModel;
    }

    @Override
    public int getRowCount() {
        return workersModel.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex){
            case 0: return "Фамилия";
            case 1: return "Должность";
            case 2: return "Зарплата";
            case 3: return "Квалификация";
        }

        return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
            case 1:
                return String.class;
            case 2:
            case 3:
                return int.class;
        }

        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0: return workersModel.get(rowIndex).getSurname();
            case 1: return workersModel.get(rowIndex).getPost();
            case 2: return workersModel.get(rowIndex).getSalary();
            case 3: return workersModel.get(rowIndex).calcSkill();
        }

        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    public void clear(){
        workersModel.clear();
    }
    public void add(Worker worker){
        workersModel.add(worker);
    }
}
