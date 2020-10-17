pipeline{
    agent any
	triggers {
        pollSCM('*/5 * * * *')
    }
    stages{
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