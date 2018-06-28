package me.androidbox.domain.interactor.bookmark

import io.reactivex.Completable
import me.androidbox.domain.executor.PostExecutionThread
import me.androidbox.domain.interactor.CompletableUseCase
import me.androidbox.domain.repository.ProjectsRepository
import javax.inject.Inject

class BookmarkProject @Inject constructor(
        private val projectsRepository: ProjectsRepository,
        postExecutionThread: PostExecutionThread)
    : CompletableUseCase<BookmarkProject.Params>(postExecutionThread) {

    override fun buildUseCaseCompletable(params: Params?): Completable {
        if(params == null) {
            throw IllegalArgumentException("Params == null")
        }

        return projectsRepository.bookmarkProject(params.projectId)
    }

    data class Params constructor(val projectId: String) {
        companion object Project {
            fun forProject(projectId: String) = Params(projectId)
        }
    }
}
