gitRepo = 'https://github.com'
gitBranch = 'master'
projMaster = 'jiangxiaogang@peilian.com'
testEmail = 'jiangxiaogang@peilian.com'

pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        git(url: gitRepo.'mornslit/ms-demo.git', branch: gitBranch, credentialsId: 'mornslit')
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
