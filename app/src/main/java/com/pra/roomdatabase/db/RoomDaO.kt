package com.pra.roomdatabase.db

import androidx.room.*
import com.pra.roomdatabase.model.Products


@Dao
interface RoomDaO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProduct(products: Products)

    @Query("select * from Products")
    fun getProduct(): List<Products>

    @Update
    fun updateProduct(products: Products)

    @Delete
    fun deleteProduct(products: Products)

}