package com.wunderbee.kbyteutils

import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.system.measureNanoTime

class PerformanceTests {

    val r = Random(System.currentTimeMillis())

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun `Compare our hex to stdlib hex`() {

        val b = r.nextBytes(1_000_000)

        val t1 = measureNanoTime {
            b.convertByteArrayToHexString()
        }

        val t2 = measureNanoTime {
            b.toHexString(HexFormat.Default)
        }

        val dt = t1.toDouble() / t2

        println("Ratio: $dt")
    }

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun `Compare unhex`() {

        val hex = r.nextBytes(1_000_000).convertByteArrayToHexString()

        val t1 = measureNanoTime {
            hex.convertHexStringToByteArray()
        }

        val t2 = measureNanoTime {
            hex.hexToByteArray(HexFormat.Default)
        }

        val dt = t1.toDouble() / t2

        println("Ratio: $dt")
    }

}