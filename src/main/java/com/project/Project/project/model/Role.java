package com.project.Project.project.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Define los roles de usuario en el sistema")

public enum Role {
    UNDEFINED,
    ADMIN,
    OPERATIVO,
    AUDITOR
}
