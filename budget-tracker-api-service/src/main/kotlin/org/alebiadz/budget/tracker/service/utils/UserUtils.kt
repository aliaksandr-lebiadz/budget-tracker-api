package org.alebiadz.budget.tracker.service.utils

import org.alebiadz.budget.tracker.service.meta.UserMeta
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*

interface UserUtils {

    companion object {

        fun getAuthorities(admin: Boolean): Set<GrantedAuthority> {

            return if (admin) Collections.singleton(SimpleGrantedAuthority(UserMeta.ADMIN_ROLE)) else Collections.emptySet()
        }

        fun getCurrentUserId(): Long {

            return SecurityContextHolder.getContext().authentication.principal as Long
        }
    }
}