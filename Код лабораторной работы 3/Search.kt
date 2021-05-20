import java.util.*


fun main(args: Array<String>){
    val `in` = Scanner(System.`in`)
    val flag = false
    println("Введите исходную строку:")
    var source: String = `in`.nextLine();
    println("Введите строку для поиска:")
    var template: String = `in`.nextLine();
    if(flag){
        source = source.toLowerCase();
        template = template.toLowerCase();
    }
    var m = System.currentTimeMillis()
    var index = KmpSearch(source, template)
    m = System.currentTimeMillis() - m
    println("Алгоритм Кнута-Морриса-Пратта выполнился за $m милисекунд. Индекс слова = $index")
    m = System.currentTimeMillis()
    index = BMSearch(source, template)
    m = System.currentTimeMillis() - m
    println("Алгоритм Бойера-Мура выполнился за $m милисекунд. Индекс слова = $index")
}
fun KmpSearch(source: String, x: String): Int?{
    val d: ArrayList<Int> = arrayListOf(0)
    val template = "$x#$source"
    for (i in 1..template.length) {
        var j = d[i - 1]
        while (j > 0 && template[j] != template[i])
            j = d[j - 1]
        if (template[j] == template[i])
            j += 1
        d.add(i, j)
        if (j == x.length)
            return i-3
    }
    return null
}

fun BMSearch(source: String, template: String): Int? {
    val sourceLen = source.length
    val templateLen = template.length
    if (templateLen > sourceLen) {
        return null
    }
    val offsetTable = HashMap<Char, Int>()
    for (i in 0..255) {
        offsetTable[i.toChar()] = templateLen
    }
    for (i in 0 until templateLen - 1) {
        offsetTable[template[i]] = templateLen - i - 1
    }
    var i = templateLen - 1
    var j = i
    var k = i
    while (j >= 0 && i <= sourceLen - 1) {
        j = templateLen - 1
        k = i
        while (j >= 0 && source[k] == template[j]) {
            k--
            j--
        }
        i += offsetTable[source[i]]!!
    }
    return if (k >= sourceLen - templateLen) {
        null
    } else {
        k + 2
    }
}
