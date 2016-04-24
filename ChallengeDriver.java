public class ChallengeDriver {
    public static void main(String[] args) throws Exception {
        String fileName = args[0];
        Roover r = new Roover(fileName);
        r.runRoover();
    }
}

