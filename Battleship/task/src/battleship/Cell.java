package battleship;

public class Cell {
    String value;
    boolean mark;
    boolean hit;
    String name;
    String fog;
    int length;

    public Cell() {
        mark = false;
        value = "~";
        fog = "~";
        length = -1;
        hit = true;
    }

    public void mark() {
        mark = true;
        value = "O";
    }

    public void unmark() {
        mark = false;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFog() {
        return fog;
    }

    public void setFog(String fog) {
        this.fog = fog;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }
}
