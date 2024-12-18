USE project_generator;

-- Insertar tipos de relación
INSERT INTO relationtype (name)
VALUES ('OneToOne'),
       ('OneToMany'),
       ('ManyToOne'),
       ('ManyToMany');

-- Insertar configuraciones de proyectos Spring
INSERT INTO SpringProjectConfig (groupId, artifactId, name, description, packageName, bootVersion, language, type)
VALUES ('com.example', 'my-app', 'My Application', 'Descripción del proyecto My Application', 'com.example.myapp',
        '3.4.0', 'java', 'maven-project'),
       ('com.example', 'demo-app', 'Demo Application', 'Descripción del proyecto Demo Application', 'com.example.demo',
        '3.4.0', 'java', 'maven-project');

-- Insertar dependencias de Spring
-- Dependencias de Spring Boot
INSERT INTO SpringProjectDependency (value)
VALUES ('spring-boot-starter-web'),
       ('spring-boot-starter-data-jpa'),
       ('spring-boot-starter-security'),
       ('spring-boot-starter-thymeleaf'),
       ('spring-boot-devtools'),
       ('spring-boot-starter-logging'),
       ('spring-boot-starter-actuator'),
       ('spring-boot-starter-cache');

-- Herramientas para desarrolladores
INSERT INTO SpringProjectDependency (value)
VALUES ('graalvm-native-support'),
       ('graphql-dgs-code-generation'),
       ('lombok'),
       ('spring-configuration-processor'),
       ('docker-compose-support'),
       ('spring-modulith');

-- Web
INSERT INTO SpringProjectDependency (value)
VALUES ('spring-web'),
       ('spring-reactive-web'),
       ('spring-for-graphql'),
       ('spring-data-rest'),
       ('spring-session'),
       ('spring-hateoas'),
       ('spring-web-services'),
       ('jersey'),
       ('vaadin'),
       ('netflix-dgs'),
       ('htmx');

-- Motores de plantillas
INSERT INTO SpringProjectDependency (value)
VALUES ('thymeleaf'),
       ('freemarker'),
       ('mustache'),
       ('groovy-templates'),
       ('jte');

-- Seguridad
INSERT INTO SpringProjectDependency (value)
VALUES ('spring-security'),
       ('oauth2-client'),
       ('oauth2-authorization-server'),
       ('oauth2-resource-server'),
       ('spring-ldap'),
       ('okta');

-- SQL
INSERT INTO SpringProjectDependency (value)
VALUES ('jdbc-api'),
       ('spring-data-jpa'),
       ('spring-data-jdbc'),
       ('spring-data-r2dbc'),
       ('mybatis-framework'),
       ('liquibase-migration'),
       ('flyway-migration'),
       ('jooq-access-layer'),
       ('ibm-db2-driver'),
       ('apache-derby-database'),
       ('h2-database'),
       ('hypersql-database'),
       ('mariadb-driver'),
       ('ms-sql-server-driver'),
       ('mysql-driver'),
       ('oracle-driver'),
       ('postgresql-driver');

-- NoSQL
INSERT INTO SpringProjectDependency (value)
VALUES ('spring-data-redis'),
       ('spring-data-reactive-redis'),
       ('spring-data-mongodb'),
       ('spring-data-reactive-mongodb'),
       ('spring-data-elasticsearch'),
       ('spring-data-apache-cassandra'),
       ('spring-data-reactive-apache-cassandra'),
       ('spring-data-couchbase'),
       ('spring-data-reactive-couchbase'),
       ('spring-data-neo4j');

-- Mensajería
INSERT INTO SpringProjectDependency (value)
VALUES ('spring-integration'),
       ('spring-for-rabbitmq'),
       ('spring-for-apache-kafka'),
       ('spring-for-apache-activemq-5'),
       ('spring-for-apache-activemq-artemis'),
       ('spring-for-apache-pulsar'),
       ('spring-for-apache-pulsar-reactive'),
       ('websocket'),
       ('rsocket'),
       ('apache-camel'),
       ('solace-pubsub');

-- Entrada/Salida
INSERT INTO SpringProjectDependency (value)
VALUES ('spring-batch'),
       ('bean-validation-hibernate-validator'),
       ('java-mail-sender'),
       ('quartz-scheduler'),
       ('spring-cache-abstraction'),
       ('spring-shell'),
       ('spring-grpc');

-- Operaciones
INSERT INTO SpringProjectDependency (value)
VALUES ('spring-boot-actuator'),
       ('cyclonedx-sbom-support'),
       ('spring-boot-admin-client'),
       ('spring-boot-admin-server'),
       ('sentry');

-- Observabilidad
INSERT INTO SpringProjectDependency (value)
VALUES ('datadog'),
       ('dynatrace'),
       ('influx'),
       ('graphite'),
       ('new-relic'),
       ('otlp-for-metrics'),
       ('prometheus'),
       ('wavefront'),
       ('zipkin');

-- Pruebas
INSERT INTO SpringProjectDependency (value)
VALUES ('spring-rest-docs'),
       ('testcontainers'),
       ('contract-verifier'),
       ('contract-stub-runner'),
       ('embedded-ldap-server');

-- Spring Cloud
INSERT INTO SpringProjectDependency (value)
VALUES ('cloud-bootstrap'),
       ('spring-cloud-function'),
       ('spring-cloud-task'),
       ('spring-cloud-config-client'),
       ('spring-cloud-config-server'),
       ('vault-configuration'),
       ('apache-zookeeper-configuration'),
       ('consul-configuration'),
       ('eureka-discovery-client'),
       ('eureka-server'),
       ('apache-zookeeper-discovery'),
       ('consul-discovery'),
       ('spring-cloud-routing-gateway'),
       ('spring-cloud-routing-reactive-gateway'),
       ('openfeign'),
       ('spring-cloud-loadbalancer'),
       ('spring-cloud-circuit-breaker'),
       ('spring-cloud-messaging-cloud-bus'),
       ('spring-cloud-messaging-cloud-stream');

