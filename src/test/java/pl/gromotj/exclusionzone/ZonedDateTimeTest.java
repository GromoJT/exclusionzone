package pl.gromotj.exclusionzone;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

public class ZonedDateTimeTest {
    @Test
    public void generateZoneDateTimeWith24hExpiration(){
        System.out.printf("\n%s\n",ZonedDateTime.now().plusMinutes(60*24));
    }
}
