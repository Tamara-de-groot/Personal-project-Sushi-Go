package sushigo.api.models;

public class PlayInputDTO {
    private int indexToPlay;
    private String owner;

    public int getIndexToPlay() {
        return indexToPlay;
    }

    public void setIndexToPlay(int indexToPlay) {
        this.indexToPlay = indexToPlay;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

}
