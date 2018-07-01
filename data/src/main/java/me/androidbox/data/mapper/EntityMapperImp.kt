package me.androidbox.data.mapper

import me.androidbox.data.model.ProjectEntity
import me.androidbox.domain.model.Project
import javax.inject.Inject

class EntityMapperImp @Inject constructor(): EntityMapper<ProjectEntity, Project> {
    override fun mapFromEntity(entity: ProjectEntity): Project {
        return Project(
                entity.id,
                entity.name,
                entity.fullName,
                entity.starCount,
                entity.dataCreated,
                entity.ownerName,
                entity.ownerAvator,
                entity.isBookedMarked)
    }

    override fun mapToEntity(domain: Project): ProjectEntity {
        return ProjectEntity(                
                domain.id,
                domain.name,
                domain.fullName,
                domain.starCount,
                domain.dataCreated,
                domain.ownerName,
                domain.ownerAvator,
                domain.isBookedMarked)
    }
}