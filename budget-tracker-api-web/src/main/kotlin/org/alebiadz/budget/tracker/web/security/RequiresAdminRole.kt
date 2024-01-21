package org.alebiadz.budget.tracker.web.security

import org.alebiadz.budget.tracker.service.meta.UserMeta
import org.springframework.security.access.prepost.PreAuthorize

@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@PreAuthorize("hasRole('${UserMeta.ADMIN_ROLE}')")
annotation class RequiresAdminRole
