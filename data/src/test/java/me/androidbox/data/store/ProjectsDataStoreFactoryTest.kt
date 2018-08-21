package me.androidbox.data.store

import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class ProjectsDataStoreFactoryTest {

    private lateinit var projectsDataStoreFactory: ProjectsDataStoreFactory
    private val projectCacheDataStoreImp: ProjectsCacheDataStoreImp = mock()
    private val projectsRemoteDataStoreImp: ProjectsRemoteDataStoreImp = mock()

    @Before
    fun setup() {
        projectsDataStoreFactory = ProjectsDataStoreFactory(
                projectCacheDataStoreImp, projectsRemoteDataStoreImp)
    }

    @Test
    fun `test ProjectsDataStoreFactory is not null value`() {
        assertThat(projectsDataStoreFactory).isNotNull()
    }

    @Test
    fun `test getDataStore return projectCacheDataStore when cached and cache has not expired`() {
        assertThat(projectsDataStoreFactory.getDataStore(true, false))
                .isEqualTo(projectCacheDataStoreImp)
    }

    @Test
    fun `test getDataStore returns remoteDataStore when not cached`() {
        assertThat(projectsDataStoreFactory.getDataStore(false, false))
                .isEqualTo(projectsRemoteDataStoreImp)
    }

    @Test
    fun `test getDataStore returns remoteDataStore project are not cached`() {
        assertThat(projectsDataStoreFactory.getDataStore(false, true))
                .isEqualTo(projectsRemoteDataStoreImp)
    }

    @Test
    fun `test getDataStore returns remoteDataStore when projects cache and cache has expired`() {
        assertThat(projectsDataStoreFactory.getDataStore(true, true))
                .isEqualTo(projectsRemoteDataStoreImp)
    }

    @Test
    fun `test getCacheDataStore return cacheDataStore`() {
        assertThat(projectsDataStoreFactory.getCachedDataStore())
                .isEqualTo(projectCacheDataStoreImp)
    }
}
