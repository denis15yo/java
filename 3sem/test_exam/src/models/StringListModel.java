package models;

import javax.swing.*;
import java.util.List;

public class StringListModel extends DefaultListModel<String> {
    private List<String> data;

    public StringListModel(List<String> data) {
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
}
