package com.example.sgprepartidor.core.data.local.Order.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.sgprepartidor.core.data.local.Client.entities.ClientEntity

@Entity(
    tableName = "Orders",
    foreignKeys = [
        ForeignKey(
            entity = ClientEntity::class,
            parentColumns = ["id"],
            childColumns = ["client_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["client_id"])]
    )
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "client_id")
    val clientId: Int,
    @ColumnInfo(name = "date")
    val data: Long,
    @ColumnInfo(name = "completed")
    val completed: Boolean
)
