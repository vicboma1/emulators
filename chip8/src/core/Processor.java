package core;

import emulator.instructions.Instructions;
import emulator.specification.SpecificationChip8Impl;
import emulator.specification.input.KeyBoard;
import emulator.specification.input.KeyBoardStatus;
import emulator.specification.memory.Memory;
import emulator.specification.register.Register;
import emulator.specification.sound.Sound;
import emulator.specification.timer.Timer;
import emulator.utils.Utils;

import java.util.stream.IntStream;

/**
 * Created by vicboma on 11/07/14.
 * Emulator Loop Core based in a Variable time step
 */
public class Processor implements Executable, Updateable {

    private static final int OPERATIONAL_CODE_1 = 0xF000;
    private static final int OPERATIONAL_CODE_2 = 0x0F00;
    private static final int OPERATIONAL_CODE_3 = 0x00F0;
    private static final int OPERATIONAL_CODE_4 = 0x000F;

    private static final double UPDATES_PER_SECOND = 60.0;
    private static final double MILLISECOND = 1000.0;
    private static final double SKIP_TICKS = MILLISECOND / UPDATES_PER_SECOND;
    private static final double UPDATE_TIMERS = 2000.0;
    private static final double SKIP_TIMER = MILLISECOND / UPDATE_TIMERS;
    private static final int SECOND = 1;
    private static final int NANOTIMES = 490000;


    private double elapsed;
    private Double delta;
    private Boolean isAlive;

    private KeyBoard keyBoard;
    private Memory memory;
    private Register register;
    private Instructions instructions;
    private Timer timer;
    private Sound sound;

    public Processor(SpecificationChip8Impl specificationChip8Impl, Instructions instructions) {
        this.keyBoard = specificationChip8Impl.keyBoard();
        this.memory = specificationChip8Impl.memory();
        this.register = specificationChip8Impl.register();
        this.timer = specificationChip8Impl.timer();
        this.sound = specificationChip8Impl.sound();
        this.instructions = instructions;
    }

    @Override
    public void stop() {
        isAlive = false;
    }

    @Override
    public void run() {
        isAlive = true;
        this.elapsed = Utils.ZERO;
        long millis, actualTime;
        long previousTime = getCurrentTimeMillis();

        while (isAlive) {

            actualTime = getCurrentTimeMillis();
            long updateLength = actualTime - previousTime;
            previousTime = actualTime;
            delta = updateLength / SKIP_TICKS;

            processInput();
            update(delta);

            try {
                millis = (long) ((previousTime - getCurrentTimeMillis() + UPDATES_PER_SECOND) / MILLISECOND);
                Thread.sleep(SECOND, NANOTIMES + (int) millis);
            } catch (Exception e) {
                stop();
                e.printStackTrace();
            }
        }
    }

    private void processInput() {
        final KeyBoardStatus status = this.keyBoard.status();
        status.reset();
        status.process(this.keyBoard);
        status.refresh();
    }

    @Override
    public void update(Double delta) {
        fetch();
        decode();
        execute();
        sound();
        timers(delta);
    }

    private void timers(Double delta) {
        elapsed += delta;
        if (elapsed >= SKIP_TIMER) {
            IntStream stream = IntStream.range(Utils.ZERO, Timer.SIZE);
            stream.sequential().filter(x -> timer.get(x) > Utils.ZERO).forEach(x -> {
                timer.set(x, (short) (timer.get(x) - 1));
            });
            elapsed = Utils.ZERO;
        }
    }

    private void sound() {
        if (timer.sound() > Utils.ZERO)
            sound.play();
    }

    private void execute() {
        final int opCode1 = register.getOpCode(1);
        this.instructions.run(opCode1);
    }

    private Long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * Instrucción (2 bytes): 6A02 = 0110 1010 0000 0010 binario
     * +----------+-----------+----------+-----------+
     * |          |           |          |           |
     * | Opcode1  | Opcode X  | Opcode Y | Opcode N  |
     * |  0110    |   1010    |  0000    |   0010    |
     * +----------+-----------+----------+-----------+
     * |          |         Opcode  NNN              |
     * |          |         10100000010              |
     * +----------+-----------+----------+-----------+
     */
    private void decode() {

        final int instruction = register.opCode();
        final short opCode1 = (short) ((instruction & OPERATIONAL_CODE_1) >> 12);
        final short opCode2 = (short) ((instruction & OPERATIONAL_CODE_2) >> 8);
        final short opCode3 = (short) ((instruction & OPERATIONAL_CODE_3) >> 4);
        final short opCode4 = (short) ((instruction & OPERATIONAL_CODE_4));

        register.opCodes(opCode1, opCode2, opCode3, opCode4);
    }

    private void fetch() {
        final int opCode = this.memory.ShiftL(this.register.IP(), 8) | this.memory.get(this.register.IP() + 1);
        register.opCode(opCode);
    }
}
