pipeline {
  agent any
  stages {
    stage('build and unit-test') {
      steps {
        env.PATH = "${tool 'Gradle4.2.1'}/bin:${env.PATH}"
        git(url: 'https://github.com/mornslit/ms-demo.git', branch: 'master', credentialsId: 'mornslit')
        echo 'Building'
        sh returnStatus: true, script: 'gradle build'
        if (returnStatus == '0') 
          sh 'java -version'
        else
          echo 'fail'
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
