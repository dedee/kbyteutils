package com.wunderbee.kbyteutils


fun ByteArray.convertByteArrayToHexString(): String {
    return HexUtils.convertByteArrayToHexString(this)
}

fun String.convertHexStringToByteArray(): ByteArray {
    return HexUtils.convertHexStringToByteArray(this)
}


class HexUtils {

    companion object {

        private const val HEX_CHARACTERS_LOWERCASE = "0123456789abcdef"
        private const val HEX_CHARACTERS_UPPERCASE = "0123456789ABCDEF"

        /**
         * Converts a byte array into hex string.
         * @param byteArray Byte array to convert
         * @return Hex String
         */
        fun convertByteArrayToHexString(byteArray: ByteArray): String {
            val s = CharArray(byteArray.size * 2)
            byteArray.forEachIndexed { index, byte ->
                s[index * 2] = HEX_CHARACTERS_LOWERCASE[byte.toInt().shr(4).and(0xf)]
                s[index * 2 + 1] = HEX_CHARACTERS_LOWERCASE[byte.toInt().and(0xf)]
            }
            return String(s)
        }

        /**
         * Converts a hex string to byte array.
         * This functions ignores invalid chars and whitespace.
         * @param s Hex string, eg '01FE'
         * @return byte array
         */
        fun convertHexStringToByteArray(s: String): ByteArray {
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

        /**
         * Return decimal value of HEX char, eg 'A' -> 10
         * @return Decimal value of hex character or -1 if invalid char
         */
        private fun charToIntVal(c: Char): Int {
            var nibbleVal: Int = HEX_CHARACTERS_LOWERCASE.indexOf(c)
            if (nibbleVal < 0) {
                nibbleVal = HEX_CHARACTERS_UPPERCASE.indexOf(c)
            }
            return nibbleVal
        }

    }
}