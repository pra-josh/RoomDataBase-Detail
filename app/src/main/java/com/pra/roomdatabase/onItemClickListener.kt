package com.pra.roomdatabase

import com.pra.roomdatabase.adapter.TypeClicked

interface onItemClickListener {
    fun onItemClick(position:Int, typeClicked: TypeClicked)
}