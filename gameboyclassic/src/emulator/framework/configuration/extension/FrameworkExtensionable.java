package emulator.framework.configuration.extension;

import framework.context.api.FrameworkExtension;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Created by vicboma on 12/02/15.
 */
public class FrameworkExtensionable implements FrameworkExtension {

    protected  CompletableFuture completableFuture;
    protected Map<? extends Class, ? extends Class> map;

    public FrameworkExtensionable(Map<? extends Class, ? extends Class> map) {
        this.map = map;
    }

    @Override
    public CompletableFuture<FrameworkExtension> installExtensionAsync() throws Exception {
        return null;
    }
}