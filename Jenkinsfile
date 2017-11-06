node {
    def GIT_URL = 'https://github.com/mornslit/ms-demo.git'
    def CRED_ID = 'mornslit'
    def QA_EMAIL = 'jiangxiaogang@peilian.com'
    def PM_EMAIL = 'yukun@peilian.com'
    def DEV_HOST = ''
    def QA_HOST = ''

    def GRADLE_HOME = "${tool name: 'Gradle4.2.1', type: 'gradle'}/bin/gradle"

    stage ('build') {
        git url: GIT_URL, branch: BRANCH_NAME, credentialsId: CRED_ID
        echo 'Building'
        try {
            //sh "${GRADLE} build -x test"
            sh 'java -version'
            if (env.BRANCH_NAME == 'master') {
                echo "build '${env.BRANCH_NAME}' success"
            }
            def temp = sh returnStdout: true, script: 'echo AAA'
            echo "temp is '${temp}'"
        } catch (err) {
            echo "Caught: ${err}"
            echo "build '${env.BRANCH_NAME}' failed"
            //emailext body: 'fail', recipientProviders: [[$class: 'DevelopersRecipientProvider']], subject: 'fail'
            //emailext body: 'fail', to: QA_EMAIL, subject: 'fail'
            currentBuild.result = 'FAILURE'
            throw err
        } finally {
            echo 'finally'
        }
    }

    stage ('test') {
        echo 'testing'
    }

    stage ('deploy') {
        echo 'deploying'
        def ciMsg = sh returnStdout: true, script: 'git log -1 --pretty=format:"%s"'

        if (env.BRANCH_NAME == 'release') {
            //deploy to release env machine
        } else if (env.BRANCH_NAME == 'master') {
            //deploy to prod env machine
            if (ciMsg.indexOf('[PROD]') == 0) {
                echo 'ci msg hash "[PROD]"'
            }
            echo 'deploy last release to prod'
        } else {
            if (ciMsg.indexOf('[DEV]') == 0) {
                //deploy to dev env machine
            }
            if (ciMsg.indexOf('[QA]') == 0) {
                //deploy to qa env machine
            }
        }
    }

    stage ('notify') {

    }
}
