pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        git(url: 'https://github.com/mornslit/ms-demo.git', branch: 'master', credentialsId: 'mornslit')
        echo 'Building'
        //sh '/var/gradle_home/bin/gradle build'
        sh 'java --version'
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
  environment {
    PATHEXTRA = '/var/gradle_home/bin'
  }
}
