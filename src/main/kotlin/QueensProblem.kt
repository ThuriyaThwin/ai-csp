data class QueensProblem(
        override val numberOfVariables: Int,
        private val board: List<Variable> = List(numberOfVariables) {
            Variable(0, List(numberOfVariables) { it + 1 })
        }
) : Problem {

    override val currentResult: String
        get() = board.map { it.value }.toString()

    override val someDomainEmpty: Boolean
        get() = board.any { it.domain.isEmpty() }

    override fun setVariable(variableIndex: Int, value: Int): Problem {
        val updatedBoard = board.copy { this[variableIndex] = this[variableIndex].copy(value = value) }
        return copy(board = updatedBoard)
    }

    override fun domainOfVariable(variableIndex: Int): Domain = board[variableIndex].domain

    override fun areConstrainsSatisfied(variableIndex: Int, value: Int): Boolean {
        for (previousColumn in 0 until variableIndex) {
            if (board[previousColumn].value == value) return false
            if (variableIndex - previousColumn == Math.abs(value - board[previousColumn].value)) return false
        }
        return true
    }

    override fun updateDomains(variableIndex: Int, value: Int): Problem {
        val newBoard = board.copy {
            var increment = 1
            for (columnIndex in (variableIndex + 1) until numberOfVariables) {
                val oldDomain = this[columnIndex].domain
                val newDomain = oldDomain - value - (value + increment) - (value - increment)
                this[columnIndex] = this[columnIndex].copy(domain = newDomain)
                ++increment
            }
        }
        return QueensProblem(numberOfVariables, newBoard)
    }
}
