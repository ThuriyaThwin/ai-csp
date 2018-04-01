class BacktrackingExecutor(problem: Problem) : CspExecutor(problem) {

    override fun printName() {
        println("Backtracking: ")
    }

    override fun searchInSubsequentValues(variableIndex: Int, problem: Problem): String? {
        for (value in problem.domainOfVariable(variableIndex)) {
            val newProblem = problem.setVariable(variableIndex, value)
            if (newProblem.areConstrainsSatisfied(variableIndex, value)) {
                findFirst(variableIndex + 1, newProblem)?.let { return it } ?: ++operationsCount
            }
        }
        return null
    }

    override fun checkValue(variableIndex: Int, value: Int, problem: Problem) {
        val newProblem = problem.setVariable(variableIndex, value)
        if (newProblem.areConstrainsSatisfied(variableIndex, value)) {
            count(variableIndex + 1, newProblem)
        }
        ++operationsCount
    }
}
