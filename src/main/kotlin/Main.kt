fun main(args: Array<String>) {
    serveUser()
}

private fun serveUser() {
    try {
        tryToServeUser()
    } catch (e: Exception) {
        println(e)
        serveUser()
    }
}

private fun tryToServeUser() {
    println("Usage: n first/all")
    val line = readLine()!!.split(' ')
    val n = line[0].toInt()
    val mode = line[1]
    val first = isFirstMode(mode)
    printQueensResult(n, first)
    serveUser()
}

private fun isFirstMode(mode: String) = mode[0] in arrayOf('f', 'F')

private fun printQueensResult(n: Int, first: Boolean) {
    val problem = QueensProblem(n)
    val executor = BacktrackingExecutor(problem)
    val result: Any? = if (first) executor.findFirst() else executor.countAll()
    println(result)
}
