package me.androidbox.data.store

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import me.androidbox.data.mapper.EntityMapper
import me.androidbox.data.repository.ProjectsCache
import me.androidbox.domain.model.Project
import me.androidbox.domain.repository.ProjectsRepository
import javax.inject.Inject

class ProjectsDataRespository @Inject constructor(private val mapper: EntityMapper,
                                                  private val projectsCache: ProjectsCache,
                                                  private val projectsDataStoreFactory: ProjectsDataStoreFactory)
    : ProjectsRepository {

    override fun getProjects(): Observable<List<Project>> {
        val observable: Observable<List<Project>> =
                Observable.zip(projectsCache.areProjectsCached().toObservable(),
                        projectsCache.isProjectsCacheExpired().toObservable(),
                        BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> {
                            areCached, isExpired -> Pair(areCached, isExpired)
                        })
                        .flatMap {project ->
                            projectsDataStoreFactory.getCachedDataStore()
                                    .saveProjects(project)
                                    .andThen(Observable.just(project))
                        }

        return observable
    }

    override fun bookmarkProject(projectId: String): Completable {

    }

    override fun unBookmarkProject(projectId: String): Completable {

    }

    override fun getBookedMarkedProjects(): Observable<List<Project>> {

    }
}