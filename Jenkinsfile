node {

   stage('Preparation') {
      git 'https://github.com/evandor/skysail-server.git'
   }
   
   stage('gradle build') {
      sh './gradlew clean build'
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
   
}

def buildCode() {
  sh './gradlew build'
}

def buildScaladoc() {
  sh './gradlew scaladoc'
  publishHTML([allowMissing: true, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'skysail.core/generated/docs/scaladoc', reportFiles: 'index.html', reportName: 'Scaladoc'])
}
