package id.allana.shoppan.network.datasource.preference

import kotlinx.coroutines.flow.Flow

interface PreferenceDataSource {
    fun getToken(): Flow<String>
    suspend fun setToken(token: String)
}