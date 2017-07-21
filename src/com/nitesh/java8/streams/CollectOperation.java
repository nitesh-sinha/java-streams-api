package com.nitesh.java8.streams;

import com.nitesh.java8.streams.models.Person;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
public class CollectOperation {

    public static void main(String[] args) {

        List<Person> persons = Arrays.asList(new Person("John", 20),
                new Person("Katy", 25),
                new Person("Jim", 40),
                new Person("Nate", 25));

        List<Person> filtered = persons.stream()
                .filter(p -> p.getAge() <= 25)
                .collect(Collectors.toList());

        System.out.println(filtered); // [John, Katy, Nate]


        /**
         * Grouping persons by age

         * Output:

         Age: 20, Person: [John]
         Age: 40, Person: [Jim]
         Age: 25, Person: [Katy, Nate]
         */
        Map<Integer, List<Person>> groupedPersons = persons.stream().collect(Collectors.groupingBy(
                p -> p.getAge()
        ));
        groupedPersons.forEach((age, p) ->
                   System.out.printf("Age: %d, Person: %s \n", age, p));


        /**
         * Get average age of persons
         */
        double avgAge = persons.stream().collect(Collectors.averagingInt(p -> p.getAge()));
        System.out.println("Average age: " + avgAge); // Average age: 27.5


        /**
         * Get Summary statistics
         */
        IntSummaryStatistics summary = persons.stream().collect(Collectors.summarizingInt(p -> p.getAge()));
        System.out.println("Summary statistics: " + summary); // Summary statistics: IntSummaryStatistics{count=4, sum=110, min=20, average=27.500000, max=40}


        /**
         * Find people above the age of 25
         */
        String filteredPeople = persons.stream().
                filter(p -> p.getAge()>20).
                map(p -> p.getName()).
                collect(Collectors.joining(" and ", "In US, ", " are above age of 25"));
        System.out.println(filteredPeople); // In US, Katy and Jim and Nate are above age of 25


        /**
         * Create a map with Collectors API
         */
        Map<Integer, String> personsMap = persons.stream().collect(Collectors.toMap(
                p -> p.getAge(),
                p -> p.getName(),
                (n1, n2) -> n1 + ";" + n2 // if keys are same for two entries in map, use this mergeFunction to combine the values into a single entry
        ));
        System.out.println(personsMap); // {20=John, 40=Jim, 25=Katy;Nate}
    }
}
