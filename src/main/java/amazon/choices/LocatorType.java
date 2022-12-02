package amazon.choices;

public enum LocatorType {

    Xpath("xpath"),
    ID("id"),
    ClassName("classname"),
    TagName("tagname"),
    LinkText("linktext"),
    PartialLinkText("partiallinktext"),
    CSS("css");

    private String value;

    public String getValue() {
        return value;
    }

    LocatorType(String value) {
        this.value = value;
    }

}
