class ForwardCheckingExecutor(problem: Problem) : CspExecutor(problem) {

    override fun printName() {
        println("Forward checking: ")
    }

    override fun searchInSubsequentValues(variableIndex: Int, problem: Problem): String? {
        for (value in problem.domainOfVariable(variableIndex)) {
            val updatedProblem = problem.setVariable(variableIndex, value)
            val newProblem = updatedProblem.updateDomains(variableIndex, value)
            if (!newProblem.someDomainEmpty) {
                val result = findFirst(variableIndex + 1, newProblem)
                result?.let { return it } ?: ++operationsCount
            }
        }
        return null
    }

    override fun checkValue(variableIndex: Int, value: Int, problem: Problem) {
        val newProblem = problem.updateDomains(variableIndex, value)
        if (!problem.someDomainEmpty) {
            count(variableIndex + 1, newProblem)
        }
        ++operationsCount
    }
}
