CREATE DATABASE IF NOT EXISTS project_generator;

CREATE TABLE RelationType
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE COMMENT 'Tipo de relación (OneToOne, OneToMany, etc.)'
);

CREATE TABLE SpringProjectConfig
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    groupId     VARCHAR(100)                             NOT NULL COMMENT 'Dominio del proyecto',
    artifactId  VARCHAR(100)                             NOT NULL COMMENT 'ID único del artefacto',
    name        VARCHAR(100)                             NOT NULL COMMENT 'Nombre del proyecto',
    description TEXT COMMENT 'Descripción del proyecto',
    packageName VARCHAR(100)                             NOT NULL COMMENT 'Paquete base del proyecto',
    bootVersion ENUM ('3.4.0', '3.3.6')                  NOT NULL DEFAULT '3.4.0' COMMENT 'Versión de Spring Boot',
    language    ENUM ('java', 'kotlin', 'groovy')        NOT NULL DEFAULT 'java' COMMENT 'Lenguaje del proyecto',
    type        ENUM ('maven-project', 'gradle-project') NOT NULL DEFAULT 'maven-project' COMMENT 'Tipo de proyecto'
);

CREATE TABLE SpringProjectDependency
(
    id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    value VARCHAR(255) NOT NULL COMMENT 'Valor de la dependencia'
);

CREATE TABLE User
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    username      VARCHAR(50)  NOT NULL UNIQUE COMMENT 'Nombre único del usuario',
    email         VARCHAR(100) NOT NULL UNIQUE COMMENT 'Correo electrónico del usuario',
    password_hash VARCHAR(255) NOT NULL COMMENT 'Contraseña encriptada',
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de creación',
    updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de última actualización'
);

CREATE TABLE ProjectDetails
(
    id                    BIGINT AUTO_INCREMENT PRIMARY KEY,
    springProjectConfigId BIGINT,
    userId                BIGINT,
    ddlSql                BOOLEAN NOT NULL COMMENT '¿Generar SQL para DDL?',
    jpa                   BOOLEAN NOT NULL COMMENT '¿Incluir JPA?',
    roles                 BOOLEAN NOT NULL COMMENT '¿Incluir gestión de roles?',
    thymeleaf             BOOLEAN NOT NULL COMMENT '¿Incluir Thymeleaf?',
    -- Clave foránea para configuración de proyectos de Spring
    FOREIGN KEY (springProjectConfigId) REFERENCES SpringProjectConfig (id) ON DELETE CASCADE,
    -- Clave foránea para usuarios
    FOREIGN KEY (userId) REFERENCES User (id) ON DELETE CASCADE
);

CREATE TABLE AttributeDataType
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    javaType VARCHAR(50) NOT NULL COMMENT 'Tipo de dato en Java',
    dbType   VARCHAR(50) NOT NULL COMMENT 'Tipo de dato en la base de datos'
);

CREATE TABLE Attribute
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    domainName VARCHAR(100) NOT NULL COMMENT 'Nombre del atributo en el dominio',
    dbName     VARCHAR(100) NOT NULL COMMENT 'Nombre del atributo en la base de datos',
    typeId     BIGINT,
    primaryKey BOOLEAN      NOT NULL COMMENT '¿Es clave primaria?',
    -- Clave foránea para tipos de datos
    FOREIGN KEY (typeId) REFERENCES AttributeDataType (id) ON DELETE SET NULL
);

CREATE TABLE Entity
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    domainName VARCHAR(100) NOT NULL UNIQUE COMMENT 'Nombre del dominio de la entidad',
    dbName     VARCHAR(100) NOT NULL UNIQUE COMMENT 'Nombre de la tabla asociada',
    mainEntity BOOLEAN      NOT NULL COMMENT '¿Es la entidad principal?'
);

CREATE TABLE Method
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL COMMENT 'Nombre del método'
);

CREATE TABLE Relation
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    relatedEntityId BIGINT,
    relationTypeId  BIGINT,
    -- Clave foránea para entidades relacionadas
    FOREIGN KEY (relatedEntityId) REFERENCES Entity (id) ON DELETE CASCADE,
    -- Clave foránea para tipos de relaciones
    FOREIGN KEY (relationTypeId) REFERENCES RelationType (id) ON DELETE CASCADE
);

CREATE TABLE Entity_Attribute
(
    entityId    BIGINT,
    attributeId BIGINT,
    PRIMARY KEY (entityId, attributeId),
    -- Clave foránea para entidades
    FOREIGN KEY (entityId) REFERENCES Entity (id) ON DELETE CASCADE,
    -- Clave foránea para atributos
    FOREIGN KEY (attributeId) REFERENCES Attribute (id) ON DELETE CASCADE
);

CREATE TABLE Entity_Method
(
    entityId BIGINT,
    methodId BIGINT,
    PRIMARY KEY (entityId, methodId),
    -- Clave foránea para entidades
    FOREIGN KEY (entityId) REFERENCES Entity (id) ON DELETE CASCADE,
    -- Clave foránea para métodos
    FOREIGN KEY (methodId) REFERENCES Method (id) ON DELETE CASCADE
);

CREATE TABLE Entity_Relation
(
    entityId   BIGINT,
    relationId BIGINT,
    PRIMARY KEY (entityId, relationId),
    -- Clave foránea para entidades
    FOREIGN KEY (entityId) REFERENCES Entity (id) ON DELETE CASCADE,
    -- Clave foránea para relaciones
    FOREIGN KEY (relationId) REFERENCES Relation (id) ON DELETE CASCADE
);

CREATE TABLE SpringProjectConfig_Dependency
(
    springProjectConfigId     BIGINT,
    springProjectDependencyId BIGINT,
    PRIMARY KEY (springProjectConfigId, springProjectDependencyId),
    -- Clave foránea para configuración de proyectos de Spring
    FOREIGN KEY (springProjectConfigId) REFERENCES SpringProjectConfig (id) ON DELETE CASCADE,
    -- Clave foránea para dependencias de proyectos de Spring
    FOREIGN KEY (springProjectDependencyId) REFERENCES SpringProjectDependency (id) ON DELETE CASCADE
);
