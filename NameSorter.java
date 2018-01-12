// package namesorter;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Name class encapsulates firstName,midName,lastName variables and then implements
 * get and set methods to access them
 */
class Name {
    // The private instance variables
    private String firstName; // First Name
    private String midName; // Middle Names
    private String lastName; // Last Name

    /**
    * This constructor accepts argument for
    * first name.
    */
    public Name(String firstName) {
        this(firstName,"","");
    }

    /**
    * This constructor accepts arguments for the
    * first name and last name.
    */
    public Name(String firstName, String lastName) {
        this(firstName,"",lastName);
    }

    /**
    * This constructor accepts arguments for the
    * first name, Middle names and last name.
    */
    public Name(String firstName, String midName, String lastName) {
        this.firstName=firstName;
        this.midName=midName;
        this.lastName=lastName;
    }

    /**
    * constructor with no argument
    */
    public Name() {
        this("","","");
    }

    /**
    * Getter for first name
    */
    public String getFirstName() {
        return this.firstName;
    }

    /**
    * Getter for middle names
    */
    public String getMidName() {
        return this.midName;
    }

    /**
    * Getter for Last name
    */
    public String getLastName() {
        return this.lastName;
    }

    /**
    * Setter for first name
    */
    public void setFirstName(String newFirstName) {
        this.firstName = newFirstName;
    }

    /**
    * Setter for middle names
    */
    public void setMidName(String newMidName) {
        this.midName = newMidName;
    }

    /**
    * Setter for Last name
    */
    public void setLastName(String newLastName) {
        this.lastName = newLastName;
    }
}

/**
 * NameCompare Class to compare the names
 */
class NameCompare implements Comparator<Name> {

    /**
     * compares two names personName1 and personName2 starting from last part of name and
     * returns lastnameCompare = 0 if names are equal
     * lastnameCompare = negative if personName1 < personName2
     * lastnameCompare = positive if personName1 > personName2
     */
    public int compare(Name personName1, Name personName2) {
        int lastnameCompare = 0;
        String s1NamePart = "";
        String s2NamePart = "";
        int s1NamePartTrigger = 0;
        int s2NamePartTrigger = 0;
        NamePartProvider namePart = new NamePartProvider();

        // s1NamePart is either lastName or midName or firstName of personName1 which needs to be compared
        s1NamePart = namePart.getNamePartToCompare(personName1);
        s1NamePartTrigger = namePart.namePartTrigger;

        // s2NamePart is either lastName or midName or firstName of personName2 which needs to be compared
        s2NamePart = namePart.getNamePartToCompare(personName2);
        s2NamePartTrigger = namePart.namePartTrigger;

        // Comparing s1NamePart and s2NamePart
        lastnameCompare  = s1NamePart.compareTo(s2NamePart);

        // If s1NamePart and s2NamePart are equal, then get next name part and compare
        lastnameCompare  = compareNextNamePartWhenMatch(lastnameCompare,namePart,personName1,personName2,s1NamePartTrigger,s2NamePartTrigger);

        return lastnameCompare;
    }

    /**
     * Comparison starts with lastName, if they are equal then get the next part of name for personName1 and personName2 to compare
     * lastnameCompare is 0 if names are equal
     * lastnameCompare = negative if personName1 < personName2
     * lastnameCompare = positive if personName1 > personName2
     * s1NamePartTrigger = > 0 is firstName, 1 is midName, 2 is lastName for personName1
     * s2NamePartTrigger = > 0 is firstName, 1 is midName, 2 is lastName for personName2
     */
    public int compareNextNamePartWhenMatch(int lastnameCompare,NamePartProvider namePart,Name personName1,Name personName2,int s1NamePartTrigger,int s2NamePartTrigger) {
        String s1NamePart = "";
        String s2NamePart = "";
        if (lastnameCompare == 0) {
            //Both the previous compared name parts match
            if (!(s1NamePartTrigger == 0 && s2NamePartTrigger == 0)) {
                // Either both the previously compared name parts(s1NamePart and s2NamePart) are not firstName or either one of them is not the first name
                s1NamePart = namePart.getNextNamePartToCompare(personName1,s1NamePartTrigger);
                s1NamePartTrigger = namePart.namePartTrigger;
                s2NamePart = namePart.getNextNamePartToCompare(personName2,s2NamePartTrigger);
                s2NamePartTrigger = namePart.namePartTrigger;

                // Compare s1NamePart and s2NamePart
                lastnameCompare  = s1NamePart.compareTo(s2NamePart);

                if (lastnameCompare == 0) {
                    if (!(s1NamePartTrigger == 0 && s2NamePartTrigger == 0)) {
                        // If the comared name parts are still same,then recursively call the module again to get next part of name and compare
                        // recursive calling ends when both the previous compared parts are firstName
                        lastnameCompare  = compareNextNamePartWhenMatch(lastnameCompare,namePart,personName1,personName2,s1NamePartTrigger,s2NamePartTrigger);
                    }
                    return lastnameCompare;
                } else {
                    return lastnameCompare;
                }
            } else {
                //Previous compared name parts match, but the compared name parts are firstName for both personName1 and personName2,
                // No more name parts to compare
                //Hence stop comparing , and returns 0
                return lastnameCompare;
            }
        } else {
            // lastnameCompare is not 0, stop comparing the current two names and return lastnameCompare
            return lastnameCompare;
        }
    }
}

