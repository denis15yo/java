package models;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class StringsListModel extends DefaultListModel<String> {
    private List<String> data;

    public StringsListModel() {
        data = new ArrayList<>();
    }

    public StringsListModel(List<String> data) {
        this.data = data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    @Override
    public int getSize() {
        return data.size();
    }

    @Override
    public String getElementAt(int index) {
        return data.get(index);
    }

    @Override
    public void addElement(String element) {
        data.add(element);
    }

    @Override
    public void clear() {
        data.clear();
    }

    public void update(){
        fireContentsChanged(this, 0, 0);
    }

}
