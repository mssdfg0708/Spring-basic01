package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class SingleTonServiceTest {

    @Test
    @DisplayName("SingleTon Test")
    void singletonServiceTest() {
        SingleTonService singleTonService01 = SingleTonService.getInstance();
        SingleTonService singleTonService02 = SingleTonService.getInstance();

        System.out.println("singleTonService01 = " + singleTonService01);
        System.out.println("singleTonService02 = " + singleTonService02);

        assertThat(singleTonService01).isSameAs(singleTonService02);
        singleTonService01.logic();
        singleTonService02.logic();
    }
}
