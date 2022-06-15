package com.nonbinsys.greenersapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nonbinsys.greenersapp.paquete.PaqueteCarrito

@Database(entities = [PaqueteCarrito::class], version = 1)
@TypeConverters(PaqueteTypeConverter::class)
abstract class PaqueteDatabase: RoomDatabase() {
    abstract fun paqueteDao(): PaqueteDao

    companion object{
        @Volatile
        var INSTANCE: PaqueteDatabase? = null

        fun getInstance(context: Context): PaqueteDatabase{
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    PaqueteDatabase::class.java,
                    "carrito.db"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as PaqueteDatabase
        }
    }
}