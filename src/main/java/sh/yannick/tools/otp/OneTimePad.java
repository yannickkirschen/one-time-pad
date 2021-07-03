package sh.yannick.tools.otp;

import java.security.SecureRandom;

/**
 * This is an implementation of the famous One-time pad algorithm to encrypt data.
 * <p>
 * <b>DISCLAIMER: This class <i>can</i> be used for serious encryption as it uses {@link SecureRandom} to generate a
 * secret. However, I do not take any responsibility for possible issues due to bad encryption!</b>
 * <p>
 * In cryptography, the one-time pad (OTP) is an encryption technique that cannot be cracked, but requires the use of a
 * one-time pre-shared key the same size as, or longer than, the message being sent. In this technique, a plaintext is
 * paired with a random secret key (also referred to as a one-time pad). Then, each bit or character of the plaintext is
 * encrypted by combining it with the corresponding bit or character from the pad using modular addition.
 * <p>
 * The resulting ciphertext will be impossible to decrypt or break if the following four conditions are met:
 * <p>
 *     <ol>
 *         <li>The key must be truly random.</li>
 *         <li>The key must be at least as long as the plaintext.</li>
 *         <li>The key must never be reused in whole or in part.</li>
 *         <li>The key must be kept completely secret.</li>
 *     </ol>
 * <p>
 * Point 3 and 4 cannot be covered by this class, as they are your responsibility. This class will take care of point 1 and 2.
 * <p>
 * You can find more information on this topic over on <a href="https://en.wikipedia.org/wiki/One-time_pad">Wikipedia</a>.
 *
 * @author Yannick Kirschen
 * @since 1.0.0
 */
public class OneTimePad {
    /**
     * Encodes a plain text with a given secret by using the One-time pad algorithm.
     * <p>
     * This method works exactly the same as {@link #decode(byte[], byte[])}, as encoding and decoding works the same in
     * One-time pad. Both functions have been seperated in to different methods though, to not confuse the people.
     *
     * @param plaintext Text to encode.
     * @param secret    Secret to use for encoding.
     * @return The encoded text.
     * @throws AssertionError When the secret is shorter than the plain text.
     */
    public byte[] encode(byte[] plaintext, byte[] secret) throws AssertionError {
        if (secret.length < plaintext.length) {
            throw new AssertionError("The secret must have the same length or greater as the plain text.");
        }

        return encodeDecode(plaintext, secret);
    }

    /**
     * Decodes an encoded text with a given secret that the text has been encoded with by using the One-time pad
     * algorithm.
     * <p>
     * This method works exactly the same as {@link #encode(byte[], byte[])}, as encoding and decoding works the same in
     * One-time pad. Both functions have been separated in to different methods though, to not confuse the people.
     *
     * @param ciphertext The encoded text to decode.
     * @param secret     Secret to use for decoding.
     * @return The decoded text.
     * @throws AssertionError When the secret is shorter than the encoded text.
     */
    public byte[] decode(byte[] ciphertext, byte[] secret) {
        if (secret.length < ciphertext.length) {
            throw new AssertionError("The secret must have the same or greater length as the ciphertext.");
        }

        return encodeDecode(ciphertext, secret);
    }


    /**
     * This will generate a byte array of random bytes by using {@link SecureRandom#nextBytes(byte[])}. This methodology
     * should be safe to use for serious encryption.
     *
     * @param n Length of the byte array to create.
     * @return A byte array of random bytes.
     */
    public byte[] generateSecret(int n) {
        byte[] b = new byte[n];
        new SecureRandom().nextBytes(b);
        return b;
    }

    private byte[] encodeDecode(byte[] text, byte[] secret) {
        byte[] ciphertext = new byte[text.length];

        for (int i = 0; i < text.length; i++) {
            ciphertext[i] = (byte) (text[i] ^ secret[i]);
        }

        return ciphertext;
    }
}
