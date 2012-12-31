package dk.esmann;

public class Game {
    int id, numberOfRounds, hours, minutes;
    String name;

    public Game() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game(int id, String name, int numberOfRounds, int hours, int minutes) {
        this.id = id;
        this.name = name;
        this.numberOfRounds = numberOfRounds;
        this.hours = hours;
        this.minutes = minutes;

    }

    @Override
    public String toString() {
        return name;
    }
}