-- VMware Tanzu
INSERT INTO SpringProjectDependency (value)
VALUES ('config-client-tas'),
       ('service-registry-tas');

-- Microsoft Azure
INSERT INTO SpringProjectDependency (value)
VALUES ('azure-support'),
       ('azure-active-directory'),
       ('azure-cosmos-db'),
       ('azure-key-vault'),
       ('azure-storage');

-- Google Cloud
INSERT INTO SpringProjectDependency (value)
VALUES ('google-cloud-support'),
       ('google-cloud-messaging'),
       ('google-cloud-storage');

-- AI
INSERT INTO SpringProjectDependency (value)
VALUES ('anthropic-claude'),
       ('azure-openai'),
       ('azure-ai-search'),
       ('amazon-bedrock'),
       ('amazon-bedrock-converse'),
       ('apache-cassandra-vector-database'),
       ('chroma-vector-database'),
       ('elasticsearch-vector-database'),
       ('milvus-vector-database'),
       ('mistral-ai'),
       ('mongodb-atlas-vector-database'),
       ('neo4j-vector-database'),
       ('ollama'),
       ('openai'),
       ('oracle-vector-database'),
       ('pgvector-vector-database'),
       ('pinecone-vector-database'),
       ('postgresml'),
       ('redis-search-and-query-vector-database'),
       ('stability-ai'),
       ('transformers-onnx-embeddings'),
       ('vertex-ai-gemini'),
       ('vertex-ai-embeddings'),
       ('qdrant-vector-database'),
       ('typesense-vector-database'),
       ('weaviate-vector-database'),
       ('markdown-document-reader'),
       ('tika-document-reader'),
       ('pdf-document-reader'),
       ('timefold-solver');

-- Insertar usuarios
INSERT INTO User (username, email, password_hash)
VALUES ('admin', 'admin@example.com', 'hashed_password_123'),
       ('user1', 'user1@example.com', 'hashed_password_456'),
       ('user2', 'user2@example.com', 'hashed_password_789');

-- Insertar detalles de proyectos
INSERT INTO ProjectDetails (springProjectConfigId, userId, ddlSql, jpa, roles, thymeleaf)
VALUES (1, 1, TRUE, TRUE, TRUE, TRUE),
       (2, 2, FALSE, TRUE, FALSE, FALSE);

-- Insertar tipos de datos para atributos
INSERT INTO AttributeDataType (javaType, dbType)
VALUES ('String', 'VARCHAR'),
       ('Integer', 'INT'),
       ('Boolean', 'TINYINT'),
       ('Date', 'DATETIME'),
       ('Long', 'BIGINT'),
       ('Double', 'DOUBLE'),
       ('Float', 'FLOAT'),
       ('Character', 'CHAR'),
       ('Decimal', 'DECIMAL'),
       ('Timestamp', 'TIMESTAMP'),
       ('Text', 'TEXT'),
       ('Short', 'SMALLINT'),
       ('Time', 'TIME'),
       ('BigDecimal', 'DECIMAL'),
       ('Byte', 'TINYINT'),
       ('ByteArray', 'BLOB'),
       ('Clob', 'TEXT');

-- Insertar atributos
INSERT INTO Attribute (domainName, dbName, typeId, primaryKey)
VALUES ('name', 'name', 1, FALSE),
       ('age', 'age', 2, FALSE),
       ('is_active', 'is_active', 3, FALSE),
       ('created_at', 'created_at', 4, FALSE);

-- Insertar entidades
INSERT INTO Entity (domainName, dbName, mainEntity)
VALUES ('User', 'users', TRUE),
       ('Project', 'projects', TRUE);

-- Insertar métodos
INSERT INTO Method (name)
VALUES ('findById'),
       ('findAll'),
       ('save'),
       ('create'),
       ('update'),
       ('delete');

-- Insertar relaciones
INSERT INTO Relation (relatedEntityId, relationTypeId)
VALUES (1, 2), -- Relación ManyToOne con User
       (2, 3);
-- Relación OneToMany con Project

-- Insertar relación entre entidades y atributos
INSERT INTO Entity_Attribute (entityId, attributeId)
VALUES (1, 1), -- User.name
       (1, 2), -- User.age
       (1, 3), -- User.is_active
       (2, 4);
-- Project.created_at

-- Insertar relación entre entidades y métodos
INSERT INTO Entity_Method (entityId, methodId)
VALUES (1, 1), -- User.findById
       (1, 2), -- User.findAll
       (2, 3), -- Project.save
       (2, 4);
-- Project.delete

-- Insertar relación entre entidades y relaciones
INSERT INTO Entity_Relation (entityId, relationId)
VALUES (1, 1), -- User está relacionado con una entidad
       (2, 2);
-- Project está relacionado con una entidad

-- Insertar relación entre configuraciones de proyectos y dependencias
INSERT INTO springProjectConfig_dependency (springProjectConfigId, springProjectDependencyId)
VALUES (1, 1), -- My Application usa spring-boot-starter-web
       (1, 2), -- My Application usa spring-boot-starter-data-jpa
       (2, 3), -- Demo Application usa spring-boot-starter-security
       (2, 4); -- Demo Application usa spring-boot-starter-thymeleaf

