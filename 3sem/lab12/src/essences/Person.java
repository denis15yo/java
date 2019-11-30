package essences;

import enums.Gender;

public abstract class Person implements Comparable<Person>{
    String surname;
    int age;
    Gender gender;

    Person() {
    }
    Person(String surname, int age, Gender gender) {
        this.surname = surname;
        this.age = age;
        this.gender = gender;
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
                gender == person.gender;
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

        return gender.compareTo(o.gender);
    }
}

