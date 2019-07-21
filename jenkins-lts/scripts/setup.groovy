import jenkins.model.*
import hudson.security.*
import hudson.*
import hudson.model.*
import jenkins.*
import java.util.*
import com.michelin.cio.hudson.plugins.rolestrategy.*
import java.lang.reflect.*

def env = System.getenv()


def jenkinsParameters = [
  "email":  "System Admin <admin@jenkins.com>",
  "url":    "http://jenkins.varu.local:8080"
  ]
// get Jenkins location configuration
def jenkinsLocationConfiguration = JenkinsLocationConfiguration.get()

jenkinsLocationConfiguration.setAdminAddress(jenkinsParameters.email)
jenkinsLocationConfiguration.setUrl(jenkinsParameters.url)

jenkinsLocationConfiguration.save()


// Create user 
def jenkins = Jenkins.getInstance()
if(!(jenkins.getSecurityRealm() instanceof HudsonPrivateSecurityRealm))
    jenkins.setSecurityRealm(new HudsonPrivateSecurityRealm(false))

if(!(jenkins.getAuthorizationStrategy() instanceof GlobalMatrixAuthorizationStrategy))
    jenkins.setAuthorizationStrategy(new GlobalMatrixAuthorizationStrategy())

def user = jenkins.getSecurityRealm().createAccount(env.JENKINS_USER, env.JENKINS_PASS)
user.save()
jenkins.getAuthorizationStrategy().add(Jenkins.ADMINISTER, env.JENKINS_USER)


// Save Jenkins
jenkins.save()





