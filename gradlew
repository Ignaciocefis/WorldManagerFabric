#!/usr/bin/env sh
##############################################################################
##
##  Gradle start up script for UN*X
##
##############################################################################
# Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
DEFAULT_JVM_OPTS=""

APP_NAME="Gradle"
APP_BASE_NAME=`basename "$0"`
APP_HOME=`dirname "$0"`/gradle/wrapper

# Add default JVM options
JVM_OPTS="${JVM_OPTS} ${DEFAULT_JVM_OPTS}"

exec "$JAVA_HOME/bin/java" ${JVM_OPTS} -cp "$APP_HOME/gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain "$@"