/**
 * NamePartProvider provides either lastName or midName or firstName for a PersonName to be compared
 */
class NamePartProvider {
    public static int namePartTrigger = 2;

    /**
    * To get part(FirstName, MiddleName, LastName) of name which is not empty
    * Person might have either only 1 name (FirstName), or 2 name (FirstName, LastName) or all 3 name parts (FirstName, MiddleName, LastName)
    * Depending on how many name parts a person has, getting the proper name part to start comparing
    */
    public String getNamePartToCompare(Name personName) {
        if (personName.getLastName().equals("")) {
            if (personName.getMidName().equals("")) {
                namePartTrigger = 0;
                return personName.getFirstName();
            } else {
                namePartTrigger = 1;
                return personName.getMidName();
            }
        } else {
            //gets lastname of the person
            namePartTrigger = 2;
            return personName.getLastName();
        }
    }

    /**
    * To get next name part(FirstName, MiddleName, LastName) of name which is not empty
    * Person might have either only 1 name (FirstName), or 2 name (FirstName, LastName) or all 3 name parts (FirstName, MiddleName, LastName)
    * Depending on which was the previous name part compared, gets the next name part to compare
    */
    public String getNextNamePartToCompare(Name personName,int namePartTriggerValue) {
        String NamePart = "";
        switch (namePartTriggerValue) {
            case 0:
                namePartTrigger = 0;
                NamePart = personName.getFirstName();
                break;
            case 1:
                namePartTrigger = 1;
                NamePart = personName.getMidName();
            case 2:
                namePartTrigger = 2;
                NamePart = personName.getLastName();
            default:
                namePartTrigger = 0;
                NamePart = personName.getFirstName();
        }
        return NamePart;
    }

}

