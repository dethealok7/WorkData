pipeline {
    agent any

    stages {
        stage('Download') {
            steps {
                //sh "mkdir -p output"
                //writeFile file: "output/testFile.txt", text: "This is example file for Jenkins"
                //Check for working condition
                sh "mvn --version"
            }
        }
        stage('Build') {
            steps {
                //sh 'make' 
                //archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
                sh "mvn --version"
            }
        }
        stage('Test') {
            steps {
                /* `make check` returns non-zero on test failures,
                * using `true` to allow the Pipeline to continue nonetheless
                */
                //sh 'make check || true' 
                //junit '**/target/*.xml' 
                sh "mvn --version"
            }
        }
         stage('Deploy') {
            when {
              expression {
                currentBuild.result == null || currentBuild.result == 'SUCCESS' 
              }
            }
            steps {
                //sh 'make publish'
                sh "mvn --version"
            }
        }
    }
}
