package me.androidbox.data.store

import me.androidbox.data.repository.ProjectsDataStore
import javax.inject.Inject

class ProjectsDataStoreFactory @Inject constructor(
        private val projectsCacheDataStore: ProjectsCacheDataStoreImp,
        private val projectsRemoteDataStore: ProjectsRemoteDataStoreImp) {

    fun getDataStore(projectsCached: Boolean, cacheExpired: Boolean): ProjectsDataStore {
        return if(projectsCached && !cacheExpired) {
            projectsCacheDataStore
        }
        else {
            projectsRemoteDataStore
        }
    }

    fun getCachedDataStore(): ProjectsDataStore {
        return projectsCacheDataStore
    }
}