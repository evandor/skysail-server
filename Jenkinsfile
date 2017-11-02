node {

   stage('Preparation') {
      git 'https://github.com/evandor/skysail-server.git'
   }
   
   stage('gradle build') {
      sh './gradlew clean build'
   }

   stage('document') {
      sh './gradlew asciidoctor --info'
      sh './gradlew scaladoc'
   }

   stage('buildJar') {
     //sh './gradlew skysail.core:export.core.int'
   }

   stage('build Docker Image') {
     //sh 'sudo ./gradlew runnable buildImage'
   }

   /*stage('restart Docker Container') {
     script{
       withEnv(['JENKINS_NODE_COOKIE =dontkill']) {
         sh "sudo ./skysail.core/deployment/scripts/run_docker.sh &"
       }
     }
   }*/

   /*stage('deployment.apps.int') {
      parallel (
  	    demo_int: { build 'skysail-core.app.demo.deploy.int' }
	    //es_int:   { build 'skysail-core.app.elasticsearch.deploy.int' }
	    //bm_int:   { build 'skysail-core.app.bookmarks.deploy.int' }
	    //pact_standalone: { build 'ssp.pact.export.standalone' }
	  )
   }*/
   
   stage('publishHTML') {
     publishHTML([
       allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false,
       reportDir: 'build/asciidoc/html5', 
       reportFiles: 'history.html',
       reportName: 'Repository History'
     ])
     publishHTML([
       allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false,
       reportDir: 'build/asciidoc/html5',
       reportFiles: 'master.html',
       reportName: 'Master Doc'
     ])
     publishHTML([
       allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false,
       reportDir: 'skysail.api/generated/docs/scaladoc',
       reportFiles: 'index.html',
       reportName: 'API Scaladoc'
     ])
     publishHTML([
       allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false,
       reportDir: 'skysail.domain/generated/docs/scaladoc',
       reportFiles: 'index.html',
       reportName: 'Domain Scaladoc'
     ])
     /*publishHTML([
       allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, 
       reportDir: 'skysail.core/generated/reports/scoverage', reportFiles: 'index.html', reportName: 'Scoverage Report'
     ])*/
   }
   
   
}

def buildCode() {
  sh './gradlew build'
}

def buildScaladoc() {
  sh './gradlew scaladoc'
  publishHTML([allowMissing: true, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'skysail.core/generated/docs/scaladoc', reportFiles: 'index.html', reportName: 'Scaladoc'])
}
