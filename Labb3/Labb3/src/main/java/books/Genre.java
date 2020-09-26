package books;

public enum Genre {
    DRAMA(0),ROMANCE(1),CRIME(2),HORROR(3),COMEDY(4);

    public int getValue(){
        return value;
    }
    private final int value;

    Genre(int value){
        this.value = value;
    }
}
