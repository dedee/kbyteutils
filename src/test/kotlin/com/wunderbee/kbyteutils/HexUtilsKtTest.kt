package com.wunderbee.kbyteutils

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class HexUtilsKtTest {

    @Test
    fun hex() {
        assertEquals("", byteArrayOf().convertByteArrayToHexString())
        assertEquals("12", byteArrayOf(0x12.toByte()).convertByteArrayToHexString())
        assertEquals("aa", byteArrayOf(0xaa.toByte()).convertByteArrayToHexString())
        assertEquals("aabb", byteArrayOf(0xaa.toByte(), 0xbb.toByte()).convertByteArrayToHexString())
    }

    @Test
    fun dehex() {
        assertArrayEquals(byteArrayOf(), "".convertHexStringToByteArray())
        assertArrayEquals(byteArrayOf(0xaa.toByte()), "aa".convertHexStringToByteArray())
        assertArrayEquals(byteArrayOf(0x12.toByte()), "12".convertHexStringToByteArray())
        assertArrayEquals(byteArrayOf(0xaa.toByte(), 0xbb.toByte()), "aabb".convertHexStringToByteArray())
    }

    @Test
    fun `dehex with whitespace`() {
        assertArrayEquals(byteArrayOf(0xaa.toByte()), " aa ".convertHexStringToByteArray())
        assertArrayEquals(byteArrayOf(0xaa.toByte(), 0x34), " aa 34 l".convertHexStringToByteArray())
        assertArrayEquals(byteArrayOf(0xaa.toByte(), 0x34), " aa 340".convertHexStringToByteArray())
    }
}