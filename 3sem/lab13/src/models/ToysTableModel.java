package models;

import essenses.AgeBounds;
import essenses.Toy;

import javax.swing.table.AbstractTableModel;

public class ToysTableModel extends AbstractTableModel {
    private ToysModel toysModel;

    public ToysTableModel(ToysModel toysModel){
        this.toysModel = toysModel;
    }

    @Override
    public int getRowCount() {
        return toysModel.getSize();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex){
            case 0 :
                return "Название";
            case 1:
                return "Стоимость";
            case 2:
                return "Возраст";
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0 : return String.class;
            case 1 : return int.class;
            case 2 : return AgeBounds.class;
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
            case 0 : return toysModel.get(rowIndex).getName();
            case 1 : return toysModel.get(rowIndex).getCost();
            case 2 : return toysModel.get(rowIndex).getAgeBounds();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    public void addToy(Toy t){
        toysModel.add(t);
    }

    public void clear(){
        toysModel.clear();
    }
}

