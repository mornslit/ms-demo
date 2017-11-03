pipeline {
  agent any
  stages {
    stage('build and unit-test') {
      steps {
        withEnv (["PATH+GRADLE = ${tool 'Gradle4.2.1'}/bin"]) {
        git(url: 'https://github.com/mornslit/ms-demo.git', branch: 'master', credentialsId: 'mornslit')
        echo 'Building'
        try {
          sh 'gradle build'
          echo 'success'
          sh 'java -version'
        } catch (err) {
          echo 'fail'
        }
        }
      }
    }
    stage('test') {
      steps {
        echo 'Testing'
      }
    }
    stage('deploy') {
      steps {
        echo 'deploying'
      }
    }
  }
}
