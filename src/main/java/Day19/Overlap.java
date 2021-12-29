package Day19;

public class Overlap {
    public final Scanner scanner1;
    public final int scanner1Index;
    public final String orientation1;
    public final Scanner scanner2;
    public final int scanner2Index;
    public final String orientation2;

    public Overlap(Scanner scanner1, int scanner1Index, String orientation1, Scanner scanner2, int scanner2Index, String orientation2) {
        this.scanner1 = scanner1;
        this.scanner1Index = scanner1Index;
        this.orientation1 = orientation1;
        this.scanner2 = scanner2;
        this.scanner2Index = scanner2Index;
        this.orientation2 = orientation2;
    }

    public Scanner getScanner1() {
        return scanner1;
    }

    public int getScanner1Index() {
        return scanner1Index;
    }

    public String getOrientation1() {
        return orientation1;
    }

    public Scanner getScanner2() {
        return scanner2;
    }

    public int getScanner2Index() {
        return scanner2Index;
    }

    public String getOrientation2() {
        return orientation2;
    }
}
