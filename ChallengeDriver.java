public class ChallengeDriver {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) return;
        
        String fileName = args[0];
        Roover r = new Roover(fileName);
        r.runRoover();
    }
}

