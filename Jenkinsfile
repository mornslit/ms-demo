pipeline {
  agent any
  stages {
    stage('build and unit-test') {
      steps {
        env.PATH = "${tool 'Gradle4.2.1'}/bin:${env.PATH}"
        git(url: 'https://github.com/mornslit/ms-demo.git', branch: 'master', credentialsId: 'mornslit')
        echo 'Building'
        sh 'gradle build'
        if ($?) 
          sh 'java -version'
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
