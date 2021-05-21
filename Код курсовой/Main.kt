import java.util.*


fun main() {

}


fun problem_coins(piles: IntArray): Int {
    Arrays.sort(piles)
    var i = piles.size - 2 
    var j = 0
    var count = 0
    while (j++ < piles.size / 3) {
        count += piles[i]
        i -= 2
    }
    return count
}


fun probmes_baloons(points: Array<IntArray>): Int {
    Arrays.sort(points) { a: IntArray, b: IntArray -> a[1].compareTo(b[1]) }
    var end = points[0][1]
    var arrow = 1
    for (i in points.indices) {
        if (points[i][0] > end) {
            arrow++
            end = points[i][1]
        }
    }
    return arrow
}

fun problem_intervals(intervals: Array<IntArray>): Array<IntArray>? {
    Arrays.sort(intervals) { a: IntArray, b: IntArray -> a[0].compareTo(b[0]) }
    val merged = LinkedList<IntArray>()
    for (interval in intervals) {
        if (merged.isEmpty() || merged.last[1] < interval[0]) {
            merged.add(interval)
        } else {
            merged.last[1] = Math.max(merged.last[1], interval[1])
        }
    }
    return merged.toTypedArray()
}

//1
fun checkIfCanBreak(s1: String, s2: String): Boolean {
    val arr1 = s1.toCharArray()
    val arr2 = s2.toCharArray()
    Arrays.sort(arr1)
    Arrays.sort(arr2)
    return helper(arr1, arr2) || helper(arr2, arr1)
}

fun helper(arr1: CharArray, arr2: CharArray): Boolean {
    for (i in arr1.indices) {
        if (arr1[i] < arr2[i]) {
            return false
        }
    }
    return true
}

//2

fun longestPalindrome(s: String): String? {
    if (s.isEmpty()) return ""
    var start = 0
    var end = 0
    for (i in 0 until s.length) {
        val len1 = expandAroundCenter(s, i, i)
        val len2 = expandAroundCenter(s, i, i + 1)
        val len = Math.max(len1, len2)
        if (len > end - start) {
            start = i - (len - 1) / 2
            end = i + len / 2
        }
    }
    return s.substring(start, end + 1)
}

fun expandAroundCenter(s: String, left: Int, right: Int): Int {
    var L = left
    var R = right
    while (L >= 0 && R < s.length && s[L] == s[R]) {
        L--
        R++
    }
    return R - L - 1
}

// 3
fun concetCout(s: String): Int {
    var j: Int
    var cout = 0
    for (i in s.indices) {
        j = 1
        while (i + 2 * j < s.length) {
            if (s.substring(i, i + j) == s.substring(i + j, i + 2 * j))
                cout++
            j++
        }
    }
    return cout
}


//var arr = arrayOf(intArrayOf(10, 16), intArrayOf(2, 8), intArrayOf(1, 6), intArrayOf(7, 12))