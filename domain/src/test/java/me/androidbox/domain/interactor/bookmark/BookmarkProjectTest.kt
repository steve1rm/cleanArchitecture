package me.androidbox.domain.interactor.bookmark

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import me.androidbox.domain.executor.PostExecutionThread
import me.androidbox.domain.interactor.mockdata.ProjectDataFactory
import me.androidbox.domain.repository.ProjectsRepository
import org.junit.Before
import org.junit.Test

class BookmarkProjectTest {
    private val projectRepository = mock<ProjectsRepository>()
    private val postExecutionThread = mock<PostExecutionThread>()
    private lateinit var bookmarkProject: BookmarkProject

    @Before
    fun setup() {
        bookmarkProject = BookmarkProject(projectRepository, postExecutionThread)
    }

    @Test
    fun testBuildUseCaseCompletable_completes() {
        val projectId = ProjectDataFactory.randomUuid()
        whenever(projectRepository.bookmarkProject(projectId)).thenReturn(Completable.complete())

        val testObserver =
                bookmarkProject.buildUseCaseCompletable(BookmarkProject.Params(projectId)).test()

        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun testBuildUseCaseComplete_throwsIllegalArgumentException() {
        bookmarkProject.buildUseCaseCompletable(null).test()
    }
}
