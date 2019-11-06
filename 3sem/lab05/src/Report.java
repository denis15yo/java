import java.util.ArrayList;

public class Report {
    private ArrayList<ComputationalStep> computationalSteps;

    public Report(){
        computationalSteps = new ArrayList<>();
    }

    public ArrayList<ComputationalStep> getComputationalSteps() {
        return computationalSteps;
    }

    public void addData(ComputationalStep step){
        computationalSteps.add(step);
    }
    public int size(){
        return computationalSteps.size();
    }
    public ComputationalStep get(int index){
        return computationalSteps.get(index);
    }
}
