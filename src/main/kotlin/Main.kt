fun main(args: Array<String>) {
    val problem = QueensProblem(4)
    val executor = BacktrackingExecutor(problem)
    val result = executor.findFirst()
    print(result)
}
