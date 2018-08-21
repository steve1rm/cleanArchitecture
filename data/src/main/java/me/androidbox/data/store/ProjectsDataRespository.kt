package me.androidbox.data.store

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import me.androidbox.data.mapper.EntityMapper
import me.androidbox.data.mapper.EntityMapperImp
import me.androidbox.data.repository.ProjectsCache
import me.androidbox.domain.model.Project
import me.androidbox.domain.repository.ProjectsRepository
import javax.inject.Inject

class ProjectsDataRespository @Inject constructor(private val mapper: EntityMapperImp,
                                                  private val projectsCache: ProjectsCache,
                                                  private val projectsDataStoreFactory: ProjectsDataStoreFactory)
    : ProjectsRepository {

    override fun getProjects(): Observable<List<Project>> {
        return Observable.zip(projectsCache.areProjectsCached().toObservable(),
                projectsCache.isProjectsCacheExpired().toObservable(),
                BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
                    Pair(areCached, isExpired)
                })
                .flatMap {
                    projectsDataStoreFactory.getDataStore(it.first, it.second).getProjects()
                }
                .flatMap { project ->
                    projectsDataStoreFactory.getCachedDataStore()
                            .saveProjects(project)
                            .andThen(Observable.just(project))
                }
                .map { listProjectEntity ->
                    listProjectEntity.map {
                        mapper.mapFromEntity(it)
                    }
                }
    }

    override fun bookmarkProject(projectId: String): Completable {
        return projectsDataStoreFactory.getCachedDataStore().setProjectAsBookmarked(projectId)
    }

    override fun unBookmarkProject(projectId: String): Completable {
        return projectsDataStoreFactory.getCachedDataStore().setProjectAsNotBookmarked(projectId)
    }

    override fun getBookedMarkedProjects(): Observable<List<Project>> {
        return projectsDataStoreFactory.getCachedDataStore().getBookmarkedProjects()
                .map { listProjectEntity ->
                    listProjectEntity.map {
                        mapper.mapFromEntity(it) }
                }
    }
}
