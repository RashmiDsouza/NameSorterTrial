// import namesorter.*;
import org.junit.Test;
import static org.junit.Assert.*;

//  Tests for NameSorter
public class NameSorterTestDrive {
    /**
    * To test name sorter
    */
    public static void main(String[] args) {
        NameSorterTestDrive testSorter = new NameSorterTestDrive();
        testSorter.canSortByLastName();
        testSorter.canSortEmptyCollection();
        testSorter.canSortOneElementCollection();
    }

    /**
    * To test if name sorter can sort names in Records starting from lastName
    */
    public void canSortByLastName() {
        ArrayList<Name> actualNameRecords = new ArrayList<Name>();
        ArrayList<Name> expectedNameRecords = new ArrayList<Name>();
        actualNameRecords.add(new Name("Janet", "Parsons"));
        actualNameRecords.add(new Name("Vaughn", "Lewis"));
        actualNameRecords.add(new Name("Adonis", "Julius", "Archer"));
        actualNameRecords.add(new Name("Shelby", "Nathan", "Yoder"));
        actualNameRecords.add(new Name("Marin", "Alvarez"));
        actualNameRecords.add(new Name("London", "Lindsey"));
        actualNameRecords.add(new Name("Beau", "Tristan", "Bentley"));
        actualNameRecords.add(new Name("Leo", "Gardner"));
        actualNameRecords.add(new Name("Hunter", "Uriah Mathew", "Clarke"));
        actualNameRecords.add(new Name("Mikayla", "Lopez"));
        actualNameRecords.add(new Name("Frankie", "Conner", "Ritter"));

        Collections.sort(actualNameRecords, new NameCompare());

        expectedNameRecords.add(new Name("Marin", "Alvarez"));
        expectedNameRecords.add(new Name("Adonis", "Julius", "Archer"));
        expectedNameRecords.add(new Name("Beau", "Tristan", "Bentley"));
        expectedNameRecords.add(new Name("Hunter", "Uriah Mathew", "Clarke"));
        expectedNameRecords.add(new Name("Leo", "Gardner"));
        expectedNameRecords.add(new Name("Vaughn", "Lewis"));
        expectedNameRecords.add(new Name("London", "Lindsey"));
        expectedNameRecords.add(new Name("Mikayla", "Lopez"));
        expectedNameRecords.add(new Name("Janet", "Parsons"));
        expectedNameRecords.add(new Name("Frankie", "Conner", "Ritter"));
        expectedNameRecords.add(new Name("Shelby", "Nathan", "Yoder"));

        // assert
        Assert.assertEquals(expectedNameRecords, actualNameRecords);

        // //Prints the sorted list of names to screen.
        // for (Name personName : actualNameRecords) {
        //     System.out.println(personName.getFirstName() + " " + personName.getMidName() + " " + personName.getLastName());
        // }
    }

    /**
    * To test if name sorter can sort one element Record
    */
    public void canSortOneElementCollection() {
        ArrayList<Name> oneNameRecords = new ArrayList<Name>();
        ArrayList<Name> expectedOneNameRecords = new ArrayList<Name>();

        oneNameRecords.add(new Name("Janet", "Parsons"));

        expectedOneNameRecords.add(new Name("Janet", "Parsons"));

        Collections.sort(oneNameRecords, new NameCompare());
        // assert
        Assert.assertEquals(expectedOneNameRecords, oneNameRecords);

        // //Prints the sorted list of names to screen.
        // for (Name personName : oneNameRecords) {
        //     System.out.println(personName.getFirstName() + " " + personName.getMidName() + " " + personName.getLastName());
        // }
    }

    /**
    * To test if name sorter can sort an empty Record
    */
    public void canSortEmptyCollection() {
        ArrayList<Name> emptyNameRecords = new ArrayList<Name>();
        ArrayList<Name> expectedEmptyNameRecords = new ArrayList<Name>();

        Collections.sort(emptyNameRecords, new NameCompare());
        // assert
        Assert.assertEquals(expectedEmptyNameRecords, emptyNameRecords);

        // // Prints the sorted list of names to screen.
        // for (Name personName : emptyNameRecords) {
        //     System.out.println(personName.getFirstName() + " " + personName.getMidName() + " " + personName.getLastName());
        // }
    }

}
