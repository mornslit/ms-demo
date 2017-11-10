
node {
    def gitUrl = 'https://github.com/mornslit/ms-demo.git'
    def credId = 'mornslit'
    
    def devHosts = '192.168.40.204, 192.168.40.207'
    def qaHosts = '192.168.40.131, 192.168.40.141'
    def preHosts = '192.168.40.132, 192.168.40.142'
    def prodHosts = ''
    
    def prop = readFile('build.gradle')
    def version = getProp(prop, 'version')
    def baseName = getProp(prop, 'baseName')

    def builder = "${tool name: 'Gradle4.2.1', type: 'gradle'}/bin/gradle"
    
    def qaEmail = 'jiangxiaogang@peilian.com'
    def pmEmail = 'jiangxiaogang@peilian.com'
    
    def ciMsg = ''
    def involveQA =''
    
    def lastStage = ''
    def success = true
    
    parameters{string(name: 'GIT_TAG', defaultValue: 'a', description: 'aaa')}
    
    echo "param from http: ${params.GIT_TAG}"
    
    stage ('git') {
        git url: gitUrl, branch: BRANCH_NAME, credentialsId: credId
        
        ciMsg = sh returnStdout: true, script: 'git log -1 --pretty=format:"%s"'
        involveQA = (ciMsg =~ '[QA]' || BRANCH_NAME == 'release')
        
        lastStage = 'git'
    }

    stage ('build') {
        def buildSuccess = true

        echo 'Building'
        try {
            sh "${builder} build -x test"
            //sh 'java -version'
            echo "build '${BRANCH_NAME}' success"
            buildSuccess = true
        } catch (err) {
            echo "Caught: ${err}"
            echo "build '${BRANCH_NAME}' failed"
            buildSuccess = false
        } finally {
            echo 'Building finally'
            
            if (buildSuccess) {
                //if (involveQA) {
                    archiveArtifacts artifacts: 'build/libs/', excludes: 'build/libs/*.original'
                //}
            }
            
            success = buildSuccess
            lastStage = 'Build'
        }
    }
    
    stage ('unit-test') {
        if (success) {
            def unitTestPass = true
    
            echo 'Unit-testing'
            try {
                //sh "${builder} build test"
                //sh "java --version"
                echo "unit test '${BRANCH_NAME}' success"
                unitTestPass = true
            } catch (err) {
                echo "Caught: ${err}"
                echo "unit test '${BRANCH_NAME}' failed"
                unitTestPass = false
            } finally {
                echo 'Unit-test finally'
                
                if (involveQA) {
                    archiveArtifacts artifacts: 'build/reports/'
                }
                
                if (unitTestPass) {
                    if (BRANCH_NAME == 'release' || BRANCH_NAME == 'master') {
                        //sh """
                        //git tag -d ${version}
                        //git tag -a ${version} -m \"${baseName} version ${version}\"
                        //git push origin --tag
                        //"""
                    }
                }
                
                success = unitTestPass
                lastStage = 'Unit-test'
            }
        } else {
            echo "${lastStage} failed, skip unit-test stage"
        }
    }
    
    stage ('test') {
        if (success) {
            echo 'testing'
        } else {
            echo "${lastStage} failed, skip test stage"
        }
    }

    stage ('deploy') {
        if (success) {
            echo 'deploying'
            
            if (BRANCH_NAME == 'release') {
                //deploy to pre env machine
            } else if (BRANCH_NAME == 'master') {
                //deploy to prod env machine
                echo 'deploy last release to prod'
                
                //deploy to dev env machine
                echo 'deploy last release to dev'
            } else {
                if (ciMsg =~ '[QA]') {
                    //deploy to qa env machine
                }
            }
        } else {
            echo "${lastStage} failed, skip deploy stage"
        }
    }

    stage ('notify') {
        def subject = "[Jenkins] ${JOB_NAME} - Build # ${BUILD_NUMBER} - " << (success ? "SUCCESS" : "FAILURE")
        if (success) {
            if (BRANCH_NAME == 'release') {
                emailext to: "${qaEmail}, ${pmEmail}",
                        recipientProviders: [[$class: 'RequesterRecipientProvider']], 
                        subject: "${subject}", 
                        body: "Build done, and can be deployed to ${preHosts}"
            }
            //else if (BRANCH_NAME == 'master') {
            //    emailext to: "${pmEmail}",
            //            recipientProviders: [[$class: 'RequesterRecipientProvider']], 
            //            subject: "${subject}", 
            //            body: "Build done, and can be deployed to ${prodHosts}"
            //} 
            else {
                if (ciMsg =~ '[QA]') {
                    emailext to: "${qaEmail}",
                            recipientProviders: [[$class: 'RequesterRecipientProvider']], 
                            subject: "[QA] ${subject}", 
                            body: "Build done, and can be deployed to ${qaHosts}"
                }
            }
        } else {
            emailext to: "${pmEmail}", 
                    recipientProviders: [[$class: 'CulpritsRecipientProvider']], 
                    subject: "${subject}", 
                    body: "Check console output at ${BUILD_URL} to view the results"
                    
            error "${lastStage} failure!"
        }
    }
}

@NonCPS
def getProp(text, propName) {
    def matcher = text =~ "${propName} = '(.+)'\n"
    matcher ? matcher[0][1] : null
}

