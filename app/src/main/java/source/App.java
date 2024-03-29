/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package source;
import java.util.Scanner;

import javax.swing.JFrame;

import java.util.ArrayList;

public class App 
{
    Scanner scan = new Scanner(System.in);
    public static void main(String[] args) 
    {
        Window window = new Window();
        window.display();
        window.readFile();
        // DEMO PROGRAM

        // Default path, does not support another file
        String path = "C:\\Gradle\\FinalProject\\file.txt";
        ArrayList<Member> members = new ArrayList<Member>();
        Scanner scan = new Scanner(System.in);
        String yn;
        int input = 0;

        // Use text file option
        System.out.println("Do you have a file you want to read?(y/n): ");
        yn = scan.nextLine();
        if(yn.equals("y"))
        {
            members = AppMethods.readFile(path);
        }

        // User interface
        while(input != 7)
        {
            System.out.println("What would you like to do? \n 1. View member(s) \n 2. Log payment(s) \n 3. Update a member's year \n 4. Update all member's year \n 5. Change member status(s), \n 6. Add a member \n 7. Close program");
            input = Integer.parseInt(scan.nextLine());

            // Prints basic description of members
            if(input == 1)
            {
                AppMethods.printMembers(members);
            }

            // Allows user to enter a payment for a member
            else if(input == 2)
            {
                members = AppMethods.makePayment(members, scan);
            }

            // Allows user to update details of one member
            else if(input == 3)
            {
                members = AppMethods.updateOneMember(members, scan);
            }

            // Shifts all member's years + 1, graduates seniors
            else if(input == 4)
            {
                members = AppMethods.updateAllMembers(members);
            }

            // If new changes are made, updates their status
            else if(input == 5)
            {
                members = AppMethods.updateStatus(members);
            }

            // Adds a new member
            else if(input == 6)
            {
                AppMethods.addMember(members, scan);
            }

            // Closes program, asks if user wants to save to file
            else if(input == 7)
            {
                AppMethods.saveChanges(members, path, scan);
            }

            // Handles invalid input
            else
            {
                System.out.println("Invalid choice, please enter a number between 1 and 7");
            }
        }
    }
}