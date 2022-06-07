package com.medvedev.wowproject.model

data class RankingDTO(
    val actual_patch: String,
    val id: Int?,
    val ranking_type: String,
    val specslist: List<SpecDTO>
)