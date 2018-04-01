class ForwardCheckingExecutor(private val problem: Problem) : CspExecutor {

    override fun printName() {
        println("Forward checking: ")
    }

    override fun findFirst(): String? {
        return findFirst(0, problem)
    }

    private fun findFirst(variableIndex: Int, problem: Problem): String? =
            if (variableIndex == problem.numberOfVariables) {
                problem.currentResult
            } else {
                searchInSubsequentValues(problem, variableIndex)
            }

    private fun searchInSubsequentValues(problem: Problem, variableIndex: Int): String? {
        for (value in problem.domainOfVariable(variableIndex)) {
            problem.setVariable(variableIndex, value)
            val newProblem = problem.updateDomains(variableIndex, value)
            if (!problem.someDomainEmpty) {
                val result = findFirst(variableIndex + 1, newProblem)
                result?.let { return it }
            }
        }
        return null
    }

    private var counter: Int = 0

    override fun countAll(): Int {
        counter = 0
        count(0, problem)
        return counter
    }

    private fun count(variableIndex: Int, problem: Problem) {
        if (variableIndex == problem.numberOfVariables) {
            ++counter
        } else {
            checkSubsequentValues(variableIndex, problem)
        }
    }

    private fun checkSubsequentValues(variableIndex: Int, problem: Problem) {
        for (value in problem.domainOfVariable(variableIndex)) {
            checkValue(variableIndex, value, problem)
        }
    }

    private fun checkValue(variableIndex: Int, value: Int, problem: Problem) {
        problem.setVariable(variableIndex, value)
        val newProblem = problem.updateDomains(variableIndex, value)
        if (!problem.someDomainEmpty) {
            count(variableIndex + 1, newProblem)
        }
    }
}
