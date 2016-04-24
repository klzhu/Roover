import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Roover {
    String fileName;
    Integer numPatchesCleaned;
    Integer finalXPos;
    Integer finalYPos;
    
    public Integer getNumPatchesCleaned() {
        return numPatchesCleaned;
    }

    public Integer getFinalXPos() {
        return finalXPos;
    }

    public Integer getFinalYPos() {
        return finalYPos;
    } 
    
    public Roover(String input)
    {
        fileName = input;
        numPatchesCleaned = 0;
    }
    
    public void runRoover() throws IOException
    {
        if (fileName == null)
        {
            System.err.println("Please set fileName before calling runRoover");
            return;
        }
        
        int patchesCleaned = 0;
        HashSet<String> dirtSet = new HashSet<String>();
        BufferedReader r = new BufferedReader(new FileReader(fileName));
        String[] dims = r.readLine().split(" "); //reads the first line, which is the dimensions of our grid
        int numCols = Integer.parseInt(dims[0]);
        int numRows = Integer.parseInt(dims[1]);
        String[] dirtPosArr;
        String currDirtPos;
                
        //read in rooverPos
        String[] rooverPosStr = r.readLine().split(" ");
        int currXPos = Integer.parseInt(rooverPosStr[0]);
        int currYPos = Integer.parseInt(rooverPosStr[1]);
        
        //validate inputs
        if (numCols <= 0 || numRows <= 0 || currXPos < 0 || currXPos > numCols - 1 
                || currYPos < 0 || currYPos > numRows - 1)
        {
            System.err.println("Invalid inputs for board size or starting roover position.");
            r.close();
            return;
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
                return;
            }
            
            currDirtPos = dirtPosArr[0] + dirtPosArr[1];
            dirtSet.add(currDirtPos);
            currLine = nextLine;
            nextLine = r.readLine();
        }
        
        r.close();
        
        //check to see if roover's original position has a dirt patch
        if (dirtSet.contains(Integer.toString(currXPos) + Integer.toString(currYPos)))
        {
            dirtSet.remove(Integer.toString(currXPos) + Integer.toString(currYPos));
            patchesCleaned++;
        }
        
        //at this point, currline will be the last line in our input, which is driving instructions
        char[] instructions = currLine.toUpperCase().toCharArray();
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
                patchesCleaned++;
            }
        }
        
        System.out.println(currXPos + " " + currYPos);
        System.out.println(patchesCleaned);
        
        //set vars for unit tests
        numPatchesCleaned = patchesCleaned;
        finalXPos = currXPos;
        finalYPos = currYPos;
    }
}
