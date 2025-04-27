import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.*;

import org.junit.jupiter.params.ParameterizedTest;
import warriors.Hero;


public class PlayerTest {
    Player player;

    @Before
    public void setUp() {
        player = new Player();
        Hero hero = new Hero("DefName");
        hero.setMainHero(true);

        player.addHero(hero);
    }

    @Test
    public void isMainHeroDeadTest() {
        boolean result = player.isMainHeroDead();
        assertFalse(result);
    }
}
