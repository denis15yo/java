package models;

import essenses.Drink;

import javax.swing.*;

public class DrinksListModel extends DefaultListModel<Drink> {
    private DrinksModel drinksModel;

    public void setDrinksModel(DrinksModel drinksModel) {
        this.drinksModel = drinksModel;
    }

    public DrinksListModel(DrinksModel drinksModel) {
        this.drinksModel = drinksModel;
    }

    @Override
    public int getSize() {
        return drinksModel.size();
    }

    @Override
    public Drink getElementAt(int index) {
        return drinksModel.get(index);
    }

    public void update(){
        fireContentsChanged(this, 0, 0);
    }

}
