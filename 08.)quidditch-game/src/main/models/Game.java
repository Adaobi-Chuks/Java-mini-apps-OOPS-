package src.main.models;

import java.util.HashMap;

public class Game {
    private HashMap<Team, Integer> scoreBoard;
    private static int gamecount;
    private static final int SNITCH_POINT = 150;
    private static final int QUAFFLE_POINT = 10;

    public Game(Team home, Team away) {
        this.scoreBoard = new HashMap<Team, Integer>();
        scoreBoard.put(new Team(home), 0);
        scoreBoard.put(new Team(away), 0);
        gamecount++;
    }

    public Integer getScore(Team team) {
        return this.scoreBoard.get(team);
    }

    public void setScore(Team team, Integer score) {
        if(team == null) {
            throw new IllegalArgumentException("Can notadd null to the score board");
        }
        scoreBoard.put(team, score);
    }

    public Team getTeam(String name) {
        return this.scoreBoard.keySet().stream()
        .filter((key) -> key.getHouse().equals(name))
        .findFirst()
        .orElse(null);
    }

    public static int getGamecount() {
        return gamecount;
    }

    public static int getQuafflePoint() {
        return QUAFFLE_POINT;
    }

    public static int getSnitchPoint() {
        return SNITCH_POINT;
    }

    public String getPlaceholder(String play) {
        return play.substring(play.indexOf("<") + 1, play.indexOf(">"));
    }

    public String replacePlaceholder(String play, String placeholder, String value) {
        return play.replace("<" + placeholder + ">", value);
    }

    public void quaffleScore(Team team) {
        this.setScore(team, this.getScore(team) + QUAFFLE_POINT);
    }

    public void catchSnitch(Team team) {
        this.setScore(team, this.getScore(team) + SNITCH_POINT);
    }

    public String simulate(String play) {
        String placeholder = getPlaceholder(play);
        Team team = getRandomTeam();
        String value = "";

        if(placeholder.equals(Team.getPositionChaser())) {
            quaffleScore(team);
            value = replacePlaceholder(play, placeholder, team.getChasers()[random(team.getChasers().length)]);
        } else if(placeholder.equals(Team.getPositionSeeker())) {
            catchSnitch(team);
            value = replacePlaceholder(play, placeholder, team.getSeeker());
        } else if(placeholder.equals(Team.getPositionKeeper())) {
            value = replacePlaceholder(play, placeholder, team.getKeeper());
        }
        return value;
    }
    
    public Team getRandomTeam() {
        Object[] teams = scoreBoard.keySet().toArray();
        return (Team)teams[random(teams.length)];
    }

    public int random(int range) {
        return (int) (Math.random() * range);
    }
}
