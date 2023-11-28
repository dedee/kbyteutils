package com.wunderbee.kbyteutils

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ExtensionFunctionsKtTest {

    @Test
    fun hex() {
        assertEquals("", byteArrayOf().hex())
        assertEquals("12", byteArrayOf(0x12.toByte()).hex())
        assertEquals("aa", byteArrayOf(0xaa.toByte()).hex())
        assertEquals("aabb", byteArrayOf(0xaa.toByte(), 0xbb.toByte()).hex())
    }

    @Test
    fun dehex() {
        assertArrayEquals(byteArrayOf(), "".dehex())
        assertArrayEquals(byteArrayOf(0xaa.toByte()), "aa".dehex())
        assertArrayEquals(byteArrayOf(0x12.toByte()), "12".dehex())
        assertArrayEquals(byteArrayOf(0xaa.toByte(), 0xbb.toByte()), "aabb".dehex())
    }

    @Test
    fun `dehex with whitespace`() {
        assertArrayEquals(byteArrayOf(0xaa.toByte()), " aa ".dehex())
        assertArrayEquals(byteArrayOf(0xaa.toByte(), 0x34), " aa 34 l".dehex())
        assertArrayEquals(byteArrayOf(0xaa.toByte(), 0x34), " aa 340".dehex())
    }
}