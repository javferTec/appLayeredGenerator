package local.javi.app.domain.util.build.impl;

import local.javi.app.common.annotation.domain.Utility;
import local.javi.app.domain.model.project.spring.config.SpringProjectConfig;
import local.javi.app.domain.model.project.spring.config.SpringProjectDependency;
import local.javi.app.domain.util.build.UrlBuilder;
import lombok.RequiredArgsConstructor;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.StringJoiner;

@Utility
@RequiredArgsConstructor
public class SpringUrlBuilderImpl implements UrlBuilder {

    @Override
    public String buildUrl(String baseUrl, SpringProjectConfig config, List<SpringProjectDependency> dependencies) {
        StringJoiner urlJoiner = new StringJoiner("&", baseUrl + "starter.zip?", "");

        urlJoiner.add("groupId=" + encode(config.getGroupId()))
                .add("artifactId=" + encode(config.getArtifactId()))
                .add("name=" + encode(config.getName()))
                .add("description=" + encode(config.getDescription()))
                .add("packageName=" + encode(config.getPackageName()))
                .add("bootVersion=" + encode(config.getBootVersion()))
                .add("language=" + encode(config.getLanguage()))
                .add("javaVersion=" + encode(config.getJavaVersion()))
                .add("type=" + encode(config.getType()))
                .add("packaging=" + encode(config.getPackaging()))
                .add("dependencies=" + encode(joinDependencies(dependencies)));

        return urlJoiner.toString();
    }

    private String encode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

    private String joinDependencies(List<SpringProjectDependency> dependencies) {
        return String.join(",", dependencies.stream().map(SpringProjectDependency::getValue).toArray(String[]::new));
    }

}