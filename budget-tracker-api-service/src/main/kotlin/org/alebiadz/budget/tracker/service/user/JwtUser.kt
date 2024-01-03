package org.alebiadz.budget.tracker.service.user

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class JwtUser(val id: Long, username: String?, password: String?, authorities: Set<GrantedAuthority>) :
    User(username, password, authorities)
