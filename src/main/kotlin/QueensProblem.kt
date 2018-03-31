class QueensProblem(override val numberOfVariables: Int) : Problem {

    private val board = Array(numberOfVariables) { 0 }

    override val domains: List<Domain> = List(numberOfVariables) { List(numberOfVariables) { it + 1 } }

    override val currentResult: String
        get() = board.contentDeepToString()

    override fun setVariable(variableIndex: Int, value: Int) {
        board[variableIndex] = value
    }

    override fun resetVariable(variableIndex: Int, value: Int) {
        board[variableIndex] = 0
    }

    override fun areConstrainsSatisfied(variableIndex: Int, value: Int): Boolean {
        for (previousColumn in 0 until variableIndex) {
            if (board[previousColumn] == value) return false
            if (variableIndex - previousColumn == Math.abs(value - board[previousColumn])) return false
        }
        return true
    }

    override fun updateDomains(variableIndex: Int, value: Int): Problem {
        TODO()
    }
}
