package models;

import essenses.AgeBounds;
import essenses.Toy;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ToysTableModel extends AbstractTableModel {
    private List<Toy> list;

    public ToysTableModel() {
        list = new ArrayList<>();
    }

    public ToysTableModel(List<Toy> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
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
            case 0 : return list.get(rowIndex).getName();
            case 1 : return list.get(rowIndex).getCost();
            case 2 : return list.get(rowIndex).getAgeBounds();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    public void addToy(Toy t){
        list.add(t);
    }

    public void setList(List<Toy> list) {
        this.list = list;
    }

    public Stream<Toy> stream(){
        return list.stream();
    }
}

