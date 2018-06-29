package me.androidbox.domain.interactor.mockdata

import me.androidbox.domain.model.Project
import java.util.*


object ProjectDataFactory {
    private fun randomUuid() = UUID.randomUUID().toString()

    private fun randomBoolean() = Math.random() < 0.5

    private fun makeProject(): Project {
        return Project(
                randomUuid(),
                randomUuid(),
                randomUuid(),
                randomUuid(),
                randomUuid(),
                randomUuid(),
                randomUuid(),
                randomBoolean())
    }

    fun makeProjectList(count: Int): List<Project> {
        val projectList = mutableListOf<Project>()
        repeat(count) {
            projectList.add(makeProject())
        }

        return projectList
    }
}