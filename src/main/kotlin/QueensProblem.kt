class QueensProblem(
        override val numberOfVariables: Int,
        private val domains: List<Domain> = List(numberOfVariables) { List(numberOfVariables) { it + 1 } },
        private val board: List<Int> = List(numberOfVariables) { 0 }
) : Problem {

    override val currentResult: String
        get() = board.toString()

    override val someDomainEmpty: Boolean
        get() = domains.any { it.isEmpty() }

    override fun setVariable(variableIndex: Int, value: Int): Problem {
        val mutableList = board.toMutableList()
        mutableList[variableIndex] = value
        return QueensProblem(numberOfVariables, domains, mutableList)
    }

    override fun domainOfVariable(variableIndex: Int): Domain = domains[variableIndex]

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
