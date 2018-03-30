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
    println("Usage: n")
    val line = readLine()!!.split(' ')
    val n = line[0].toInt()
    printQueensResult(n)
    serveUser()
}

private fun printQueensResult(n: Int) {
    val problem = QueensProblem(n)
    val executor = BacktrackingExecutor(problem)
    val result = executor.findFirst()
    println(result)
}