package com.wunderbee.kbyteutils


fun ByteArray.hex(): String {
    return ExtensionFunctions.convertByteArrayToString(this)
}

fun String.dehex(): ByteArray {
    return ExtensionFunctions.convertStringToByteArray(this)
}


class ExtensionFunctions {


    companion object {

        val hexChars = "0123456789abcdef"
        val hexCharsUppercase = "0123456789ABCDEF"

        fun convertByteArrayToString(byteArray: ByteArray): String {
            val s = CharArray(byteArray.size * 2)
            byteArray.forEachIndexed { index, byte ->
                s[index * 2] = hexChars[byte.toInt().shr(4).and(0xf)]
                s[index * 2 + 1] = hexChars[byte.toInt().and(0xf)]
            }
            return String(s)
        }

        fun convertStringToByteArray(s: String): ByteArray {
            val b = ByteArray(s.length / 2)
            var idx = 0
            var tmp = 0
            s.forEach { c ->
                val n = charToIntVal(c)
                if (n >= 0) {
                    if (tmp > 0) {
                        tmp = tmp.or(n)
                        b[idx++] = tmp.toByte()
                        tmp = 0
                    } else {
                        tmp = n.shl(4)
                    }
                } else {
                    // ignore errors, skip whitespace
                }
            }
            // When whitespace was used, b is too large and needs trim
            return if (idx < b.size) {
                b.copyOf(idx)
            } else {
                b
            }
        }

        private fun charToIntVal(c: Char): Int {
            var nibbleVal: Int = hexChars.indexOf(c)
            if (nibbleVal < 0) {
                nibbleVal = hexCharsUppercase.indexOf(c)
            }
            return nibbleVal
        }

    }
}