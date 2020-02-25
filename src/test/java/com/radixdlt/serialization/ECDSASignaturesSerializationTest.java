package com.radixdlt.serialization;

import com.google.common.collect.ImmutableMap;
import com.radixdlt.TestSetupUtils;
import com.radixdlt.crypto.BouncyCastleKeyHandler;
import com.radixdlt.crypto.CryptoException;
import com.radixdlt.crypto.ECDSASignature;
import com.radixdlt.crypto.ECDSASignatures;
import com.radixdlt.crypto.ECKeyPair;
import com.radixdlt.crypto.ECKeyUtils;
import com.radixdlt.crypto.Hash;
import com.radixdlt.universe.Universe;
import com.radixdlt.utils.Bytes;
import com.radixdlt.utils.Ints;
import org.bouncycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi;
import org.bouncycastle.jce.ECKeyUtil;
import org.bouncycastle.util.encoders.UTF8;
import org.junit.BeforeClass;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.byteThat;
import static org.mockito.Mockito.mock;


/**
 * Serialization for Signatures to JSON.
 */
public class ECDSASignaturesSerializationTest extends SerializeObjectEngine<ECDSASignatures> {


    public ECDSASignaturesSerializationTest() {
        super(ECDSASignatures.class, ECDSASignaturesSerializationTest::getECDSASignatures);
    }

    @BeforeClass
    public static void startRadixTest() {
        TestSetupUtils.installBouncyCastleProvider();
    }

    private static ECDSASignatures getECDSASignatures() {

        ECKeyPair k1 = null;
        try {
            k1 = new ECKeyPair();
        } catch (CryptoException e) {
            e.printStackTrace();
        }
        ECDSASignature s1 = new ECDSASignature(BigInteger.ONE, BigInteger.ONE);

        return new ECDSASignatures(
                ImmutableMap.of(
                    k1.getPublicKey(), s1
                )
        );
    }
}
