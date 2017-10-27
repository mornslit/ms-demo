pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        git(url: 'https://github.com/mornslit/ms-demo.git', branch: 'master', credentialsId: 'mornslit')
        echo 'Building'
        sh 'gradle build'
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
    PATH = '/var/gradle_home/bin:$PATH'
  }
}