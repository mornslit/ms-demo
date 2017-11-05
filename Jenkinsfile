pipeline {
  agent any
  
  tools {
    gradle 'Gradle4.2.1'
  }

  environment {
    GIT_URL = 'https://github.com/mornslit/ms-demo.git'
    CRED_ID = 'mornslit'
    QA_EMAIL = 'jiangxiaogang@peilian.com'
    PM_EMAIL = 'yukun@peilian.com'
    DEV_HOST = ''
    QA_HOST = ''
  }

  stages {
    stage('build and unit-test') {
      steps {
        //git(url: env.GIT_URL, branch: env.BRANCH_NAME, credentialsId: env.CRED_ID)
        echo 'Building'
        //sh 'gradle build'
        echo 'success'
        sh 'java --version'
      }
      post {
        success {
          when {
            branch 'master'
          }
          steps {
            echo 'build master success'
          }
        }
        failure {
          echo "build '${env.BRANCH_NAME}' failed"
          script {
            emailext body: 'fail', recipientProviders: [[$class: 'DevelopersRecipientProvider']], subject: 'fail'
          }
        }
        always {
          echo 'done'
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
