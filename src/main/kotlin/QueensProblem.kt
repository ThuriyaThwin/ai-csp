class QueensProblem(
        override val numberOfVariables: Int,
        override val domains: List<Domain> = List(numberOfVariables) { List(numberOfVariables) { it + 1 } },
        private val board: Array<Int> = Array(numberOfVariables) { 0 }
) : Problem {

    override val currentResult: String
        get() = board.contentDeepToString()

    override val someDomainEmpty: Boolean
        get() = domains.any { it.isEmpty() }

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
        val newDomains = domains.toMutableList()
        var increment = 1
        for (columnIndex in (variableIndex + 1) until numberOfVariables) {
            newDomains[columnIndex] = newDomains[columnIndex] - value
            newDomains[columnIndex] = newDomains[columnIndex] - (value + increment)
            newDomains[columnIndex] = newDomains[columnIndex] - (value - increment)
            ++increment
        }
        return QueensProblem(numberOfVariables, newDomains, board) // todo: Clone?
    }
}
