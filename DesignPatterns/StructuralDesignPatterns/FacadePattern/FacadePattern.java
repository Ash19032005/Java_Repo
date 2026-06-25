// Facade pattern provides a unified simplified interface to a set of interfaces in a subsystem. 
// It hides complexity from the client and makes the subsystem easier to use. 
// A good example is Spring’s JdbcTemplate, where database operations are simplified into single method calls.
// Facade pattern example in Java

// Subsystem classes
class CPU{
    public void execute() {
        System.out.println("CPU is executing instructions.");
    }
}

class Memory {
    public void load() {
        System.out.println("Memory is loading data.");
    }
}

class HardDrive {
    public void read() {
        System.out.println("HardDrive is reading data.");
    }
}

// Facade class
class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;

    public ComputerFacade(){
        this.cpu=new CPU();
        this.memory=new Memory();
        this.hardDrive=new HardDrive();
    }

    public void startComputer(){
        System.out.println("Starting Computer...");
        cpu.execute();
        memory.load();
        hardDrive.read();
    } 
}

// Client code
public class FacadePattern {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade();
        computer.startComputer();
    }
}