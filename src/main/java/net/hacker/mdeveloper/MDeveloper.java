package net.hacker.mdeveloper;

import com.mojang.logging.LogUtils;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(MDeveloper.MODID)
public class MDeveloper {
    public static final String MODID = "mdeveloper";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MDeveloper() {
        LOGGER.info("MDeveloper initialized");
    }
}
