package uk.co.proxying.cubecraft.enums;

/**
 * Created by Kieran Quigley (Proxying) on 25-Aug-16.
 */
public enum Country {

    SCOTLAND(0, "SC"),
    MEXICO(1, "MX"),
    CHINA(2, "CH"),
    JAPAN(3, "JP"),
    FRANCE(4, "FR"),
    ENGLAND(5, "EN"),
    AFRICA(6, "AF");

    int identifier;
    String tag;

    Country(int identifier, String tag) {
        this.identifier = identifier;
        this.tag = tag;
    }
}
