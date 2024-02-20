package source;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;


public class AppMethods 
{
    // Methods used for demo

    // If new changes are made, updates their status
    public static ArrayList<Member> updateStatus(ArrayList<Member> members)
    {
        for (Member member : members)
                {
                    // If GPA is 2.7 or higher and does not owe money, set do Good Standing
                    if(member.getStanding().getGpa() >= 2.7 && member.getStanding().getDues() == 0)
                    {
                        member.standing = new GoodStanding(member.standing.getGpa(), 0);
                        System.out.println(member.getName() + " is now in good standing.");
                    }
                    // If conditions not met, set to poor standing
                    else
                    {
                        member.standing = new PoorStanding(member.standing.getGpa(), member.standing.getDues());
                        System.out.println(member.getName() + " is now in poor standing.");
                    }
                }
        return members;
    }

    // Allows user to enter a payment for a member
    public static ArrayList<Member> makePayment(ArrayList<Member> members, Scanner scan)
    {
        boolean found = false;
        String input2;
        int inputmoney;
        System.out.println("Enter the name of the member you'd like to log payments for");
                input2 = scan.nextLine();
                for (Member member : members)
                {
                    if(member.getName().equals(input2))
                    {
                        found = true;
                        System.out.println("Member Owes: " + member.amountOwed());
                        System.out.println("Enter a payment amount");
                        inputmoney = Integer.parseInt(scan.nextLine());
                        member.setDues(member.amountOwed() - inputmoney);
                        System.out.println("Members balance is now: " + member.amountOwed());
                    }
                }
                // Handles a name not found in database
                if(!found)
                {
                    System.out.println("Could not find member: " + input2);
                }
        return members;
    }

    // Prints basic description of members
    public static void printMembers(ArrayList<Member> members)
    {
        for (Member member : members)
                {
                    System.out.println(member.describe());
                }
    }

    // Allows user to update details of one member
    public static ArrayList<Member> updateOneMember(ArrayList<Member> members, Scanner scan)
    {
        String input2;
        String year;
                System.out.println("Enter the name of the member who's year you'd like to update: ");
                input2 = scan.nextLine();
                for (Member member : members)
                {
                    if(member.getName().equals(input2))
                    {
                        System.out.println("Enter updated year: ");
                        year = scan.nextLine();
                        member.setYear(year);
                    }
                }
        return members;
    }

    // Shifts all member's years + 1, graduates seniors
    public static ArrayList<Member> updateAllMembers(ArrayList<Member> members)
    {
        Iterator<Member> iterator = members.iterator();
        while (iterator.hasNext()) 
        {
            Member member = iterator.next();
            switch (member.getYear()) 
            {
                case "Freshman":
                    member.setYear("Sophomore");
                    break;
                case "Sophomore":
                    member.setYear("Junior");
                    break;
                case "Junior":
                    member.setYear("Senior");
                    break;
                case "Senior":
                    iterator.remove();
                    System.out.println(member.getName() + " has graduated!");
                    break;
            }
        }
        return members;
    }

    // Adds a new member
    public static ArrayList<Member> addMember(ArrayList<Member> members, Scanner scan)
    {
        String name;
        String year;
        double dues;
        double gpa;
        System.out.println("Enter the member's name: ");
                name = scan.nextLine();
                System.out.println("Enter the member's year: ");
                year = scan.nextLine();
                System.out.println("Enter the member's due amount: ");
                dues = Double.parseDouble(scan.nextLine());
                System.out.println("Enter the member's GPA: ");
                gpa = Double.parseDouble(scan.nextLine());

                // Sets new member's name, standing, gpa, and dues
                if(year.equals("Freshman"))
                {
                    members.add(new Freshman(name, new PoorStanding(gpa, dues)));
                    updateStatus(members);
                }
                else if(year.equals("Sophmore"))
                {
                    members.add(new Sophmore(name, new PoorStanding(gpa, dues)));
                    updateStatus(members);
                }
                else if(year.equals("Junior"))
                {
                    members.add(new Junior(name, new PoorStanding(gpa, dues)));
                    updateStatus(members);
                }
                else if(year.equals("Senior"))
                {
                    members.add(new Senior(name, new PoorStanding(gpa, dues)));
                    updateStatus(members);
                }
                return members;
    }

    // Closes program, asks if user wants to save to file
    public static void saveChanges(ArrayList<Member> members, String path, Scanner scan)
    {
        String ans;
        System.out.println("Do you want to clear the file and save your new changes?(y/n)");
        ans = scan.nextLine();
        if(ans.equals("y"))
        {
            try 
            {
                // Wipes file clean to prepare for changes
                FileWriter writer = new FileWriter(path, true);
                clearFile(path);
                
                // Updates blank file to reflect changes made
                for(Member member : members)
                {
                    writer.write(member.toFile() + "\n");
                }
                writer.close();
            }
            // Handles an error with file being wiped
            catch(IOException e)
            {
                System.out.println("Error occured writing to file. File has not been wiped.");
                e.printStackTrace();
            }
        }
        // Default if user enters no
        else
        {
            System.out.println("File will remain unchanged");
        }
        System.out.println("Closing...");
    }

    // Wipes file clean to prepare for changes
    public static void clearFile(String path)
    {
        try 
        {
            FileWriter clear = new FileWriter(path, false);
            clear.close();
            System.out.println("Wiped existing data.");
        }
        // Handles an error with file being wiped
        catch(IOException e)
        {
            System.out.println("Error occured wiping file.");
            e.printStackTrace();
        }
    }

    // Reads in the default text file, adds to member array list
    public static ArrayList<Member> readFile(String filePath) 
    {
        ArrayList<Member> members = new ArrayList<>();
        try (Scanner scan = new Scanner(new File(filePath))) 
        {
            while (scan.hasNextLine()) 
            {
                String[] data = scan.nextLine().split(", ");
                if (data.length >= 5) {
                    String name = data[0];
                    String year = data[1];
                    Standing standing = Standing.createStanding(data[2], Double.parseDouble(data[3]), Double.parseDouble(data[4]));
                    if(year.equals("Freshman"))
                    {
                        members.add(new Freshman(name, standing));
                    }
                    else if(year.equals("Sophmore"))
                    {
                        members.add(new Sophmore(name, standing));
                    }
                    else if(year.equals("Junior"))
                    {
                        members.add(new Junior(name, standing));
                    }
                    else if(year.equals("Senior"))
                    {
                        members.add(new Senior(name, standing));
                    }
                }
            }
            AppMethods.updateStatus(members);
        }
        // Handles error where file path is wrong (Should never be the case) 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
        return members;
    }

}