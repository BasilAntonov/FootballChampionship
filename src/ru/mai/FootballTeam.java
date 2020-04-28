package ru.mai;

public class FootballTeam implements Comparable<FootballTeam> {

    private String name;
    private int numGame;
    private Integer points;

    FootballTeam(String name, int numGame, int points){
        this.name=name;
        this.numGame=numGame;
        this.points=points;
    }

    public String getName() {
        return name;
    }

    public int getNumGame() {
        return numGame;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public int compareTo(FootballTeam o) {
        int result = -this.points.compareTo(o.points);

        if (result == 0) {
            result = this.name.compareTo(o.name);
        }

        return result;
    }
}