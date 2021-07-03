package sh.yannick.tools.otp;

import org.junit.jupiter.api.*;

import java.nio.charset.StandardCharsets;

public class OneTimePadTest {
    @Test
    public void encodeTest() {
        OneTimePad pad = new OneTimePad();

        byte[] text = "hello, world".getBytes(StandardCharsets.UTF_8);
        byte[] secret = pad.generateSecret(text.length);
        byte[] ciphertext = pad.encode(text, secret);

        Assertions.assertNotEquals(new String(text, StandardCharsets.UTF_8), new String(secret, StandardCharsets.UTF_8));
        Assertions.assertNotEquals(new String(text, StandardCharsets.UTF_8), new String(ciphertext, StandardCharsets.UTF_8));
    }

    @Test
    public void decodeTest() {
        OneTimePad pad = new OneTimePad();

        byte[] text = "hello, world".getBytes(StandardCharsets.UTF_8);
        byte[] secret = pad.generateSecret(text.length);
        byte[] ciphertext = pad.encode(text, secret);
        byte[] decrypted = pad.decode(ciphertext, secret);

        Assertions.assertArrayEquals(text, decrypted);
        Assertions.assertNotEquals(new String(text, StandardCharsets.UTF_8), new String(secret, StandardCharsets.UTF_8));
        Assertions.assertNotEquals(new String(decrypted, StandardCharsets.UTF_8), new String(secret, StandardCharsets.UTF_8));
    }

    @Test
    public void generateSecretTest() {
        OneTimePad pad = new OneTimePad();

        // As the secret is generated randomly, we can only test the length of the secret.
        byte[] secret = pad.generateSecret(4);
        Assertions.assertEquals(4, secret.length);
    }
}
