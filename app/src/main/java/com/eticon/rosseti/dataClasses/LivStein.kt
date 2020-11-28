package com.eticon.rosseti.dataClasses

import java.util.*

object EditDistanceRecursive {
    fun calculate(x: String, y: String): Int {
        if (x.isEmpty()) {
            return y.length
        }
        if (y.isEmpty()) {
            return x.length
        }
        val substitution = (calculate(x.substring(1), y.substring(1))
                + costOfSubstitution(x[0], y[0]))
        val insertion = calculate(x, y.substring(1)) + 1
        val deletion = calculate(x.substring(1), y) + 1
        return min(substitution, insertion, deletion)
    }

    fun costOfSubstitution(a: Char, b: Char): Int {
        return if (a == b) 0 else 1
    }

    fun calculateTwo(x: String, y: String): Int {
        val dp = Array(x.length + 1) { IntArray(y.length + 1) }
        for (i in 0..x.length) {
            for (j in 0..y.length) {
                if (i == 0) {
                    dp[i][j] = j
                } else if (j == 0) {
                    dp[i][j] = i
                } else {
                    dp[i][j] = min(dp[i - 1][j - 1]
                            + costOfSubstitution(x[i - 1], y[j - 1]),
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1)
                }
            }
        }
        return dp[x.length][y.length]
    }

    fun min(vararg numbers: Int): Int {
        return Arrays.stream(numbers)
                .min().orElse(Integer.MAX_VALUE)
    }
}