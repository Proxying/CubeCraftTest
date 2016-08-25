package uk.co.proxying.cubecraft.enums;

/**
 * Created by Kieran Quigley (Proxying) on 25-Aug-16.
 */
public enum AgeRange {

    UNDER_FIVE(0),
    FIVE_TO_FIFTEEN(1),
    FIFTEEN_TO_TWENTYONE(2),
    TWENTYONE_TO_FIFTY(3),
    FIFTY_TO_SEVENTY(4),
    OVER_SEVENTY(5);

    int identifier;

    AgeRange(int identifier) {
        this.identifier = identifier;
    }

    public static AgeRange getByRange(int age) {
        if (age < 5) {
            return UNDER_FIVE;
        } else if (age < 15) {
            return FIVE_TO_FIFTEEN;
        } else if (age < 21) {
            return FIFTEEN_TO_TWENTYONE;
        } else if (age < 50) {
            return TWENTYONE_TO_FIFTY;
        } else if (age < 70) {
            return FIFTY_TO_SEVENTY;
        } else {
            return OVER_SEVENTY;
        }
    }

}
