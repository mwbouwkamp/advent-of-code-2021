package Day22;

public class Instruction {
    private final Box box;
    private final String instruction;

    public Instruction(Box box, String instruction) {
        this.box = box;
        this.instruction = instruction;
    }

    public Box getBox() {
        return box;
    }

    public String getInstruction() {
        return instruction;
    }
}
