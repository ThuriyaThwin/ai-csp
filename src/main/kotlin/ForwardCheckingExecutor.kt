class ForwardCheckingExecutor(private val problem: Problem) : CspExecutor {

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
        for (value in problem.domains[variableIndex]) {
            problem.setVariable(variableIndex, value)
            val newProblem = problem.updateDomains(variableIndex, value)
            if (!problem.someDomainEmpty) {
                val result = findFirst(variableIndex + 1, newProblem)
                result?.let { return it }
            }
        }
        return null
    }

    override fun countAll(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
