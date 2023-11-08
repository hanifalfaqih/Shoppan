package id.allana.shoppan.network.datasource.preference

import id.allana.shoppan.network.data.datastore.UserPreferences
import kotlinx.coroutines.flow.Flow

class PreferenceDataSourceImpl(
    private val userPreferences: UserPreferences
): PreferenceDataSource {
    override fun getToken(): Flow<String> {
        return userPreferences.getToken()
    }

    override suspend fun setToken(token: String) {
        userPreferences.setToken(token)
    }
}