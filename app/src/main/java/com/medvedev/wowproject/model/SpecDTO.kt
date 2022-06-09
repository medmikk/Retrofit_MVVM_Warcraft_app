package com.medvedev.wowproject.model

import java.io.Serializable

data class SpecDTO(
    val dps: String?,
    val id: Int?,
    val mode: String,
    val name: String,
    val proc: String?,
    val ranking_id: Int?,
    val spelllist: List<SpellDTO>?,
    val target: Int
) : Serializable

