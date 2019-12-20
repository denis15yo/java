package essenses;

public class Tea extends Drink {
    private TeaType teaType;

    public Tea() {
        super();
        teaType = TeaType.BLACK;
    }
    public Tea(String name, int caffeine, int cost, TeaType teaType) {
        super(name, caffeine, cost);
        this.teaType = teaType;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("%-6s", teaType);
    }

    public void setTeaType(TeaType teaType) {
        this.teaType = teaType;
    }

    public TeaType getTeaType() {
        return teaType;
    }
}
