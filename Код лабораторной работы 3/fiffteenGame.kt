import java.util.*


fun main(args: Array<String>) {
    val `in` = Scanner(System.`in`)
    val sb = `in`.nextLine() + " " +
            `in`.nextLine() + " " +
            `in`.nextLine() + " " +
            `in`.nextLine()
    val field = Arrays.stream(sb.split(" ".toRegex()).toTypedArray()).mapToInt { s: String -> s.toInt() }.toArray()
    if (!checkState(field)) {
        val astar = Astar()
        val res = astar.search(State(field))
        if (res == null) {
            println("Решений не нашлось")
        } else {
            for (s in res) println(s.toString())
        }
    } else println("[]")
}

fun checkState(field: IntArray): Boolean {
    var inv = 0
    for (i in 0..15) if (field[i] != 0) for (j in 0 until i) if (field[j] > field[i]) ++inv
    for (i in 0..15) if (field[i] == 0) inv += 1 + i / 4
    return inv % 2 == 1
}



class Astar {
    var finalfield = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0)
    private var closedStates = 0
    fun search(startState: State): Collection<State?>? {
        val close = LinkedList<State?>()
        val open = LinkedList<State?>()
        open.add(startState)
        startState.g = 0
        startState.h = getH(startState)
        while (!open.isEmpty()) {
            val x = getStateWithMinF(open)
            if (isTerminate(x)) {
                closedStates = close.size
                return completeSolution(x)
            }
            open.remove(x)
            close.add(x)
            val neighbors = getNeighbors(x)
            for (neighbor in neighbors) {
                if (close.contains(neighbor)) {
                    continue
                }
                val g = x!!.g + getDistance(x, neighbor)
                var isGBetter: Boolean
                if (!open.contains(neighbor)) {
                    neighbor!!.h = getH(neighbor)
                    open.add(neighbor)
                    isGBetter = true
                } else {
                    isGBetter = g < neighbor!!.g
                }
                if (isGBetter) {
                    neighbor.parent = x
                    neighbor.g = g
                }
            }
        }
        return null
    }

    private fun getDistance(a: State?, b: State?): Int {
        var c = b
        var res: Int = 0
        while (c != null && c != a) {
            c = c.parent
            ++res
        }
        return res
    }

    fun getNeighbors(currentState: State?): List<State?> {
        val res: ArrayList<State?> = ArrayList<State?>()
        val field = currentState!!.field
        var zero = 0
        while (zero < 16) {
            if (field[zero] == 0) {
                break
            }
            zero++
        }
        if (zero - 4 >= 0) res.add(State(swap(field, zero, zero - 4), zero - 4))
        if (zero + 4 < 16) res.add(State(swap(field, zero, zero + 4), zero + 4))
        if ((zero + 1) / 4 == zero / 4 && zero + 1 < 16) res.add(State(swap(field, zero, zero + 1), zero + 1))
        if ((zero - 1) / 4 == zero / 4 && zero - 1 >= 0) res.add(State(swap(field, zero, zero - 1), zero - 1))
        return res
    }

    private fun swap(arr: IntArray, a: Int, b: Int): IntArray {
        val newField = arr.copyOf(16)
        val temp = newField[a]
        newField[a] = newField[b]
        newField[b] = temp
        return newField
    }

    private fun completeSolution(terminate: State?): Collection<State?> {
        val path: LinkedList<State?> = LinkedList<State?>()
        var c = terminate
        while (c != null) {
            path.addFirst(c)
            c = c.parent
        }
        return path
    }

    private fun isTerminate(x: State?): Boolean {
        return Arrays.equals(x!!.field, finalfield)
    }

    private fun getStateWithMinF(open: Collection<State?>): State? {
        var res: State? = null
        var min = Int.MAX_VALUE
        for (state in open) {
            if (state!!.f < min) {
                min = state.f
                res = state
            }
        }
        return res
    }

    private fun getH(startState: State?): Int {
        var diff = 0
        val field = startState!!.field
        for (i in 0..15) {
            if (finalfield[i] != field[i]) diff++
        }
        return diff
    }
}


class State {
    var g = 0
    var h = 0
    var parent: State? = null
    lateinit var field: IntArray
    private val size = 16

    var changed = 0

    val f: Int
        get() = g + h

    constructor(parent: State?) {
        this.parent = parent
    }

    constructor(field: IntArray, changed: Int) {
        this.field = field
        this.changed = changed
    }

    val path: Unit
        get() {
            if (parent != null) {
                print(" $changed")
            }
        }

    override fun toString(): String {
        val sb = StringBuilder()
        for (i in 0..3) {
            for (j in 0..3) {
                sb.append(field[j + i * 4]).append("\t")
            }
            sb.append("\n")
        }
        return sb.toString()
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val state = o as State
        return field.contentEquals(state.field)
    }

    override fun hashCode(): Int {
        return field.contentHashCode()
    }

    internal constructor(field: IntArray) {
        this.field = field
    }
}


