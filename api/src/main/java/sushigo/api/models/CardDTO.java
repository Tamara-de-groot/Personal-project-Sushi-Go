package sushigo.api.models;

import sushigo.domain.ISushiGo.CardType;

public class CardDTO {
    private CardType cardType;
    private String owner;

    public CardDTO(CardType cardType, String owner) {
        this.cardType = cardType;
        this.owner = owner;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setcardType(CardType cardType) {
        this.cardType = cardType;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
