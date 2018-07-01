package me.androidbox.domain.interactor.browse

import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import me.androidbox.domain.executor.PostExecutionThread
import me.androidbox.domain.interactor.mockdata.ProjectDataFactory
import me.androidbox.domain.model.Project
import me.androidbox.domain.repository.ProjectsRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class GetProjectsTest {
    private lateinit var getProjects: GetProjects

    @Mock
    private val projectsRepository: ProjectsRepository
            = Mockito.mock(ProjectsRepository::class.java)
    @Mock
    private val postExecutionThread: PostExecutionThread
            = Mockito.mock(PostExecutionThread::class.java)

    @Before
    fun setup() {
        getProjects = GetProjects(projectsRepository, postExecutionThread)
    }

    @Test
    fun testBuildUseCaseObservable_completes() {
        stubGetProjects(Observable.just(ProjectDataFactory.makeProjectList(2)))

        val testObserver = getProjects.buildUseCaseObservable().test()

        testObserver.completions()
    }

    @Test
    fun testBuildUseCaseObservable_returnsData() {
        val projects = ProjectDataFactory.makeProjectList(2)
        stubGetProjects(Observable.just(projects))

        val testObserver = getProjects.buildUseCaseObservable().test()

        testObserver.assertValue(projects)
    }

    private fun stubGetProjects(observable: Observable<List<Project>>) {
        whenever(projectsRepository.getProjects()).thenReturn(observable)
    }
}