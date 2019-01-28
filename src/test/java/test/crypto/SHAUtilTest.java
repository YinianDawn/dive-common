package test.crypto;

import dive.common.crypto.SHAUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * SHAUtil测试
 *
 * @author dawn
 */
public class SHAUtilTest {

    @Test
    public void test() {
        String message = "abc";
        Assert.assertEquals("A9993E364706816ABA3E25717850C26C9CD0D89D", SHAUtil.SHA(message));
        Assert.assertEquals("a9993e364706816aba3e25717850c26c9cd0d89d", SHAUtil.sha(message));
        Assert.assertEquals("BA7816BF8F01CFEA414140DE5DAE2223B00361A396177A9CB410FF61F20015AD", SHAUtil.SHA256(message));
        Assert.assertEquals("ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad", SHAUtil.sha256(message));
        Assert.assertEquals("CB00753F45A35E8BB5A03D699AC65007272C32AB0EDED1631A8B605A43FF5BED8086072BA1E7CC2358BAECA134C825A7", SHAUtil.SHA384(message));
        Assert.assertEquals("cb00753f45a35e8bb5a03d699ac65007272c32ab0eded1631a8b605a43ff5bed8086072ba1e7cc2358baeca134c825a7", SHAUtil.sha384(message));
        Assert.assertEquals("DDAF35A193617ABACC417349AE20413112E6FA4E89A97EA20A9EEEE64B55D39A2192992A274FC1A836BA3C23A3FEEBBD454D4423643CE80E2A9AC94FA54CA49F", SHAUtil.SHA512(message));
        Assert.assertEquals("ddaf35a193617abacc417349ae20413112e6fa4e89a97ea20a9eeee64b55d39a2192992a274fc1a836ba3c23a3feebbd454d4423643ce80e2a9ac94fa54ca49f", SHAUtil.sha512(message));

    }

}
