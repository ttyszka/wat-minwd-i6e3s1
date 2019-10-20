public enum PropertyType {
    DOM("dom"),MIESZKANIE("mieszkanie");

    String type;

    PropertyType(String type) {
        this.type = type;
    }

    public static PropertyType getPropertyType(String type){
        switch (type){
            case "dom":
                return DOM;
            case "mieszkanie":
                return MIESZKANIE;
        }
        return null;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
