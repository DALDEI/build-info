package org.jfrog.build.utils

import static java.lang.System.getenv

/**
 * @author Lior Hasson
 */
class TestsConfig {
    private static TestsConfig instance = new TestsConfig()
    public def config

    static TestsConfig getInstance() {
        return instance
    }

    private TestsConfig() {
        config = new ConfigSlurper().parse(this.class.getResource('/org/jfrog/build/config.groovy')).conf

        def artifactoryUrl = getenv('ARTIFACTORY_URL')
        if (!artifactoryUrl) {
            artifactoryUrl = System.getProperty('artifactoryUrl')
        }
        if (artifactoryUrl) {
            config.artifactory.url = artifactoryUrl
        }
        def artifactoryUser = getenv('ARTIFACTORY_USER')
        if (!artifactoryUser) {
            artifactoryUser = System.getProperty('artifactoryUser')
        }
        if (artifactoryUser) {
            config.artifactory.username = artifactoryUser
        }
        def artifactoryPassword = getenv('ARTIFACTORY_PASSWORD')
        if (!artifactoryPassword) {
            artifactoryPassword = System.getProperty('artifactoryPassword')
        }
        if (artifactoryPassword) {
            config.artifactory.password = artifactoryPassword
        }
        def testConfigurationsPath = getenv('TEST_CONFIGURATION_PATH')
        if (!testConfigurationsPath) {
            testConfigurationsPath = System.getProperty('testConfigurationsPath')
        }
        if (testConfigurationsPath) {
            config.testConfigurationsPath = testConfigurationsPath
        }
        if (!config.testConfigurationsPath) {
            config.testConfigurationsPath = getClass().getResource("/org/jfrog/build/testConfigurations").path;
        }
    }
}