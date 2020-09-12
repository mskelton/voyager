package com.markskelton.voyager.modules

import android.app.Application
import androidx.room.Room
import com.markskelton.voyager.api.WebService
import com.markskelton.voyager.api.adapter.LiveDataCallAdapterFactory
import com.markskelton.voyager.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideWebService(): WebService {
        return Retrofit.Builder()
            .baseUrl("https://899cd40e7117.ngrok.io")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(WebService::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, "app.db")
            .fallbackToDestructiveMigrationFrom(2)
            .build()
    }

    @Singleton
    @Provides
    fun provideLogDao(db: AppDatabase) = db.logDao()

    @Singleton
    @Provides
    fun provideProjectDao(db: AppDatabase) = db.projectDao()

    @Singleton
    @Provides
    fun provideVehicleDao(db: AppDatabase) = db.vehicleDao()
}
