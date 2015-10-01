package emulator.configuration.action;

import cpu.processor.interrupt.Interrupt;
import cpu.processor.interrupt.InterruptImpl;
import cpu.processor.interrupt.check.CheckInterrupts;
import cpu.processor.interrupt.check.CheckInterruptsImpl;
import cpu.processor.mainRam.MainRam;
import cpu.processor.memory.ioSpecialRegisterProxy.IoSpecialRegister;
import cpu.processor.memory.ioSpecialRegisterProxy.IoSpecialRegisterImpl;
import cpu.processor.memory.read.MemoryMapRead;
import cpu.processor.memory.read.MemoryMapReadImpl;
import cpu.processor.memory.write.MemoryMapWrite;
import cpu.processor.memory.write.MemoryMapWriteImpl;
import cpu.processor.register.bit16.Register16bits;
import cpu.processor.register.bits8.Register8bits;
import cpu.processor.spriteAttribMemory.OAM;
import cpu.processor.z80.Z80Impl;
import cpu.processor.z80.powerUp.PowerUpZ80;
import cpu.processor.z80.powerUp.PowerUpZ80Impl;
import cpu.processor.z80.timer.Z80Clock;
import cpu.processor.z80.timer.Z80ClockImpl;
import cpu.processor.z80.timer.Z80Timer;
import framework.context.api.FrameworkConfig;
import framework.injector.api.annotation.Inject;
import framework.injector.api.annotation.method.PostConstruct;
import framework.injector.api.type.Injector;
import framework.logger.api.Logger;

/**
 * Created by vicboma on 30/09/15.
 */
public class ProcessorConfigure implements FrameworkConfig {

    @Inject
    public Injector inject;

    @Inject
    public Logger logger;

    private CheckInterrupts checkInterruptsImpl;
    private Interrupt interrupt;
    private OAM oam;
    private Register8bits register8Bits;
    private Register16bits register16bits;
    private Z80Timer z80Timer;

    public static FrameworkConfig create(){
        return new ProcessorConfigure();
    }

    public ProcessorConfigure() {
    }

    @PostConstruct
    public void initialize(){
        logger.info(this);

        this.interrupt = InterruptImpl.Create();
        this.checkInterruptsImpl = CheckInterruptsImpl.Create();
        this.oam = OAM.Create(OAM.size);
        this.register16bits = Register16bits.Create();
        this.register8Bits = Register8bits.Create();
        this.z80Timer = Z80Timer.Create();
    }

    @Override
    public void configure() throws Exception {
        mapperClasses();
        injectInto();
    }

    private void injectInto() throws Exception {
        inject.injectInto(checkInterruptsImpl);
        inject.injectInto(interrupt);
        inject.injectInto(register16bits);
        inject.injectInto(register8Bits);
        inject.injectInto(oam);
        inject.injectInto(z80Timer);
    }

    private void mapperClasses() {

        inject.mapSingleton(PowerUpZ80.class,PowerUpZ80Impl.class);
        inject.mapSingleton(IoSpecialRegister.class,IoSpecialRegisterImpl.class);
        inject.mapSingleton(Z80Clock.class, Z80ClockImpl.class);
        inject.mapSingleton(MemoryMapWrite.class,MemoryMapWriteImpl.class);
        inject.mapSingleton(MemoryMapRead.class,MemoryMapReadImpl.class);
        inject.mapValue(CheckInterrupts.class, checkInterruptsImpl);
        inject.mapValue(Interrupt.class, interrupt);
        inject.mapValue(MainRam.class, null);
        inject.mapValue(Z80Impl.class, null);
        inject.mapValue(Register16bits.class, this.register16bits);
        inject.mapValue(Register8bits.class, this.register8Bits);
        inject.mapValue(OAM.class, oam);
        inject.mapValue(Z80Timer.class, this.z80Timer);

    }
}
