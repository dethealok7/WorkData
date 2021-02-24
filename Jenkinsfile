pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                //sh 'make' 
                //archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
                //check for working conditions
                sh "mvn --version"
                echo "hello world"
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
                //check for working conditions
                sh "mvn --version"
            }
        }
    }
}
