package emulator.cartridge;

import utils.Diposable.Disposable;
import utils.Reseteable.Reseteable;

import java.awt.*;
import java.util.concurrent.CompletableFuture;

/**
 * Created by vicboma on 15/08/15.
 */
public interface CartridgeAsync extends Disposable, Reseteable {

    CompletableFuture load(String romFileName, Component a);

    CompletableFuture<Byte> write(int addr, int data);

    CompletableFuture<Byte> read(int addr);

    int gBIndicator();

    boolean isGbColor();
}
