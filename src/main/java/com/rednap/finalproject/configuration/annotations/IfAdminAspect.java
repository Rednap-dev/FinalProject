package com.rednap.finalproject.configuration.annotations;

import com.rednap.finalproject.exception.NoAdminPermissionException;
import com.rednap.finalproject.model.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
public class IfAdminAspect {

    @SneakyThrows
    @Around("@annotation(IfAdmin)")
    public Object processIfAdmin(ProceedingJoinPoint proceedingJoinPoint) {
        boolean isAdmin = false;
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (Objects.nonNull(authentication)) {
            isAdmin = authentication.getAuthorities().stream()
                    .anyMatch(authority -> authority.getAuthority().equals(UserEntity.UserRole.ROLE_ADMIN.name()));
        }

        if(isAdmin) {
            final Object result = proceedingJoinPoint.proceed();
            return result;
        }

        throw new NoAdminPermissionException();
    }
}
