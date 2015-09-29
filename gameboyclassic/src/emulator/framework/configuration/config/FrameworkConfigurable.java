package emulator.framework.configuration.config;

import framework.context.api.FrameworkConfig;

import java.util.Map;

/**
 * Created by vicboma on 12/02/15.
 */
public class FrameworkConfigurable implements FrameworkConfig {

    protected Map<? extends Class, ? extends Class> map;

    public FrameworkConfigurable(Map<? extends Class, ? extends Class> map) {
        this.map = map;
    }

    @Override
    public void configure() throws Exception {

    }
}
