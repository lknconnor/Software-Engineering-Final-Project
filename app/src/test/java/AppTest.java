import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import source.AppMethods;
import source.Freshman;
import source.GoodStanding;
import source.Junior;
import source.Member;
import source.PoorStanding;
import source.Senior;
import source.Sophmore;

import java.util.ArrayList;

public class AppTest 
{
    private ArrayList<Member> members;

    @BeforeEach
    void BeforeEach()
    {
        // New list of members
        members = new ArrayList<>();

        members.add(new Freshman("Jagger", new GoodStanding(3.5, 0)));
        members.add(new Sophmore("Marshal", new PoorStanding(2.5, 100)));
        members.add(new Junior("Charlie", new GoodStanding(3.2, 0)));
        members.add(new Senior("Rick", new PoorStanding(2.8, 200)));
    }

    @Test
    void testMemberCreation() 
    {
        // Tests that member was created correctly
        Member jagger = members.get(0);
        assertEquals("Jagger", jagger.getName());
        assertEquals("Freshman", jagger.getYear());
        assertEquals("Good", jagger.getStanding().getStatus());
    }

    @Test
    void testUpdateStatus() 
    {
        // Tests that Update Status reflects right standing
        AppMethods.updateStatus(members);
        for (Member member : members) 
        {
            if (member.getStanding().getGpa() >= 2.7 && member.getStanding().getDues() == 0) {
                assertEquals("Good", member.getStanding().getStatus());
            } 
            else 
            {
                assertEquals("Poor", member.getStanding().getStatus());
            }
        }
    }

    // Tests basic print function
    @Test 
    void testPrintMembers()
    {
        Member jagger = members.get(0);
        assertEquals("Jagger, Freshman in Good standing.", jagger.describe());
    }

    @Test

}
