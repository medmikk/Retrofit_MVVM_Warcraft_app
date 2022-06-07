package com.medvedev.wowproject.model

import java.io.Serializable

data class GuideDTO(
    val agility: String?,
    val crit: String?,
    val haste: String?,
    val id: Int?,
    val img_link: String?,
    val intellect: String?,
    val intro: String?,
    val lvl_15: String?,
    val lvl_25: String?,
    val lvl_30: String?,
    val lvl_35: String?,
    val lvl_40: String?,
    val lvl_45: String?,
    val lvl_50: String?,
    val mastery: String?,
    val pve_stat_priority: String?,
    val spec_name: String?,
    val stamina: String?,
    val stat_weights: String?,
    val strength: String?,
    val talent_builds: String?,
    val versatility: String?
): Serializable