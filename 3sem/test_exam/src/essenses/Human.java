package essenses;

public abstract class Human {
    protected String surname;

    public Human() {
        surname = "";
    }

    public Human(String surname) {
        this.surname = surname;
    }

    public abstract int calcSkill();
}
