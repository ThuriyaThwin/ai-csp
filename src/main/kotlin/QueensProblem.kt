class QueensProblem(n: Int) : Problem {

    private val board = Array(n) { Array(n) { false } }

    override val domains: List<Domain> = List(n) { List(n) { it + 1 } }

    override val currentResult: String
        get() = board.contentDeepToString()

    override fun setVariable(variableIndex: Int, value: Int) {
        board[value - 1][variableIndex] = true
    }

    override fun resetVariable(variableIndex: Int, value: Int) {
        board[value - 1][variableIndex] = false
    }

    override fun areConstrainsSatisfied(variableIndex: Int, value: Int): Boolean {
        return !isAttacked(value - 1, variableIndex)
    }

    private fun isAttacked(rowIndex: Int, columnIndex: Int): Boolean {
        if (isOtherQueenInRow(rowIndex, columnIndex)) return true
        if (isOtherQueenInColumn(columnIndex, rowIndex)) return true
        if (isOtherQueenInDiagonal(rowIndex, columnIndex)) return true
        return false
    }

    private fun isOtherQueenInRow(rowIndex: Int, columnIndex: Int): Boolean {
        for (otherColumnIndex in (0 until board.size) - columnIndex) {
            if (board[rowIndex][otherColumnIndex]) return true
        }
        return false
    }

    private fun isOtherQueenInColumn(columnIndex: Int, rowIndex: Int): Boolean {
        for (otherRowIndex in (0 until board.size) - rowIndex) {
            if (board[otherRowIndex][columnIndex]) return true
        }
        return false
    }

    private fun isOtherQueenInDiagonal(rowIndex: Int, columnIndex: Int): Boolean {
        for (p in 0 until board.size) {
            for (q in 0 until board.size) {
                if (board[p][q] && (p != rowIndex || q != columnIndex)) {
                    if (p + q == rowIndex + columnIndex) return true
                    if (p - q == rowIndex - columnIndex) return true
                }
            }
        }
        return false
    }
}
