package me.androidbox.domain.interactor.bookmark

import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import me.androidbox.domain.executor.PostExecutionThread
import me.androidbox.domain.interactor.mockdata.ProjectDataFactory
import me.androidbox.domain.repository.ProjectsRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UnBookmarkProjectTest {
    @Mock
    private lateinit var projectsRepository: ProjectsRepository

    @Mock
    private lateinit var postExecutionThread: PostExecutionThread

    private lateinit var unBookmarkProject: UnBookmarkProject

    @Before
    fun setup() {
        unBookmarkProject = UnBookmarkProject(projectsRepository, postExecutionThread)
    }

    @Test
    fun testBuildUseCaseCompletable_completes() {
        val projectId = ProjectDataFactory.randomUuid()
        whenever(projectsRepository.unBookmarkProject(projectId)).thenReturn(Completable.complete())

        val testObserver =
                unBookmarkProject.buildUseCaseCompletable(UnBookmarkProject.Params(projectId)).test()

        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun testBuildUseCaseComplete_throwsIllegalArgumentException() {
        unBookmarkProject.buildUseCaseCompletable(null).test()
    }
}