package yandex

import java.lang.Integer.max

object Zeros {

    fun maxOnesSequence(array: List<Int>): Int {
        var seqStart = -1
        val oneRanges = mutableListOf<IntRange>()
        for (i in array.indices) {
            val it = array[i]
            when {
                seqStart == -1 && it == 1 && i == array.lastIndex -> {
                    oneRanges.add(i..i)
                }
                seqStart == -1 && it == 1 -> seqStart = i
                seqStart != -1 && it == 0 -> {
                    oneRanges.add(seqStart until i)
                    seqStart = -1
                }
                seqStart != -1 && i == array.lastIndex -> {
                    oneRanges.add(seqStart..i)
                }
                else -> Unit
            }
        }
        // merge ranges
        var maxLength = oneRanges.firstOrNull()?.length() ?: 0

        if (oneRanges.size == 1 && (oneRanges.firstOrNull()?.length() ?: 0) == array.size) {
            maxLength--
        }

        for (i in 0 until oneRanges.lastIndex) {
            val currentRange = oneRanges[i]
            val nextRange = oneRanges[i + 1]
            // zero between ranges
            if (currentRange.last + 2 == nextRange.first) {
                maxLength = max(currentRange.length() + nextRange.length(), maxLength)
            }
        }

        return maxLength
    }

    private fun IntRange.length(): Int {
        return last - first + 1
    }
}
