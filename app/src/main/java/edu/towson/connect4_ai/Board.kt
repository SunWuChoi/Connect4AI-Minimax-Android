package edu.towson.connect4_ai


class Board : IBoard {

    private var currentPlayer = Player.X
    private var winner = Player.EMPTY
    lateinit var grid: List<MutableList<Player>>

    init {
        reset()
    }

    override fun getWinner(): Winner {
        winner = when(hasWinner()) {
            true -> currentPlayer
            false -> Player.EMPTY
        }
        return when(winner) {
            Player.X -> Winner.X
            Player.O -> Winner.O
            Player.EMPTY -> {
                if(boardFull()) {
                    Winner.TIE
                } else {
                    Winner.NONE
                }
            }
        }
    }

    private fun boardFull(): Boolean {
        return grid.all {
            it.none { it == Player.EMPTY }
        }
    }

    private fun hasWinner(): Boolean {
        var count = 0
        var height = 5 //y
        var width = 6 //x

        //vertical win check
        for(x in 0 .. 6){
            count = 0
            for(y in 0 .. 5){
                if(grid[y][x] == currentPlayer){
                    count++

                    if(count >= 4){
                        return true
                    }
                }else{
                    count = 0
                }
            }
        }

        //horizontal win check
        for(y in 0 .. 5){
            count = 0
            for(x in 0 .. 6){
                if(grid[y][x] == currentPlayer){
                    count++

                    if(count >= 4){
                        return true
                    }
                }else{
                    count = 0
                }
            }
        }

        //diagonal top right to bottom left upper quarter
        for(x in 3..5){
            count = 0
            var newx = x
            var newy = 0
            while(newx >= 0) {
                if (grid[newy][newx] == currentPlayer) {
                    count++
                    if (count >= 4) {
                        return true
                    }
                } else {
                    count = 0
                }
                newx--
                newy++
            }
        }


        //diagonal top right to bottom left lower quarter
        for(x in 1..3){
            count = 0
            var newy = height
            var newx = x
            while(newx <= 6) {
                if (grid[newy][newx] == currentPlayer) {
                    count++
                    if (count >= 4) {
                        return true
                    }
                } else {
                    count = 0
                }
                newx++
                newy--
            }
        }


        //diagonal top left to bottom right upper quarter
        for(x in 1..3){
            count = 0
            var newy = 0
            var newx = x
            while(newx <= 6) {
                if (grid[newy][newx] == currentPlayer) {
                    count++
                    if (count >= 4) {
                        return true
                    }
                } else {
                    count = 0
                }
                newx++
                newy++
            }
        }


        //diagonal top left to bottom right lower quarter
        for(x in 3..5){
            count = 0
            var newy = height
            var newx = x
            while(newx >= 0) {
                if (grid[newy][newx] == currentPlayer) {
                    count++
                    if (count >= 4) {
                        return true
                    }
                } else {
                    count = 0
                }
                newx--
                newy--
            }
        }

        return false
    }

    fun fromTop(col: Int): Boolean{
        var emptyPos = -1
        var x = col-1

        if(grid[0][x] != Player.EMPTY){
            return false
        }

        for (y in 0..5) {
            if(grid[y][x] == Player.EMPTY){
                emptyPos++
            }
        }
        return setLocation(emptyPos,x)
    }

    override fun setLocation(x: Int, y: Int): Boolean {
        //System.out.println(x)
        //System.out.println(y)
        if(winner != Player.EMPTY) return false
        if(x in 0..5) {
            if(y in 0..6) {
                if(grid[x][y] != Player.EMPTY) {
                    return false
                }
                grid[x][y] = currentPlayer
                return true
            }
        }
        return false
    }

    override fun updateCurrentPlayer() {
        currentPlayer = when(currentPlayer) {
            Player.EMPTY -> throw Exception("Why is current player EMPTY?")
            Player.X -> Player.O
            Player.O -> Player.X
        }
    }

    override fun getBoard(): List<List<Player>> {
        return grid
    }

    override fun getCurrentPlayer(): Player {
        return currentPlayer
    }

    override fun reset() {
        grid = (0..5).map {
            (0..6).map { Player.EMPTY }.toMutableList()
        }
        winner = Player.EMPTY
        currentPlayer = Player.X
    }

    enum class Player { X, O, EMPTY }
    enum class Winner { X, O, TIE, NONE }
}

interface IBoard {
    fun getWinner(): Board.Winner
    fun setLocation(x: Int, y: Int): Boolean
    fun updateCurrentPlayer()
    fun getCurrentPlayer(): Board.Player
    fun getBoard(): List<List<Board.Player>>
    fun reset()
}