project {
  modelVersion '4.0.0'
  parent {
    groupId 'org.springframework.boot'
    artifactId 'spring-boot-starter-parent'
    version '1.3.5.RELEASE'
  }
  groupId 'bcp.spring'
  artifactId 'spring-boot-sample-jpa'
  version '1.0-SNAPSHOT'
  name 'Spring sample JPA'
  url 'http://maven.apache.org'
  properties {
    'maven.compiler.source' '1.8'
    'project.build.sourceEncoding' 'UTF-8'
    'maven.compiler.target' '1.8'
  }
  dependencies {
    dependency {
      groupId 'org.springframework.boot'
      artifactId 'spring-boot-starter-web'
    }
    dependency {
      groupId 'org.springframework.boot'
      artifactId 'spring-boot-starter-actuator'
    }
    dependency {
      groupId 'org.springframework.boot'
      artifactId 'spring-boot-starter-data-jpa'
    }
    dependency {
      groupId 'org.hibernate'
      artifactId 'hibernate-jpamodelgen'
    }
    dependency {
      groupId 'com.h2database'
      artifactId 'h2'
    }
    dependency {
      groupId 'org.projectlombok'
      artifactId 'lombok'
      version '1.16.4'
      scope 'provided'
    }
    dependency {
      groupId 'org.codehaus.groovy'
      artifactId 'groovy-all'
    }
    dependency {
      groupId 'org.spockframework'
      artifactId 'spock-core'
      exclusions {
        exclusion {
          artifactId 'groovy-all'
          groupId 'org.codehaus.groovy'
        }
      }
    }
    dependency {
      groupId 'org.codehaus.groovy.modules.http-builder'
      artifactId 'http-builder'
      version '0.7.1'
    }
    dependency {
      groupId 'junit'
      artifactId 'junit'
      scope 'test'
    }
  }
  build {
    plugins {
      plugin {
        groupId 'org.springframework.boot'
        artifactId 'spring-boot-maven-plugin'
      }
    }
  }
}
