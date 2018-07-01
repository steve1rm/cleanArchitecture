package me.androidbox.domain.interactor.bookmark

import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import me.androidbox.domain.executor.PostExecutionThread
import me.androidbox.domain.interactor.mockdata.ProjectDataFactory
import me.androidbox.domain.repository.ProjectsRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class GetBookmarkedProjectsTest {
    private val projectsRepository = Mockito.mock(ProjectsRepository::class.java)
    private val postExecutionThread = Mockito.mock(PostExecutionThread::class.java)

    private lateinit var getBookmarkedProjects: GetBookmarkedProjects

    @Before
    fun setup() {
        getBookmarkedProjects = GetBookmarkedProjects(projectsRepository, postExecutionThread)
    }

    @Test
    fun testBuildUseCaseObservable_completes() {
        val projectList = ProjectDataFactory.makeProjectList(2)
        whenever(projectsRepository.getBookedMarkedProjects()).thenReturn(Observable.just(projectList))

        val testObserver = getBookmarkedProjects.buildUseCaseObservable().test()

        testObserver.assertComplete()
    }

    @Test
    fun testBuildUseCaseObservable_returnsListOfBookedProjects() {
        val projectList = ProjectDataFactory.makeProjectList(2)
        whenever(projectsRepository.getBookedMarkedProjects()).thenReturn(Observable.just(projectList))

        val testObserver = getBookmarkedProjects.buildUseCaseObservable().test()

        testObserver.assertValue(projectList)
    }
}
