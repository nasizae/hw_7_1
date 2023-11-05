package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.network.ApiService
import com.example.myapplication.data.room.Dao
import com.example.myapplication.data.room.DataBase
import com.example.myapplication.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context = context,
        klass = DataBase::class.java,
        name = "notes"
    ).build()
    @Provides
    fun provideNoteDao(database: DataBase) = database.getDao()

    @Provides
    fun provideApi(): ApiService =
        Retrofit.Builder().baseUrl("http://cars.cprogroup.ru/api/rubetek/").addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
}