typealias Board = Array<Array<Boolean>>

class QueensProblem {

    fun resultExists(n: Int): Boolean {
        val board = Array(n) { Array(n) { false } }
        return resultExists(board, n)
    }

    private fun resultExists(board: Board, n: Int): Boolean {
        if (n == 0) return true
        for (rowIndex in 0 until n) {
            for (columnIndex in 0 until n) {
                if (isAttacked(rowIndex, columnIndex, board)) continue
                board[rowIndex][columnIndex] = true
                if (resultExists(board, n - 1)) return true
                board[rowIndex][columnIndex] = false
            }
        }
        return false
    }

    private fun isAttacked(rowIndex: Int, columnIndex: Int, board: Board): Boolean {
        if (isQueenInRow(board, rowIndex)) return true
        if (isQueenInColumn(board, columnIndex)) return true
        if (isQueenInDiagonal(board, rowIndex, columnIndex)) return true
        return false
    }

    private fun isQueenInRow(board: Board, rowIndex: Int): Boolean {
        for (i in 0 until board.size) {
            if (board[rowIndex][i]) return true
        }
        return false
    }

    private fun isQueenInColumn(board: Board, columnIndex: Int): Boolean {
        for (i in 0 until board.size) {
            if (board[i][columnIndex]) return true
        }
        return false
    }

    private fun isQueenInDiagonal(board: Board, rowIndex: Int, columnIndex: Int): Boolean {
        for (p in 0 until board.size) {
            for (q in 0 until board.size) {
                if (board[p][q]) {
                    if (p + q == rowIndex + columnIndex) return true
                    if (p - q == rowIndex - columnIndex) return true
                }
            }
        }
        return false
    }
}
