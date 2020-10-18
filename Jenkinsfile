pipeline{
    agent any
	triggers {
        pollSCM('*/5 * * * *')
    }
    stages{
    stage('SCM Checkout'){
             steps{
               echo 'SCM Checkout'
    		   git credentialsId: '49500cc8-0a44-48b7-bfb0-1a16653051bf', url: 'https://github.com/harilearning1989/SpringRestGradleDocker.git'
              }
          }
		stage('compile'){
            steps{
                echo 'compiling the application'
            }
        }
        stage('build'){
            steps{
                echo 'building the application'
            }
        }
        stage('test'){
            steps{
                echo 'testing the application'
            }
        }
        stage('deploy'){
            steps{
                echo 'deploying the application'
            }
        }
    }
	post{
		always{
			echo 'this will run always'
		}
		success{
			echo 'this will run success'
		}
		failure{
			echo 'this will run failure'
		}
		unstable{
			echo 'this will run unstable'
		}
		changed{
			echo 'this will run changed'
		}
	}
}