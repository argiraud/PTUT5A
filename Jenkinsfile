pipeline {
    agent any 
    triggers {
        githubPush()
    }
    stages {
        stage('Build') {
            steps {
                echo "Build"
                sh 'npm install --prefix /var/lib/jenkins/workspace/PolyRecrute_master/front'
                sh 'npm run build --prefix /var/lib/jenkins/workspace/PolyRecrute_master/front'
                sh 'mvn -f /var/lib/jenkins/workspace/PolyRecrute_master/back/pom.xml dependency:tree'
                sh 'mvn -f /var/lib/jenkins/workspace/PolyRecrute_master/back/pom.xml package'
                sh 'chmod -R 777 /var/lib/jenkins/workspace/PolyRecrute_master/'
            }
        }
        stage('Deploy') {
            steps {
                echo "Deploy" 
                
                sh 'sudo service nginx restart'
                sh 'systemctl restart springserver'
            }
        }
    }
}
