package com.provus.util;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import junit.framework.TestCase;
import com.provus.util.MD5Wrapper;
import com.provus.util.NumbertoWord;

/**
 * Test class for com.provus.util.NumbertoWord
 * 
 * @author
 * 
 */
public class NumbertoWordTest extends TestCase {
	MD5Wrapper md5;

	@Override
	protected void setUp() {
		md5 = new MD5Wrapper();
	}

	private String getMD5Value(String str) {
		try {
			return md5.getValue(str);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * Tests com.provus.util.NumbertoWord.parse(double, double,
	 * String) & com.provus.util.NumbertoWord.parse(long)
	 */
	public void testNumberToWord() {
		assertEquals(NumbertoWord.parse(0.10, 2, "{0};{1} Ykr"), ";On Ykr");
		assertEquals(NumbertoWord.parse(1.11, 2, "{0};{1} Ykr"),
				"Bir;OnBir Ykr");
		assertEquals(getMD5Value(NumbertoWord.parse(1)),
				"3bfbb3d122951dd5a5510769973324be");
		assertEquals(getMD5Value(NumbertoWord.parse(2)),
				"cc983fd0d732a3de7c46599e9cbde10e");
		assertEquals(getMD5Value(NumbertoWord.parse(3)),
				"2804b4483060528086cc30b98f8f04da");
		assertEquals(getMD5Value(NumbertoWord.parse(4)),
				"6e9deca676b8042fc77fe1bf6e862b86");
		assertEquals(getMD5Value(NumbertoWord.parse(5)),
				"2e569017d5ec2ac39437c5acdcd57b29");
		assertEquals(getMD5Value(NumbertoWord.parse(6)),
				"7704dd8c216d3bb7cfbdc8490a814d45");
		assertEquals(getMD5Value(NumbertoWord.parse(7)),
				"8b5300bbac4edac07d7067f59962bbab");
		assertEquals(getMD5Value(NumbertoWord.parse(8)),
				"ffcb6aeb12ad23652df852b1ab1751f2");
		assertEquals(getMD5Value(NumbertoWord.parse(9)),
				"09b6c2bca0caab4db37db916da726015");
		assertEquals(getMD5Value(NumbertoWord.parse(10)),
				"521c36a31c2762741cf0f8890cbe05e3");
		assertEquals(getMD5Value(NumbertoWord.parse(20)),
				"8533ac290b0a259429a34dcee6ad8fc9");
		assertEquals(getMD5Value(NumbertoWord.parse(30)),
				"c4548fb35f0b35b30bfb079cd34cd64b");
		assertEquals(getMD5Value(NumbertoWord.parse(38)),
				"df9f29ebcff4bc5335ca210f05030bcb");
		assertEquals(getMD5Value(NumbertoWord.parse(99)),
				"04f934eaad7d95d9fabbb2bd56f05fde");
		assertEquals(getMD5Value(NumbertoWord.parse(100)),
				"a21f5b4d80a008c7f32e7da05e6a084c");
		assertEquals(getMD5Value(NumbertoWord.parse(1000)),
				"807dbe7d1c25a633894d4a231b1c76d3");
		assertEquals(getMD5Value(NumbertoWord.parse(10000)),
				"8829d960153968ed0406439341290e97");
		assertEquals(getMD5Value(NumbertoWord.parse(100000)),
				"fe6734aa0a3746d530ff3650be880671");
		assertEquals(getMD5Value(NumbertoWord.parse(1000000)),
				"ce126f1270f5751908fd469a477447f8");
		assertEquals(getMD5Value(NumbertoWord.parse(1100)),
				"ea8e34afbfb6f079bdbd0d26456e1b0a");
		assertEquals(getMD5Value(NumbertoWord.parse(2100)),
				"3a9fadc76aa965da48542d3e2c18f672");
		assertEquals(getMD5Value(NumbertoWord.parse(1456787456)),
				"ed7f7b0a86a8877086d5412abe8aa8cb");
	}

}
