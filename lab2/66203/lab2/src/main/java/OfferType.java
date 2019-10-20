public enum OfferType {
    SPRZEDAZ("sprzedaz"),
    WYNAJEM("wynajem");

    String type;


    OfferType(String type) {
        this.type = type;
    }

    public static OfferType getOfferType(String type){
        switch (type){
            case "sprzedaz":
                return SPRZEDAZ;
            case "wynajem":
                return WYNAJEM;
        }
        return null;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