/**
* ReadInputFile class to read the input files
*/
class ReadInputFile {
    /**
    * parseFile reads the input file of names, and stores the names in nameRecords
    */
    public ArrayList<Name> parseFile() {
        //Creating ArrayList to hold Name objects
        ArrayList<Name> nameRecords = new ArrayList<Name>();
        BufferedReader reader = null;
        String firstName = "";
        String midName = "";
        String lastName = "";
        String filePath = "";
        NameSorter inputArgs = new NameSorter();
        filePath = inputArgs.getInputFilePath();

        try {
            //Creating BufferedReader object to read the input text file
            reader = new BufferedReader(new FileReader("C:\\input.txt"));

            // filePath has user given input filePath
            // reader = new BufferedReader(new FileReader(filePath));

            //Reading Name records one by one
            String currentLine = reader.readLine();
            System.out.println("currentLine: " + currentLine);
            while (currentLine != null) {
                String[] currentName = currentLine.split(" ");

                if (currentName != null) {
                    int stringCount = currentName.length;
                    System.out.println("stringCount: " + stringCount);
                    if (stringCount == 1) {
                        // Only firstName is present
                        firstName = currentName[0];
                        midName = "";
                        lastName = "";
                    } else if (stringCount == 2) {
                        // Only firstName and lastName are present
                        firstName = currentName[0];
                        midName = "";
                        lastName = currentName[stringCount-1];
                    } else {
                    // All firstName, midName and lastName are present
                    //length of Name is 3 or more
                        firstName = currentName[0];
                        lastName = currentName[stringCount-1];
                        int stopLength = stringCount-1;
                        for(int x = 1; x < stopLength; x++) {
                            System.out.println("currentName: " + currentName[x] + " x: " + x);
                            midName = midName.concat(currentName[x] + " ");
                        }
                    }
                    // System.out.println("firstName: " + firstName + " midName: " + midName + " lastName: " + lastName);
                }

                //Creating Name object for every name record and adding it to ArrayList
                nameRecords.add(new Name(firstName, midName, lastName));

                currentLine = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input File not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //Closing the resource
                if (reader != null) {
                    reader.close();
                }

                // ###########Remove the following#############
                    nameRecords.add(new Name("Janet", "Parsons"));
                    nameRecords.add(new Name("Vaughn", "Lewis"));
                    nameRecords.add(new Name("Adonis", "Julius", "Archer"));
                    nameRecords.add(new Name("Shelby", "Nathan", "Yoder"));
                    nameRecords.add(new Name("Marin", "Alvarez"));
                    nameRecords.add(new Name("London", "Lindsey"));
                    nameRecords.add(new Name("Beau", "Tristan", "Bentley"));
                    nameRecords.add(new Name("Leo", "Gardner"));
                    nameRecords.add(new Name("Hunter", "Uriah Mathew", "Clarke"));
                    nameRecords.add(new Name("Mikayla", "Lopez"));
                    nameRecords.add(new Name("Frankie", "Conner", "Ritter"));
                // ############################################
                return nameRecords;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return nameRecords;

    }
}

/**
* WriteOutput class to write the output
*/
class WriteOutput {
//     private static final String FILENAME = "C:\\sorted-names-list.txt";
    private static final String FILENAME = "sorted-names-list.txt";

    /**
    * writeOutputToFileAndScreen class to write the output sorted name ArrayList to sorted-names-list.txt and to the screen
    */
    public void writeOutputToFileAndScreen(ArrayList<Name> nameRecords) {
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            fw = new FileWriter(FILENAME);
            //Creating BufferedWriter object to write into sorted-names-list text file
            bw = new BufferedWriter(fw);

            //Writing every nameRecords into sorted-names-list text file
            for (Name currentName : nameRecords) {
                bw.write(currentName.getFirstName());
                bw.write(" "+currentName.getMidName());
                bw.write(" "+currentName.getLastName());
                bw.newLine();

                //Prints the sorted list of names to screen.
                System.out.println(currentName.getFirstName() + " " + currentName.getMidName() + " " + currentName.getLastName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //Closing the resources
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}

/**
 * The main NameSorter class for sorting names by last name
 * It acts as the overall controller of the NameSorter.
 */
public class NameSorter {
    // The private instance variable
    private String inputFilePath;

    public static void main(String[] args) {
        int noError = 1;
        NameSorter inputArgs = new NameSorter();
        noError = inputArgs.checkInputArguments(args);
        if (noError == 1) {
            // noError - Only checks if input argument is provided by user

            //Creating ArrayList to hold Name objects
            ArrayList<Name> nameRecords = new ArrayList<Name>();

            ReadInputFile readNames = new ReadInputFile();
            //Parsing the input file given by users, and storing all the names from input file to nameRecords
            nameRecords = readNames.parseFile();

            //Sorting ArrayList nameRecord by LastName
            Collections.sort(nameRecords, new NameCompare());

            WriteOutput writeNames = new WriteOutput();
            //Printing sorted list of names to screen and writing to output file sorted-names-list.txt
            writeNames.writeOutputToFileAndScreen(nameRecords);
        }
    }

    /**
    * Getter for InputFilePath
    */
    public String getInputFilePath()
    {
        return this.inputFilePath;
    }

   /**
    * Setter for InputFilePath
    */
    public void setInputFilePath(String newInputFilePath)
    {
        this.inputFilePath = newInputFilePath;
    }

    /**
    * Check if input file is provided as argument by user
    */
    public int checkInputArguments(String[] args) {
        if (args.length <= 0) {
            System.out.println("Please provide the input file");
            return 1;
        } else {
            String filePath = args[0];
            NameSorter inputArgs = new NameSorter();
            inputArgs.setInputFilePath(filePath);
            return 0;
        }
    }
}



