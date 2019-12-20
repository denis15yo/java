package models;

import essenses.Drink;

import javax.swing.*;

public class DrinksListModel extends DefaultListModel<Drink> {
    private DrinksModel data;

    public void setData(DrinksModel data) {
        this.data = data;
    }

    public DrinksListModel(DrinksModel data) {
        this.data = data;
    }

    @Override
    public int getSize() {
        return data.size();
    }

    @Override
    public Drink getElementAt(int index) {
        return data.get(index);
    }

    public void update(){
        fireContentsChanged(this, 0, 0);
    }

}
