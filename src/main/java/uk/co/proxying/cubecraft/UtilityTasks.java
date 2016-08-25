package uk.co.proxying.cubecraft;

import uk.co.proxying.cubecraft.enums.AgeRange;
import uk.co.proxying.cubecraft.enums.Country;
import uk.co.proxying.cubecraft.objects.Customer;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by Kieran Quigley (Proxying) on 25-Aug-16.
 */
class UtilityTasks {

    private static UtilityTasks instance = null;

    static UtilityTasks getInstance() {
        if (instance == null) {
            instance = new UtilityTasks();
        }
        return instance;
    }

    //@TODO: Hmm what should I do with these?
    private Map<Country, Map<AgeRange, Customer>> customerMap = new HashMap<>();
    private List<Integer> oddNumbers = new ArrayList<>();

    void runTasks() {
        Set<Customer> customers = new HashSet<>();
        customers.add(new Customer("Proxying", Country.SCOTLAND, 21));
        customers.add(new Customer("xFinityPro", Country.AFRICA, 40));
        customers.add(new Customer("Vectorization", Country.ENGLAND, 5));
        customers.add(new Customer("Xwaffle", Country.MEXICO, 17));
        customers.add(new Customer("Dinnerbone", Country.FRANCE, 30));
        customers.add(new Customer("ChrisRoberts", Country.CHINA, 60));
        customers.add(new Customer("Shmozo", Country.JAPAN, 90));

        customerMap = getSortedMap(customers);

        IntStream integerList = new Random().ints(15);

        oddNumbers = getOddNumbers(integerList);
    }

    /**
     * @param customers The set of customers you wish to sort into a map by their country.
     * "Given a set of class type: class Customer { String name, Country country, int age },
     *  return a Map<Country, Map<AgeRange, Customer>> where AgeRange is an enum of UNDER_5, 5_TO_15, 15_TO_21, 21_TO_50, 50_TO_70, OVER_70."
     * @return The requested map.
     */
    private Map<Country, Map<AgeRange, Customer>> getSortedMap(Set<Customer> customers) {
        Map<Country, Map<AgeRange, Customer>> sortedMap = new HashMap<>();

        customers.forEach(customer -> sortedMap.put(customer.getCountry(), Collections.singletonMap(AgeRange.getByRange(customer.getAge()), customer)));

        return sortedMap;
    }

    /**
     * @param integerList The stream of Integers to check.
     * "Create a stream of random numbers and filter those which are not even and then order them and return a list."
     * @return The requested list.
     */
    private List<Integer> getOddNumbers(IntStream integerList) {
        List<Integer> oddNumbers = new ArrayList<>();
        integerList.filter(number -> (number & 1) != 0).forEach(oddNumbers::add);

        return oddNumbers;
    }
}
