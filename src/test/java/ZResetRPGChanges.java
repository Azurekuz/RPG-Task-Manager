import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ZResetRPGChanges {

    @Test
    public void resetChanges() throws IOException {
        RPGManager r = new RPGManager(true);
        r.player = new Player();
        JsonUtil.toJsonFile("src/resources/rpgManager.json", r);

    }
}
