import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Roover {
    String fileName;
    Integer numPatchesCleaned;
    Integer currXPos;
    Integer currYPos;
    HashSet<String> dirtSet;
    Integer numRows;
    Integer numCols;
    
    public Integer getNumPatchesCleaned() {
        return numPatchesCleaned;
    }

    public Integer getCurrXPos() {
        return currXPos;
    }

    public Integer geCurrYPos() {
        return currYPos;
    } 
    
    public Roover(String input)
    {
        fileName = input;
        numPatchesCleaned = 0;
        dirtSet = new HashSet<String>();
    }
    
    /*
     * Calls rooverSetUp and executes driving instructions for roover
     */
    public void runRoover() throws IOException
    {   
        String drivingInstr = rooverSetUp();
        if (drivingInstr == null)
            return;
        
        //check to see if roover's original position has a dirt patch
        if (dirtSet.contains(Integer.toString(currXPos) + Integer.toString(currYPos)))
        {
            dirtSet.remove(Integer.toString(currXPos) + Integer.toString(currYPos));
            numPatchesCleaned++;
        }
        
        //at this point, currline will be the last line in our input, which is driving instructions
        char[] instructions = drivingInstr.toUpperCase().toCharArray();
        for (int i = 0; i < instructions.length; i++)
        {
            switch(instructions[i]) {
                case 'N':
                    if (currYPos + 1 < numRows)
                    {
                        currYPos++;
                    }
                    break;
                case 'S':
                    if (currYPos - 1 >= 0)
                    {
                        currYPos--;
                    }
                    break;
                case 'W':
                    if (currXPos - 1 >= 0)
                    {
                        currXPos--;
                    }
                    break;
                case 'E':
                    if (currXPos + 1 < numCols)
                    {
                        currXPos++;
                    }
                    break;
                default:
                    System.err.println("Invalid driving instruction, please enter N, S, W, or E only.");
                    break;
            }
            
            if (dirtSet.contains(Integer.toString(currXPos) + Integer.toString(currYPos)))
            {
                dirtSet.remove(Integer.toString(currXPos) + Integer.toString(currYPos));
                numPatchesCleaned++;
            }
        }
        
        System.out.println(currXPos + " " + currYPos);
        System.out.println(numPatchesCleaned);
    }
    
    
    /*
     * Sets up the roover from input and returns the driving instructions
     */
    private String rooverSetUp() throws IOException
    {
        if (fileName == null)
        {
            System.err.println("Please set fileName before calling runRoover");
            return null;
        }
        
        BufferedReader r = new BufferedReader(new FileReader(fileName));
        String[] dims = r.readLine().split(" "); //reads the first line, which is the dimensions of our grid
        numCols = Integer.parseInt(dims[0]);
        numRows = Integer.parseInt(dims[1]);
        String[] dirtPosArr;
        String currDirtPos;
                
        //read in rooverPos
        String[] rooverPosStr = r.readLine().split(" ");
        currXPos = Integer.parseInt(rooverPosStr[0]);
        currYPos = Integer.parseInt(rooverPosStr[1]);
        
        //validate inputs
        if (numCols <= 0 || numRows <= 0 || currXPos < 0 || currXPos > numCols - 1 
                || currYPos < 0 || currYPos > numRows - 1)
        {
            System.err.println("Invalid inputs for board size or starting roover position.");
            r.close();
            return null;
        }
        
        String currLine = r.readLine();
        String nextLine = r.readLine();
        //set up the roover and dirt patches
        while (nextLine != null)
        {
            dirtPosArr = currLine.split(" ");
            
            //validate dirt pos
            if (Integer.parseInt(dirtPosArr[0]) < 0 || Integer.parseInt(dirtPosArr[0]) > numCols - 1
                    || Integer.parseInt(dirtPosArr[1]) < 0 || Integer.parseInt(dirtPosArr[1]) > numRows - 1)
            {
                System.err.println("Invalid dirt position.");
                r.close();
                return null;
            }
            
            currDirtPos = dirtPosArr[0] + dirtPosArr[1];
            dirtSet.add(currDirtPos);
            currLine = nextLine;
            nextLine = r.readLine();
        }
        
        r.close();
        
        //at this point, currLine is the driving instructions
        return currLine;
    }
}
