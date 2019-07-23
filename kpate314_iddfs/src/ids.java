/* The input string is assumed to be in the hexadecimal format i.e. making the goal state = "123456789BCDEF0"*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class ids
{
    //for the path.
    Stack <String> q = new Stack<String>();
    Stack <Character> moves = new Stack<Character>();

    //for initial input
    String z;
    int exp;
    long time;
    long starttime;// to calculate the running time.

    /*main function accepts input string in hexadecimal format from console,
    runs iterative deepening DFS
    and shows output*/
    public static void main(String args[])
    {
        ids obj = new ids();
        int a=0;
        obj.exp=0;
        int i =1; //arbitrary value to limit the iterative search
        obj.starttime = System.nanoTime();
        Runtime runtime = Runtime.getRuntime();
        BufferedReader input = new BufferedReader (new InputStreamReader (System.in));
        System.out.println("Enter the initial state of board as a hexadecimal string");
        try
        {
            obj.z = input.readLine(); //initial state of the board.
        }catch(Exception e)
        {System.out.println("IO exception");}
        obj.q.push(obj.z);

        //iterative depth search logic. i is a arbitrary value to limit the search.
        while(a!=1 && i<200)
        {
            obj.q=new Stack<String>();
            obj.q.push(obj.z);
            obj.search(obj.z,i);
            if(obj.q.peek().equalsIgnoreCase("123456789ABCDEF0"))
            {
                a=1;
                break;
            }
            i++;
            if (runtime.freeMemory() < (.0001) * runtime.totalMemory()){
                return;
            }

        }
        obj.time= (System.nanoTime() - obj.starttime);

        //printing the path.
        if(a==1)
        {
            System.out.print("Moves:");
            while(!obj.moves.empty()) {
                //System.out.println(obj.q.pop());
                System.out.print(obj.moves.pop());
            }
        }
        else
            System.out.println("\nFailure occurred during printing the path");
        System.out.println("\nNumber of nodes expanded: "+obj.exp);
        //time and memory usage.
        System.out.println("Time taken "+obj.time+" nanoseconds");
        System.out.println("Memory used: " + (runtime.totalMemory()-runtime.freeMemory())/1024+"kb");

    }

    void search(String s,int d)
    {
        if(d<0)
            return;
        else
        {
            if(s.equalsIgnoreCase("123456789ABCDEF0"))
                return;

            int i= upPuzzle(s); //new nodes
            if(i==1)
            {
                exp++;
                search(q.peek(),d-1);//recursive call
                if(q.peek().equalsIgnoreCase("123456789ABCDEF0")) //checking goal state
                    return; //to get out of the recursion
                q.pop();
                moves.pop();
            }

            i=downPuzzle(s); //new nodes
            if(i==1)
            {
                exp++;
                search(q.peek(),d-1);//recursive call
                if(q.peek().equalsIgnoreCase("123456789ABCDEF0")) //checking goal state
                    return; //to get out of the recursion
                q.pop();
                moves.pop();
            }

            i=leftPuzzle(s); //new node
            if(i==1)
            {
                exp++;
                search(q.peek(),d-1);//recursive call
                if(q.peek().equalsIgnoreCase("123456789ABCDEF0")) //checking goal state
                    return; //to get out of the recursion
                q.pop();
                moves.pop();
            }

            i=rightPuzzle(s);//new nodes
            if(i==1)
            {
                exp++;
                search(q.peek(),d-1); //recursive call
                if(q.peek().equalsIgnoreCase("123456789ABCDEF0")) //checking goal state
                    return; //to get out of the recursion.
                q.pop();
                moves.pop();
            }
        }
    }

    int upPuzzle(String b) //moves the blank tile up.
    {
        String s = "";
        int a = b.indexOf("0");
        if (a > 3)
            s = b.substring(0, a - 4) + "0" + b.substring(a - 3, a)+ b.charAt(a -4) + b.substring(a + 1); //performs up shift
        int w=q.search(s);
        if(w!=.1 && !s.equals(""))
        {
            q.push(s);
            moves.push('U');
            return 1;
        }
        else
            return 0;
    }

    int downPuzzle(String b) //moves the blank tile down
    {
        String s = "";
        int a = b.indexOf("0");
        if (a < 12)
            s = b.substring(0, a) + b.substring(a + 4, a + 5)+ b.substring(a + 1,a + 4) + "0"+ b.substring(a + 5);//performs down shift
        int w=q.search(s);
        if(w!=.1 && !s.equals(""))
        {
            q.push(s);
            moves.push('D');
            return 1;
        }
        else
            return 0;
    }

    int leftPuzzle(String b) //moves the blank tile left
    {
        String s = "";
        int a = b.indexOf("0");
        if (a != 0 && a != 4 && a != 8 && a != 12)
            s = b.substring(0, a - 1) + "0" + b.substring(a-1,a)+ b.substring(a + 1);// performs left shift
        int w=q.search(s);
        if(w!=.1 && !s.equals(""))
        {
            q.push(s);
            moves.push('L');
            return 1;
        }
        else
            return 0;

    }

    int rightPuzzle(String b) //moves the blank tile right.
    {
        String s = "";
        int a = b.indexOf("0");
        if (a != 3 && a != 7 && a != 11 && a != 15)
            if(a==14)
                s = b.substring(0, a) + b.substring(a + 1) + "0"; //performs right shift.
            else
                s = b.substring(0, a) + b.substring(a + 1,a+2) + "0"+ b.substring(a+2);// performs right shift

        int w=q.search(s);
        if(w!=.1 && !s.equals(""))
        {
            q.push(s);
            moves.push('R');
            return 1;
        }
        else
            return 0;

    }

}