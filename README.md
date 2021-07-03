# One-time Pad

[![Lint commit message](https://github.com/yannickkirschen/one-time-pad/actions/workflows/commit-lint.yml/badge.svg)](https://github.com/yannickkirschen/one-time-pad/actions/workflows/commit-lint.yml)
[![Release Drafter](https://github.com/yannickkirschen/one-time-pad/actions/workflows/release-drafter.yml/badge.svg)](https://github.com/yannickkirschen/one-time-pad/actions/workflows/release-drafter.yml)
[![Unit tests](https://github.com/yannickkirschen/one-time-pad/actions/workflows/maven-test.yml/badge.svg)](https://github.com/yannickkirschen/one-time-pad/actions/workflows/maven-test.yml)

This is a Java-implementation of the famous One-time pad algorithm to encrypt data.

**DISCLAIMER: This library *can* be used for serious encryption as it uses `java.util.SecureRandom` to generate a
secret. However, I do not take any responsibility for possible issues due to bad encryption!**

As [Wikipedia](https://en.wikipedia.org/wiki/One-time_pad) says:

In cryptography, the one-time pad (OTP) is an encryption technique that cannot be cracked, but requires the use of a
one-time pre-shared key the same size as, or longer than, the message being sent. In this technique, a plaintext is
paired with a random secret key (also referred to as a one-time pad). Then, each bit or character of the plaintext is
encrypted by combining it with the corresponding bit or character from the pad using modular addition.

The resulting ciphertext will be impossible to decrypt or break if the following four conditions are met:

1. The key must be truly random.
2. The key must be at least as long as the plaintext.
3. The key must never be reused in whole or in part.
4. The key must be kept completely secret.

Point 3 and 4 cannot be covered by this library, as they are your responsibility. This library will take care of point 1
and 2.

## Usage

```java
OneTimePad pad = new OneTimePad();

byte[] text = "hello, world".getBytes(StandardCharsets.UTF_8);

// Destroy this secret once the message has been delivered
// and tell the recipient to destroy it after they decoded
// the message.
byte[] secret = pad.generateSecret(text.length);

byte[] ciphertext = pad.encode(text, secret);
byte[] decrypted = pad.decode(ciphertext, secret);

Assertions.assertArrayEquals(text, decrypted);
```
