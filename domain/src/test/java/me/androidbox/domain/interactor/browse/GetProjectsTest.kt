package me.androidbox.domain.interactor.browse

import me.androidbox.domain.executor.PostExecutionThread
import me.androidbox.domain.repository.ProjectsRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class GetProjectsTest {
    private lateinit var getProjects: GetProjects
    @Mock
    val projectsRepository: ProjectsRepository = Mockito.mock(ProjectsRepository::class.java)
    @Mock
    val postExecutionThread: PostExecutionThread = Mockito.mock(PostExecutionThread::class.java)

    @Before
    fun setup() {
        getProjects = GetProjects(projectsRepository, postExecutionThread)
    }

    @Test
    fun testBuildUseCaseObservable() {
        getProjects.
    }
}