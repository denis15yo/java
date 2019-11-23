public class Student extends Number {
    private String name;
    private int ball;

    public Student() {
    }

    public Student(String name, int ball) {
        this.name = name;
        this.ball = ball;
    }

    @Override
    public int intValue() {
        return ball;
    }

    @Override
    public long longValue() {
        return (long)ball;
    }

    @Override
    public float floatValue() {
        return (float)ball;
    }

    @Override
    public double doubleValue() {
        return (double)ball;
    }
}
