package org.alebiadz.budget.tracker.service.utils

import org.alebiadz.budget.tracker.service.meta.UserMeta
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.*

interface UserUtils {

    companion object {

        fun getAuthorities(admin: Boolean): Set<GrantedAuthority> {

            return if (admin) Collections.singleton(SimpleGrantedAuthority(UserMeta.ROLE_ADMIN)) else Collections.emptySet()
        }
    }
}