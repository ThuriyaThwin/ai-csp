fun main(args: Array<String>) {
    val problem = QueensProblem(4)
    val result = BacktrackingExecutor(problem).findFirst()
    print(result)
}
