package me.androidbox.data.store

import io.reactivex.Completable
import io.reactivex.Observable
import me.androidbox.data.model.ProjectEntity
import me.androidbox.data.repository.ProjectsDataStore
import me.androidbox.data.repository.ProjectsRemote
import javax.inject.Inject

class ProjectsRemoteDataStoreImp @Inject constructor(private val projectsRemote: ProjectsRemote)
    : ProjectsDataStore {

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectsRemote.getProjects()
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        throw UnsupportedOperationException("save projects is unsupported for remote access")
    }

    override fun clearProjects(): Completable {
        throw UnsupportedOperationException("clear projects is unsupported for remote access")
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        throw UnsupportedOperationException("get book marked projects is unsupported for remote access")
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("set projects as book marked is unsupported for remote access")
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("set projects as not book marked is unsupported for remote access")
    }
}
