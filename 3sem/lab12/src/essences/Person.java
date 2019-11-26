package essences;

public abstract class Person implements Comparable<Person>{
    String surname;
    int age;
    Country country;

    Person() {
    }
    Person(String surname, int age, Country country) {
        this.surname = surname;
        this.age = age;
        this.country = country;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return age == person.age &&
                surname.equals(person.surname) &&
                country == person.country;
    }

    @Override
    public int compareTo(Person o) {
        int current = surname.compareTo(o.surname);
        if(current != 0){
            return current;
        }
        current = age - o.age;
        if(current != 0){
            return current;
        }

        return country.compareTo(o.country);
    }

    public enum Country{
        BELARUS, RUSSIA, USA;
    }
}

