import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class RooverTest {
    static Roover r;
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    
    @Before
    public void setUpStreams() {
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setErr(null);
    }
    
    @Test
    public void testNullInput() throws IOException {
        r = new Roover(null);
        Assert.assertNotNull(r);
        r.runRoover();
        assertEquals("Please set fileName before calling runRoover", errContent.toString().trim());
    }
    
    @Test
    public void testExampleInput() throws IOException {
        r = new Roover("input.txt");
        Assert.assertNotNull(r);
        r.runRoover();
        assertEquals(Integer.toString(r.getCurrXPos()), "1");
        assertEquals(Integer.toString(r.geCurrYPos()), "3");
        assertEquals(Integer.toString(r.getNumPatchesCleaned()), "1");
    }
    
    @Test
    public void testInvalidBoardInput() throws IOException {
        r = new Roover("invalidBoardSize.txt");
        Assert.assertNotNull(r);
        r.runRoover();
        assertEquals("Invalid inputs for board size or starting roover position.", errContent.toString().trim());
    }
    
    @Test
    public void testInvalidRooverPos() throws IOException {
        r = new Roover("invalidRooverPos.txt");
        Assert.assertNotNull(r);
        r.runRoover();
        assertEquals("Invalid inputs for board size or starting roover position.", errContent.toString().trim());
    }
    
    @Test
    public void testInvalidDirtPos() throws IOException {
        r = new Roover("invalidDirtPos.txt");
        Assert.assertNotNull(r);
        r.runRoover();
        assertEquals("Invalid dirt position.", errContent.toString().trim());
    }
    
    @Test
    public void testNoDirt() throws IOException {
        r = new Roover("noDirtInput.txt");
        Assert.assertNotNull(r);
        r.runRoover();
        assertEquals(Integer.toString(r.getCurrXPos()), "1");
        assertEquals(Integer.toString(r.geCurrYPos()), "3");
        assertEquals(Integer.toString(r.getNumPatchesCleaned()), "0");
    }
    
    @Test
    public void testInvalidDrivingInstr() throws IOException {
        r = new Roover("invalidDrivingInstr.txt");
        Assert.assertNotNull(r);
        r.runRoover();
        assertEquals(Integer.toString(r.getCurrXPos()), "1");
        assertEquals(Integer.toString(r.geCurrYPos()), "3");
        assertEquals(Integer.toString(r.getNumPatchesCleaned()), "1");
        assertEquals("Invalid driving instruction, please enter N, S, W, or E only.", errContent.toString().trim());
    }

}
