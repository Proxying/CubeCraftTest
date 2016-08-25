package uk.co.proxying.cubecraft.objects;

import uk.co.proxying.cubecraft.enums.Country;

/**
 * Created by Kieran Quigley (Proxying) on 25-Aug-16.
 */
public class Customer {

    private String name;
    private Country country;
    private int age;

    public Customer(String name, Country country, int age) {
        this.name = name;
        this.country = country;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    public int getAge() {
        return age;
    }
}
