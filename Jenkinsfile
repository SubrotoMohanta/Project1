node {
   def mvnHome
   stage('Preparation') {
      git 'https://github.com/SubrotoMohanta/Project1.git'
      mvnHome = tool 'maven'
   }
   stage('Build') {
     
bat 'mvn clean install'
}
  
}
